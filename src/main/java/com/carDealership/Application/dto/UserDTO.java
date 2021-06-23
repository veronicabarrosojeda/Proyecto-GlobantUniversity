package com.carDealership.Application.dto;

import com.carDealership.Application.entity.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    Long id;
    UserRoleEnum role;
    String name;
    int age;
}
