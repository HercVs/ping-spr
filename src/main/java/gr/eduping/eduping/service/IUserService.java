package gr.eduping.eduping.service;

import gr.eduping.eduping.core.exceptions.EntityAlreadyExistsException;
import gr.eduping.eduping.core.exceptions.EntityInvalidArgumentsException;
import gr.eduping.eduping.core.exceptions.EntityNotFoundException;
import gr.eduping.eduping.dto.UserInsertDTO;
import gr.eduping.eduping.dto.UserReadOnlyDTO;
import gr.eduping.eduping.dto.UserUpdateDTO;

public interface IUserService {
    UserReadOnlyDTO insertUser(UserInsertDTO userInsertDTO) throws EntityAlreadyExistsException, EntityInvalidArgumentsException;
    UserReadOnlyDTO updateUser(UserUpdateDTO userUpdateDTO) throws EntityNotFoundException, EntityInvalidArgumentsException;
}