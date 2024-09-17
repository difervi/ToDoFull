package com.todo.mapper;

import com.todo.dtos.UserCreateDto;
import com.todo.dtos.UserDto;
import com.todo.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "password", ignore = true)
    User toUser(UserCreateDto userCreateDto);
    UserDto toUserDto(User user);
    UserCreateDto toUserCreateDto(User user);
}
