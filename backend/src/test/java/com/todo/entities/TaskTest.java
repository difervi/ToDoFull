package com.todo.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TaskTest {

    @Test
    public void testTask(){
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Task 1");
        task.setDescription("Description 1");
        task.setCompleted(true);
        task.setDueDate("2021-12-31");
        task.setPriority("High");
        task.setCategory("Work");
        task.setCreatedAt("2021-09-01");
        task.setUpdatedAt("2021-09-01");
        task.setDeletedAt("2021-09-01");

        assertEquals(1L, task.getId());
        assertEquals("Task 1", task.getTitle());
        assertEquals("Description 1", task.getDescription());
        assertTrue(task.isCompleted());
        assertEquals("2021-12-31", task.getDueDate());
        assertEquals("High", task.getPriority());
        assertEquals("Work", task.getCategory());
        assertEquals("2021-09-01", task.getCreatedAt());
        assertEquals("2021-09-01", task.getUpdatedAt());
        assertEquals("2021-09-01", task.getDeletedAt());

    }
}
