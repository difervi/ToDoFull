package com.todo.dtos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

public class UserCreateDto {
    @NotNull
    @Size(max = 255)
    private  String name;

    @NotNull
    @Size(max = 255)
    private  String email;

    @NotNull
    private  String password;


    public void setId(long l) {
    }

    public void setUsername(String s) {
    }
}
