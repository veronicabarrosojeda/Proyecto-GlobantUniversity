package com.carDealership.Application.service;

import com.carDealership.Application.dto.UserDTO;
import com.carDealership.Application.entity.User;
import com.carDealership.Application.entity.UserRoleEnum;
import com.carDealership.Application.exception.NotFoundException;
import com.carDealership.Application.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

import static com.carDealership.Application.mapper.UserMapper.*;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public List<UserDTO> allUsers() {
        List<User> allUsers = userRepository.findAll();
        if (!CollectionUtils.isEmpty(allUsers)) {
            return INSTANCE.allUsersToDto(allUsers);
        }
        return Collections.emptyList();
    }

    public UserDTO findById(Long id) {
        Optional<User> foundUser = userRepository.findById(id);
        if (foundUser.isPresent()) {
            return INSTANCE.userToUserDto(foundUser.get());
        }
        throw new NotFoundException(id);
    }

    public List<UserDTO> findByRole(UserRoleEnum role) {
        List<User> getByRole = userRepository.findByRole(role);
        if (!CollectionUtils.isEmpty(getByRole)) {
            return INSTANCE.userByRoleToDto(getByRole);
        }
        return Collections.emptyList();
    }

    public UserDTO newUser(UserDTO userDTO) {
        User user = INSTANCE.userDtoToUser(userDTO);
        User savedUser = userRepository.save(user);
        return INSTANCE.userToUserDto(savedUser);
    }

    public UserDTO updateUser(UserDTO userDTO) {
        Optional<User> userToUpdate = userRepository.findById(userDTO.getId());
        if (userToUpdate.isPresent()) {
            User updateUser = INSTANCE.userDtoToUser(userDTO);
            User updatedUser = userRepository.save(updateUser);
            return INSTANCE.userToUserDto(updatedUser);
        }
        throw new NotFoundException(userDTO.getId());
    }

    public boolean deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception error) {
            return false;
        }
    }
}
