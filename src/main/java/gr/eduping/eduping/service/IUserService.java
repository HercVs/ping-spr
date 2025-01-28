package gr.eduping.eduping.service;

import gr.eduping.eduping.core.exceptions.EntityAlreadyExistsException;
import gr.eduping.eduping.core.exceptions.EntityInvalidArgumentsException;
import gr.eduping.eduping.core.exceptions.EntityNotFoundException;
import gr.eduping.eduping.dto.DepartmentReadOnlyDTO;
import gr.eduping.eduping.dto.UserInsertDTO;
import gr.eduping.eduping.dto.UserReadOnlyDTO;
import gr.eduping.eduping.dto.UserUpdateDTO;

import java.util.Set;

public interface IUserService {
    UserReadOnlyDTO insertUser(UserInsertDTO userInsertDTO) throws EntityAlreadyExistsException, EntityInvalidArgumentsException;
    UserReadOnlyDTO updateUser(UserUpdateDTO userUpdateDTO) throws EntityNotFoundException, EntityInvalidArgumentsException;
    UserReadOnlyDTO deleteUser(Long id) throws EntityNotFoundException;
    Set<DepartmentReadOnlyDTO> getUserDepartments(Long id);
    Set<DepartmentReadOnlyDTO> insertDepartmentToUser(Long userId, Long departmentId)
            throws EntityNotFoundException, EntityAlreadyExistsException;
    Set<DepartmentReadOnlyDTO> removeDepartmentFromUser(Long userId, Long departmentId)
            throws EntityNotFoundException, EntityInvalidArgumentsException;
}