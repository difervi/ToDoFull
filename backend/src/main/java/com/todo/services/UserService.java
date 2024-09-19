package com.todo.services;

import com.todo.dtos.UserCreateDto;
import com.todo.dtos.UserDto;
import com.todo.entities.User;
import com.todo.mapper.UserMapper;
import com.todo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    public boolean userExistByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public UserDto createUser(UserCreateDto userCreateDto) {
        if (userExistByEmail(userCreateDto.getEmail())) {
            throw new RuntimeException("User already exists");
        }
        User user = userMapper.toUser(userCreateDto);
        user.setPassword(userCreateDto.getPassword());
        userRepository.save(user);
        return userMapper.toUserDto(user);
    }

    public UserDto findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return userMapper.toUserDto(user);
    }

    public UserDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return userMapper.toUserDto(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
    public UserDto updateUser(Long id, UserCreateDto userCreateDto) {
        User user = userRepository.findById(id).orElseThrow();
        user.setName(userCreateDto.getName());
        user.setEmail(userCreateDto.getEmail());
        user.setPassword(userCreateDto.getPassword());
        userRepository.save(user);
        return userMapper.toUserDto(user);
    }

    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().
                map(userMapper::toUserDto).
                collect(Collectors.toList());
    }
    public User convertToEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        return user;
    }
}