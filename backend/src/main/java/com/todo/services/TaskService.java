package com.todo.services;

import com.todo.dtos.TaskDto;
import com.todo.entities.Task;
import com.todo.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private  final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public  Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow();
    }

    public Task updateTask(Long id, Task task) {
        Task taskToUpdate = taskRepository.findById(id).orElseThrow();
        taskToUpdate.setTitle(task.getTitle());
        taskToUpdate.setDescription(task.getDescription());
        taskToUpdate.setDueDate(task.getDueDate());
        taskToUpdate.setCompleted(task.isCompleted());
        return taskRepository.save(taskToUpdate);
    }
    public Task completeTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setCompleted(true);
        return taskRepository.save(task);
    }
    public Task uncompleteTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setCompleted(false);
        return taskRepository.save(task);
    }


    public List<Task> findAll() {
        return taskRepository.findAll();
    }
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }


}
