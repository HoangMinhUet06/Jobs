package com.jobs.controller;

import java.util.List;
import java.util.Optional;

import com.jobs.util.error.IdInvalidException;
import com.jobs.service.UserService;
import com.jobs.domain.User;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// REST Controller for User CRUD operations
// Handles HTTP requests: GET, POST, PUT, DELETE for /users endpoints
// @RestController = @Controller + @ResponseBody (returns JSON automatically)
@RestController
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    // Constructor Injection - Spring auto-injects dependencies
    // Better than @Autowired: explicit dependencies, easier testing, immutable
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    // POST /users - Create new user
    // @RequestBody = parse JSON body to User object
    // Password is hashed in UserService before saving
    @PostMapping("/users")
    public ResponseEntity<User> createNewUser(
            @RequestBody User postManUser) {

        // UserService will handle password hashing
        User newUser = this.userService.handleCreateUser(postManUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    // DELETE /users/{id} - Delete user by ID
    // @PathVariable = extract {id} from URL path
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id) throws IdInvalidException {

        if (id <= 0) {
            throw new IdInvalidException("ID is invalid");
        }
        this.userService.handleDeleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted");
    }

    // GET /users/{id} - Get single user by ID
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        User user = this.userService.handleGetUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    // GET /users - Get all users
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(
            @RequestParam("current") Optional<String> currentOptional,
            @RequestParam("pageSize") Optional<String> pageSizeOptional) {

        String currentString = currentOptional.isPresent() ? currentOptional.get() : "";
        String pageSizeString = pageSizeOptional.isPresent() ? pageSizeOptional.get() : "";

        int current = Integer.parseInt(currentString) - 1;
        int pageSize = Integer.parseInt(pageSizeString);
        Pageable pageable = PageRequest.of(current, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.handleGetAllUsers(pageable));
    }

    // PUT /users - Update existing user
    // Expects full User object with ID in request body
    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User updatedUser) {
        User resultUser = this.userService.handleUpdateUser(updatedUser);
        return ResponseEntity.status(HttpStatus.OK).body(resultUser);
    }
}