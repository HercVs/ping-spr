package gr.eduping.eduping.service;

import gr.eduping.eduping.core.exceptions.EntityAlreadyExistsException;
import gr.eduping.eduping.core.exceptions.EntityInvalidArgumentsException;
import gr.eduping.eduping.core.exceptions.EntityNotFoundException;
import gr.eduping.eduping.dto.DepartmentReadOnlyDTO;
import gr.eduping.eduping.dto.UserInsertDTO;
import gr.eduping.eduping.dto.UserReadOnlyDTO;
import gr.eduping.eduping.dto.UserUpdateDTO;
import gr.eduping.eduping.mapper.DepartmentMapper;
import gr.eduping.eduping.mapper.UserMapper;
import gr.eduping.eduping.model.User;
import gr.eduping.eduping.model.static_data.Department;
import gr.eduping.eduping.repository.DepartmentRepository;
import gr.eduping.eduping.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public UserReadOnlyDTO insertUser(UserInsertDTO userInsertDTO) throws EntityAlreadyExistsException {

        if (userRepository.findByUsername(userInsertDTO.getUsername()).isPresent()) {
            throw new EntityAlreadyExistsException("User", "User with username: " +
                    userInsertDTO.getUsername() + " already exists");
        }

        User user = userMapper.mapToUserEntity(userInsertDTO);
        User savedUser = userRepository.save(user);

        return userMapper.mapToUserReadOnlyDTO(savedUser);
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

        User user = userMapper.mapToUserEntity(userUpdateDTO);
        User updatedUser = userRepository.save(user);

        return userMapper.mapToUserReadOnlyDTO(updatedUser);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public UserReadOnlyDTO deleteUser(Long id) throws EntityNotFoundException {

        User user = userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("User", "User with id: " + id + " not found"));
        UserReadOnlyDTO deletedUser = userMapper.mapToUserReadOnlyDTO(user);
        userRepository.delete(user);

        return deletedUser;
    }

    @Override
    @Transactional
    public Set<DepartmentReadOnlyDTO> getUserDepartments(Long id) {
        return departmentRepository.findAllByUsersId(id)
                .stream()
                .map(departmentMapper::mapToDepartmentReadOnlyDTO)
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public Set<DepartmentReadOnlyDTO> insertDepartmentToUser(Long userId, Long departmentId)
            throws EntityNotFoundException, EntityAlreadyExistsException {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new EntityNotFoundException("User", "User with id: " + userId + " not found"));
        Department department = departmentRepository.findById(departmentId).orElseThrow(() ->
                new EntityNotFoundException("Department", "Department with id: " + departmentId + " not found"));

        if (user.getAllDepartments().contains(department)) {
            throw new EntityAlreadyExistsException("Department", "Department with id: " + departmentId +
                    " already exists for user with id: " + userId);
        }

        user.addDepartment(department);
        return getUserDepartments(userId);
    }

    @Override
    @Transactional
    public Set<DepartmentReadOnlyDTO> removeDepartmentFromUser(Long userId, Long departmentId)
            throws EntityNotFoundException, EntityInvalidArgumentsException {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new EntityNotFoundException("User", "User with id: " + userId + " not found"));
        Department department = departmentRepository.findById(departmentId).orElseThrow(() ->
                new EntityNotFoundException("Department", "Department with id: " + departmentId + " not found"));

        if (!user.getAllDepartments().contains(department)) {
            throw new EntityInvalidArgumentsException("Department", "Department with id: " + departmentId +
                    " doesn't exist for user with id: " + userId);
        }

        user.removeDepartment(department);
        return getUserDepartments(userId);
    }
}