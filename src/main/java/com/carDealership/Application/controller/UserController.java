package com.carDealership.Application.controller;

import com.carDealership.Application.dto.UserDTO;
import com.carDealership.Application.entity.UserRoleEnum;
import com.carDealership.Application.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity <List<UserDTO>> allUsers() {
        return new ResponseEntity<> (userService.allUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<UserDTO> oneUser(@PathVariable Long id) {
        return new ResponseEntity<> (userService.findById(id), HttpStatus.OK);
    }

    @GetMapping("roles/{role}")
    ResponseEntity <List<UserDTO>> getByRole(UserRoleEnum role) {
        return new ResponseEntity<> (userService.findByRole(role), HttpStatus.OK);
    }

    @PostMapping("/add")
    ResponseEntity <UserDTO> newUser(@RequestBody UserDTO newUser) {
        return new ResponseEntity<> (this.userService.newUser(newUser), HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    ResponseEntity <UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable Long id) {
        return new ResponseEntity<> (this.userService.updateUser(userDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean ok = this.userService.deleteUser(id);
        if (ok) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
