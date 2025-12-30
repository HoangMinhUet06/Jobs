package com.vehiclestore.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.vehiclestore.service.UserService;
import com.vehiclestore.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    // Constructor, Spring auto inject UserService
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public User createNewUser(
            @RequestBody User postManUser) {

        User newUser = this.userService.handleCreateUser(postManUser);
        return newUser;
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        this.userService.handleDeleteUser(id);
        return "User deleted";
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") long id) {
        User user = this.userService.handleGetUserById(id);
        return user;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return this.userService.handleGetAllUsers();
    }

    @PutMapping("/user")
    public User updateUser(@RequestBody User updatedUser) {
        User resultUser = this.userService.handleUpdateUser(updatedUser);
        return resultUser;
    }
}