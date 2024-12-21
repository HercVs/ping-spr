package gr.eduping.eduping.service;

import gr.eduping.eduping.core.exceptions.EntityAlreadyExistsException;
import gr.eduping.eduping.dto.UserInsertDTO;
import gr.eduping.eduping.dto.UserReadOnlyDTO;
import gr.eduping.eduping.mapper.Mapper;
import gr.eduping.eduping.model.User;
import gr.eduping.eduping.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final Mapper mapper;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public UserReadOnlyDTO saveUser(UserInsertDTO userInsertDTO) throws EntityAlreadyExistsException {

        if (userRepository.findByUsername(userInsertDTO.getUsername()).isPresent()) {
            throw new EntityAlreadyExistsException("User", "User with username: " +
                    userInsertDTO.getUsername() + " already exists");
        }

        User user = mapper.mapToUserEntity(userInsertDTO);
        User savedUser = userRepository.save(user);

        return mapper.mapToUserReadOnlyDTO(savedUser);
    }
}