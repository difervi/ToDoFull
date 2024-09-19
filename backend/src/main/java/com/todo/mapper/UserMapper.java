package com.todo.mapper;

import com.todo.dtos.UserCreateDto;
import com.todo.dtos.UserDto;
import com.todo.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreateDto userCreateDto);

    @Mapping(source = "name", target = "username")
    UserDto toUserDto(User user);

    UserCreateDto toUserCreateDto(User user);
}