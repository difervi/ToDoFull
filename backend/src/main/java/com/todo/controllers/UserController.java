package com.todo.controllers;

import com.todo.dtos.UserCreateDto;
import com.todo.dtos.UserDto;
import com.todo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.findById(id); // Pending check why the password is showing when we get the user by id
    }


    @GetMapping("/email")
    public UserDto getUserByEmail(@RequestParam String email) {
        return userService.findByEmail(email);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserCreateDto userCreateDto) {
        UserDto createdUser = userService.createUser(userCreateDto);
       return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserCreateDto userCreateDto) {
        return userService.updateUser(id, userCreateDto);
    }
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAll();
    }
}