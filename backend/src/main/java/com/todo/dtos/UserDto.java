package com.todo.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long id;
    private String name;
    private String email;

    public void setUsername(String s) {
    }

    public String getUsername() {
        return name;
    }
}