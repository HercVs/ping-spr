package gr.eduping.eduping.mapper;

import gr.eduping.eduping.core.enums.Role;
import gr.eduping.eduping.dto.UserInsertDTO;
import gr.eduping.eduping.dto.UserReadOnlyDTO;
import gr.eduping.eduping.dto.UserUpdateDTO;
import gr.eduping.eduping.model.User;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public User mapToUserEntity(UserInsertDTO insertDTO) {
        User user = new User();

        user.setUsername(insertDTO.getUsername());
        user.setPassword(insertDTO.getPassword());

        // Defaults
        user.setRole(Role.ROLE_USER);
        user.setIsActive(true);

        return user;
    }

    public User mapToUserEntity(UserUpdateDTO updateDTO) {
        User user = new User();

        user.setId(updateDTO.getId());
        user.setUsername(updateDTO.getUsername());
        user.setPassword(updateDTO.getPassword());
        user.setRole(updateDTO.getRole());
        user.setIsActive(updateDTO.getIsActive());

        return user;
    }

    public UserReadOnlyDTO mapToUserReadOnlyDTO(User user) {
        UserReadOnlyDTO userReadOnlyDTO = new UserReadOnlyDTO();
        userReadOnlyDTO.setUsername(user.getUsername());
        userReadOnlyDTO.setRole(user.getRole());

        return userReadOnlyDTO;
    }
}