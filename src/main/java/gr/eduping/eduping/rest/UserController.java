package gr.eduping.eduping.rest;

import gr.eduping.eduping.authentication.AuthenticationService;
import gr.eduping.eduping.core.exceptions.*;
import gr.eduping.eduping.dto.UserInsertDTO;
import gr.eduping.eduping.dto.UserReadOnlyDTO;
import gr.eduping.eduping.dto.UserUpdateDTO;
import gr.eduping.eduping.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

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
}