package com.todo.dtos;

import lombok.Data;
import lombok.Setter;

@Data
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    @Setter
    private boolean completed;


}
