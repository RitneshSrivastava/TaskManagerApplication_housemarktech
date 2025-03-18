package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.TaskPriority;
import com.example.taskmanager.model.TaskStatus;
import com.example.taskmanager.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {
    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnAllTasks() {
        List<Task> mockTasks = List.of(new Task(1L, "Test Task", "Description", TaskStatus.PENDING, TaskPriority.MEDIUM));
        when(taskRepository.findAll()).thenReturn(mockTasks);

        List<Task> tasks = taskService.getAllTasks();
        assertEquals(1, tasks.size());
    }

    @Test
    void shouldAddTask() {
        Task task = new Task(1L, "New Task", "Description", TaskStatus.PENDING, TaskPriority.HIGH);
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task savedTask = taskService.addTask(task);
        assertNotNull(savedTask);
        assertEquals("New Task", savedTask.getTitle());
    }

    @Test
    void shouldUpdateTask() {
        Task existingTask = new Task(1L, "Old Task", "Old Description", TaskStatus.PENDING, TaskPriority.LOW);
        Task updatedTask = new Task(1L, "Updated Task", "Updated Description", TaskStatus.COMPLETED, TaskPriority.HIGH);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(any(Task.class))).thenReturn(updatedTask);

        Task result = taskService.updateTask(1L, updatedTask);
        assertEquals("Updated Task", result.getTitle());
        assertEquals(TaskStatus.COMPLETED, result.getStatus());
    }

    @Test
    void shouldDeleteTask() {
        when(taskRepository.existsById(1L)).thenReturn(true);
        doNothing().when(taskRepository).deleteById(1L);

        assertDoesNotThrow(() -> taskService.deleteTask(1L));
    }
}
