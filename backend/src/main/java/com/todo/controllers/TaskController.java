package com.todo.controllers;

import com.todo.dtos.TaskDto;
import com.todo.dtos.UserDto;
import com.todo.entities.Task;
import com.todo.entities.User;
import com.todo.services.TaskService;
import com.todo.services.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "Task Controller", description = "API for managing all tasks in the application")
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task, @RequestParam Long userId) {
        UserDto userDto = userService.findById(userId);
        User user = userService.convertToEntity(userDto);
        task.setUser(user);
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.ok(createdTask);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> findById(@PathVariable Long id) {
        TaskDto taskDto = taskService.findById(id);
        return ResponseEntity.ok(taskDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String,Object>> updateTask(@PathVariable Long id, @RequestBody Task task) {
        Task updatedTask = taskService.updateTask(id, task);
        Map<String, Object> response = new HashMap<>();
        response.put("id", updatedTask.getId());
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<Map<String, Object>> completeTask(@PathVariable Long id) {
        Task completedTask = taskService.completeTask(id);
        Map<String, Object> response = new HashMap<>();
        response.put("id", completedTask.getId());
        response.put("title", completedTask.getTitle());
        response.put("completed", completedTask.isCompleted());
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/uncompleted")
    public ResponseEntity<Map<String, Object>> uncompletedTask(@PathVariable Long id) {
        Task uncompletedTask = taskService.uncompleteTask(id);
        Map<String, Object> response = new HashMap<>();
        response.put("id", uncompletedTask.getId());
        response.put("title", uncompletedTask.getTitle());
        response.put("completed", uncompletedTask.isCompleted());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> findAll() {
        List<TaskDto> tasks = taskService.findAll();
        return ResponseEntity.ok(tasks);
    }
}