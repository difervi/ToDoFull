package com.todo.dtos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class UserCreateDto {
    @NotNull
    @Size(max = 255)
    private  String name;

    @NotNull
    @Size(max = 255)
    private  String email;

    @NotNull
    private  String password;

}
