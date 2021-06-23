package com.carDealership.Application.controller;

import com.carDealership.Application.dto.UserDTO;
import com.carDealership.Application.entity.UserRoleEnum;
import com.carDealership.Application.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;

    UserController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping("/")
    public List<UserDTO> allUsers() {
        return userService.allUsers();
    }

    @GetMapping("/{id}")
    public UserDTO oneUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("roles/{role}")
    public List<UserDTO> getByRole(UserRoleEnum role) {
        return userService.findByRole(role);
    }

    @PostMapping("/add")
    public UserDTO newUser(@RequestBody UserDTO newUser) {
        return this.userService.newUser(newUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        boolean ok = this.userService.deleteUser(id);
    }

}
