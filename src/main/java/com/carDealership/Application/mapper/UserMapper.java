package com.carDealership.Application.mapper;

import com.carDealership.Application.dto.UserDTO;
import com.carDealership.Application.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userDtoToUser(UserDTO userDTO);

    UserDTO userToUserDto(User user);

    List<UserDTO> allUsersToDto(List<User> allUsers);

    List<UserDTO> userByRoleToDto(List<User> getByRole);
}
