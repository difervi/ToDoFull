
package com.todo.controllers;

import com.todo.dtos.TaskDto;
import com.todo.dtos.UserDto;
import com.todo.entities.Task;
import com.todo.entities.User;
import com.todo.services.TaskService;
import com.todo.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Task createTask(@RequestBody Task task, @RequestParam Long userId) {
        UserDto userDto = userService.findById(userId);
        User user = userService.convertToEntity(userDto);
        task.setUser(user);
        return taskService.createTask(task);
    }

    @GetMapping("/{id}")
    public Task findById(@PathVariable Long id) {
        return taskService.findById(id);
    }

    @PutMapping("/{id}")
    public Map<String, Object> updateTask(@PathVariable Long id, @RequestBody Task task) {
        Task updatedTask = taskService.updateTask(id, task);
        Map<String, Object> response = new HashMap<>();
        response.put("id", updatedTask.getId());
        response.put("title", updatedTask.getTitle());
        response.put("description", updatedTask.getDescription());
        response.put("dueDate", updatedTask.getDueDate());
        response.put("completed", updatedTask.isCompleted());
        return response;
    }

    @PatchMapping("/{id}/complete")
    public Map<String, Object> completeTask(@PathVariable Long id) {
        Task completedTask = taskService.completeTask(id);
        Map<String, Object> response = new HashMap<>();
        response.put("id", completedTask.getId());
        response.put("title", completedTask.getTitle());
        response.put("completed", completedTask.isCompleted());
        return response;
    }

    @PatchMapping("/{id}/uncomplete")
    public Map<String, Object> uncompleteTask(@PathVariable Long id) {
        Task uncompletedTask = taskService.uncompleteTask(id);
        Map<String, Object> response = new HashMap<>();
        response.put("id", uncompletedTask.getId());
        response.put("title", uncompletedTask.getTitle());
        response.put("completed", uncompletedTask.isCompleted());
        return response;
    }



    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        taskService.deleteById(id);
    }

    @GetMapping
    public List<Task> findAll() {
        return taskService.findAll();
    }
}