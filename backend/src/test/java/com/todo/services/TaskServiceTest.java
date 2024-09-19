package com.todo.services;

import com.todo.entities.Task;
import com.todo.exception.ResourceNotFoundException;
import com.todo.repositories.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTask(){
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Task 1");
        task.setDescription("Description 1");

        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task createdTask = taskService.createTask(task);

        assertEquals(1L, createdTask.getId());
        assertEquals("Task 1", createdTask.getTitle());
        assertEquals("Description 1", createdTask.getDescription());
    }

    @Test
    public void testFindTaskById(){
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Task 1");
        task.setDescription("Description 1");

        when(taskRepository.findById(1L)).thenReturn(java.util.Optional.of(task));

        Task foundTask = taskService.findTaskById(1L);

        assertEquals(1L, foundTask.getId());
        assertEquals("Task 1", foundTask.getTitle());
        assertEquals("Description 1", foundTask.getDescription());
    }

    @Test
    public void testUpdateTask(){
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Task 1");
        task.setDescription("Description 1");

        Task updatedTask = new Task();
        updatedTask.setId(1L);
        updatedTask.setTitle("Task 1 Updated");
        updatedTask.setDescription("Description 1 Updated");

        when(taskRepository.findById(1L)).thenReturn(java.util.Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(updatedTask);

        Task taskDetails = new Task();
        taskDetails.setTitle("Task 1 Updated");
        taskDetails.setDescription("Description 1 Updated");

        Task updated = taskService.updateTask(1L, taskDetails);

        assertEquals(1L, updated.getId());
        assertEquals("Task 1 Updated", updated.getTitle());
        assertEquals("Description 1 Updated", updated.getDescription());
    }
    @Test
    public void testCompleteTask(){
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Task 1");
        task.setDescription("Description 1");

        when(taskRepository.findById(1L)).thenReturn(java.util.Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task completedTask = taskService.completeTask(1L);

        assertEquals(1L, completedTask.getId());
        assertEquals("Task 1", completedTask.getTitle());
        assertEquals("Description 1", completedTask.getDescription());
        assertTrue(completedTask.isCompleted());
    }
    @Test
    public void testUncompleteTask(){
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Task 1");
        task.setDescription("Description 1");
        task.setCompleted(true);

        when(taskRepository.findById(1L)).thenReturn(java.util.Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task uncompletedTask = taskService.uncompleteTask(1L);

        assertEquals(1L, uncompletedTask.getId());
        assertEquals("Task 1", uncompletedTask.getTitle());
        assertEquals("Description 1", uncompletedTask.getDescription());
        assertFalse(uncompletedTask.isCompleted());
    }
    @Test
    public void testDeleteById(){
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Task 1");
        task.setDescription("Description 1");

        when(taskRepository.existsById(1L)).thenReturn(true);

        taskService.deleteById(1L);
    }
    @Test
    public void testDeleteByIdThrowsException(){
        when(taskRepository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> taskService.deleteById(1L));
    }
    @Test
    public void testGetTaskResponse(){
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Task 1");
        task.setDescription("Description 1");

        TaskService taskService = new TaskService(taskRepository);

        assertEquals(1L, taskService.getTaskResponse(task).get("id"));
    }
    @Test
    public void testGetTaskResponseTitle(){
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Task 1");
        task.setDescription("Description 1");

        TaskService taskService = new TaskService(taskRepository);

        assertEquals("Task 1", taskService.getTaskResponse(task).get("title"));
    }
    @Test
    public void testGetTaskResponseDescription(){
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Task 1");
        task.setDescription("Description 1");

        TaskService taskService = new TaskService(taskRepository);

        assertEquals("Description 1", taskService.getTaskResponse(task).get("description"));
    }
}
