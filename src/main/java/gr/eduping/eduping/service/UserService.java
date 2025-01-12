package gr.eduping.eduping.service;

import gr.eduping.eduping.core.exceptions.EntityAlreadyExistsException;
import gr.eduping.eduping.core.exceptions.EntityInvalidArgumentsException;
import gr.eduping.eduping.core.exceptions.EntityNotFoundException;
import gr.eduping.eduping.dto.UserInsertDTO;
import gr.eduping.eduping.dto.UserReadOnlyDTO;
import gr.eduping.eduping.dto.UserUpdateDTO;
import gr.eduping.eduping.mapper.Mapper;
import gr.eduping.eduping.model.User;
import gr.eduping.eduping.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final Mapper mapper;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public UserReadOnlyDTO insertUser(UserInsertDTO userInsertDTO) throws EntityAlreadyExistsException {

        if (userRepository.findByUsername(userInsertDTO.getUsername()).isPresent()) {
            throw new EntityAlreadyExistsException("User", "User with username: " +
                    userInsertDTO.getUsername() + " already exists");
        }

        User user = mapper.mapToUserEntity(userInsertDTO);
        User savedUser = userRepository.save(user);

        return mapper.mapToUserReadOnlyDTO(savedUser);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public UserReadOnlyDTO updateUser(UserUpdateDTO userUpdateDTO)
            throws EntityNotFoundException, EntityInvalidArgumentsException {

        if (userRepository.findById(userUpdateDTO.getId()).isEmpty()) {
            throw new EntityNotFoundException("User", "User with id: " + userUpdateDTO.getId() + " not found");
        }

        if (userRepository.findByUsername(userUpdateDTO.getUsername()).isPresent()
                && !Objects.equals(
                        userRepository.findByUsername(userUpdateDTO.getUsername()).get().getId(),
                        userUpdateDTO.getId()))
        {
            throw new EntityInvalidArgumentsException("User", "User with username: " + userUpdateDTO.getUsername()
                    + " already exists");
        }

        User user = mapper.mapToUserEntity(userUpdateDTO);
        User updatedUser = userRepository.save(user);

        return mapper.mapToUserReadOnlyDTO(updatedUser);
    }
}