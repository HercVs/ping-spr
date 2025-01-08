package gr.eduping.eduping.rest;

import gr.eduping.eduping.core.exceptions.*;
import gr.eduping.eduping.dto.UserInsertDTO;
import gr.eduping.eduping.dto.UserReadOnlyDTO;
import gr.eduping.eduping.dto.UserUpdateDTO;
import gr.eduping.eduping.service.UserService;
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
public class UserRestController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/insert")
    public ResponseEntity<UserReadOnlyDTO> insertUser(@Valid @RequestBody UserInsertDTO userInsertDTO,
                                                      BindingResult bindingResult)
            throws EntityAlreadyExistsException, ValidationException {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }

        // TODO comment for testing
//        String rawPwd = userInsertDTO.getPassword();
//        String encryptedPwd = passwordEncoder.encode(rawPwd);
//        userInsertDTO.setPassword(encryptedPwd);

        UserReadOnlyDTO userReadOnlyDTO = userService.insertUser(userInsertDTO);
        return new ResponseEntity<>(userReadOnlyDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserReadOnlyDTO> updateUser(@PathVariable Long userId,
                                                      @Valid @RequestBody UserUpdateDTO userUpdateDTO,
                                                      BindingResult bindingResult)
            throws ValidationException, EntityNotFoundException, EntityInvalidArgumentsException, EntityNotAuthorizedException {

        if (false) { // TODO User should only be able to update their own details
            throw new EntityNotAuthorizedException("User", "Not authorized");
        }

        if (userId != userUpdateDTO.getId()) { // TODO Should be covered above by comparing with JWT
            throw new EntityInvalidArgumentsException("User", "Invalid id: " + userId +
                    " for user with id: " + userUpdateDTO.getId());
        }

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }

        UserReadOnlyDTO userReadOnlyDTO = userService.updateUser(userUpdateDTO);
        return new ResponseEntity<>(userReadOnlyDTO, HttpStatus.OK);
    }
}