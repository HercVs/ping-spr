package gr.eduping.eduping.dto;

import gr.eduping.eduping.core.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserReadOnlyDTO {

    private String username;
    private Role role;
    private PersonalDetailsReadOnlyDTO personalDetailsReadOnlyDTO;
}
