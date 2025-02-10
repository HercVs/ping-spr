package gr.eduping.eduping.rest;

import gr.eduping.eduping.authentication.AuthenticationService;
import gr.eduping.eduping.core.exceptions.*;
import gr.eduping.eduping.dto.*;
import gr.eduping.eduping.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    // TODO "if (!authService.isPrincipalSelf(userId) && !authService.isPrincipalAdmin()) {
    // should be in a filter, since it will be required for all requests in /api/users

    private final UserService userService;
    private final AuthenticationService authService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/insert")
    public ResponseEntity<UserReadOnlyDTO> insertUser(@Valid @RequestBody UserInsertDTO userInsertDTO,
                                                      BindingResult bindingResult)
            throws EntityAlreadyExistsException, ValidationException {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }

        String rawPwd = userInsertDTO.getPassword();
        String encryptedPwd = passwordEncoder.encode(rawPwd);
        userInsertDTO.setPassword(encryptedPwd);

        UserReadOnlyDTO userReadOnlyDTO = userService.insertUser(userInsertDTO);
        return new ResponseEntity<>(userReadOnlyDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserReadOnlyDTO> updateUser(@PathVariable Long userId,
                                                      @Valid @RequestBody UserUpdateDTO userUpdateDTO,
                                                      BindingResult bindingResult)
            throws ValidationException, EntityNotFoundException, EntityInvalidArgumentsException, EntityNotAuthorizedException {

        if (!authService.isPrincipalSelf(userUpdateDTO.getUsername()) && !authService.isPrincipalAdmin()) {
            throw new EntityNotAuthorizedException("User", "Not authorized");
        }

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }

        String rawPwd = userUpdateDTO.getPassword();
        String encryptedPwd = passwordEncoder.encode(rawPwd);
        userUpdateDTO.setPassword(encryptedPwd);

        UserReadOnlyDTO userReadOnlyDTO = userService.updateUser(userUpdateDTO);
        return new ResponseEntity<>(userReadOnlyDTO, HttpStatus.OK);
    }

    @Operation(summary = "Delete a user", description = "Deletes a new user from the DB.")
    @DeleteMapping("/{userId}")
    public ResponseEntity<UserReadOnlyDTO> deleteUser(@PathVariable Long userId)
            throws EntityNotAuthorizedException, EntityNotFoundException {

        if (!authService.isPrincipalSelf(userId) && !authService.isPrincipalAdmin()) {
            throw new EntityNotAuthorizedException("User", "Not authorized");
        }

        UserReadOnlyDTO userReadOnlyDTO = userService.deleteUser(userId);
        return new ResponseEntity<>(userReadOnlyDTO, HttpStatus.OK);
    }

    @Operation(summary = "Select user's departments",
            description = "Find all the departments that the user is subscribed to.")
    @GetMapping("/{userId}/departments")
    public ResponseEntity<Set<DepartmentReadOnlyDTO>> getUserDepartments(@PathVariable Long userId)
            throws EntityNotAuthorizedException, EntityNotFoundException {

        if (!authService.isPrincipalSelf(userId) && !authService.isPrincipalAdmin()) {
            throw new EntityNotAuthorizedException("User", "Not authorized");
        }

        Set<DepartmentReadOnlyDTO> userDepartmentsReadOnlyDTO = userService.getUserDepartments(userId);
        return new ResponseEntity<>(userDepartmentsReadOnlyDTO, HttpStatus.OK);
    }

    @Operation(summary = "Insert department to user",
            description = "Add a new department to the user's subscription list.")
    @PostMapping("/{userId}/departments")
    public ResponseEntity<Set<DepartmentReadOnlyDTO>> addDepartmentToUser(
            @PathVariable Long userId,
            @Valid @RequestBody UserDepartmentDTO userDepartmentDTO,
            BindingResult bindingResult)
            throws EntityNotAuthorizedException, EntityNotFoundException, EntityAlreadyExistsException, ValidationException {
        if (!authService.isPrincipalSelf(userId) && !authService.isPrincipalAdmin()) {
            throw new EntityNotAuthorizedException("User", "Not authorized");
        }

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }

        Set<DepartmentReadOnlyDTO> userDepartmentsReadOnlyDTO = userService.insertDepartmentToUser(userId, userDepartmentDTO.getDepartmentId());
        return new ResponseEntity<>(userDepartmentsReadOnlyDTO, HttpStatus.OK);
    }

    @Operation(summary = "Delete a department from user",
            description = "Deletes a department from the user's subscription list.")
    @DeleteMapping("/{userId}/departments/{departmentId}")
    public ResponseEntity<Set<DepartmentReadOnlyDTO>> removeDepartmentFromUser(
            @PathVariable Long userId,
            @PathVariable Long departmentId)
            throws EntityNotAuthorizedException, EntityNotFoundException, EntityInvalidArgumentsException {

        if (!authService.isPrincipalSelf(userId) && !authService.isPrincipalAdmin()) {
            throw new EntityNotAuthorizedException("User", "Not authorized");
        }

        Set<DepartmentReadOnlyDTO> userDepartmentsReadOnlyDTO = userService.removeDepartmentFromUser(
                userId,
                departmentId
        );

        return new ResponseEntity<>(userDepartmentsReadOnlyDTO, HttpStatus.OK);
    }
}