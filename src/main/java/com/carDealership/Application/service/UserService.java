package com.carDealership.Application.service;

import com.carDealership.Application.dto.UserDTO;
import com.carDealership.Application.entity.UserRoleEnum;

import java.util.List;

public interface UserService {

    List<UserDTO> allUsers();

    UserDTO findById(Long id);

    List<UserDTO> findByRole(UserRoleEnum role);

    UserDTO newUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO);

    boolean deleteUser(Long id);

}
