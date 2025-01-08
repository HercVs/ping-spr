package gr.eduping.eduping.dto;

import gr.eduping.eduping.core.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserUpdateDTO {

    private Long id;

    @NotEmpty(message = "Username must not be empty")
    @Email(message = "Invalid username")
    private String username;

    // TODO reset password should be separate
    @NotEmpty(message = "Password must not be empty")
    @Pattern(regexp = "^(?=.*?[a-z])(?=.*?[A-Z])(?=.*?\\d)(?=.*?[@$!%*?&]).{8,}$", message = "Invalid password")
    private String password;

    private Role role;

    private Boolean isActive;
}
