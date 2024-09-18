package com.todo.services;

import com.todo.dtos.TaskDto;
import com.todo.entities.Task;
import com.todo.exception.ResourceNotFoundException;
import com.todo.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task findTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));
    }

    public TaskDto findById(Long id) {
        Task task = findTaskById(id);
        return convertToDto(task);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteById(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException("Task not found with id " + id);
        }
        taskRepository.deleteById(id);
    }

    public Task updateTask(Long id, Task taskDetails) {
        Task task = findTaskById(id);
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setDueDate(taskDetails.getDueDate());
        task.setCompleted(taskDetails.isCompleted());
        return taskRepository.save(task);
    }

    public Task completeTask(Long id) {
        Task task = findTaskById(id);
        task.setCompleted(true);
        return taskRepository.save(task);
    }

    public Task uncompleteTask(Long id) {
        Task task = findTaskById(id);
        task.setCompleted(false);
        return taskRepository.save(task);
    }

    public Map<String, Object> getTaskResponse(Task task) {
        Map<String, Object> response = new HashMap<>();
        response.put("id", task.getId());
        response.put("title", task.getTitle());
        response.put("description", task.getDescription());
        response.put("dueDate", task.getDueDate());
        response.put("completed", task.isCompleted());
        return response;
    }

    public List<TaskDto> findAll() {
        return taskRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private TaskDto convertToDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setTitle(task.getTitle());
        taskDto.setDescription(task.getDescription());
        taskDto.setDueDate(task.getDueDate());
        taskDto.setCompleted(task.isCompleted());
        return taskDto;
    }
}