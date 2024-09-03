package com.nauth.todo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.nauth.todo.entity.Task;
import com.nauth.todo.repository.TaskRepository;

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
	    public void deleteTask_TaskExists_DeleteTask() {
	        Long taskId = 1L;

	        when(taskRepository.existsById(taskId)).thenReturn(true);

	        taskService.deleteTask(taskId);
	        verify(taskRepository, times(1)).deleteById(taskId);
	    }

	    @Test
	    public void deleteTask_TaskNotExist_ThrowException() {
	        Long taskId = 1L;

	        when(taskRepository.existsById(taskId)).thenReturn(false);

	        ResponseStatusException thrown = assertThrows(ResponseStatusException.class, () -> {
	            taskService.deleteTask(taskId);
	        });

	        assertEquals(HttpStatus.NOT_FOUND, thrown.getStatusCode());
	        assertEquals("Tarea con el ID " + taskId + " no encontrada", thrown.getReason());
	    }
	    
	    
	    
	    @Test
	    public void getAllTasksOrderedByPriority_ReturnsOrderedTasks() {
	    	
	        Task task1 = new Task(1L, "Task 1", 2);
	        Task task2 = new Task(2L, "Task 2", 1);
	        Task task3 = new Task(3L, "Task 3", 3);
	        Task task4 = new Task(4L, "Task 4", 1);
	        Task task5 = new Task(5L, "Task 5", 2);

	        List<Task> tasks = Arrays.asList(task2, task1, task3, task4, task5);

	        when(taskRepository.findAllOrderedByPriority()).thenReturn(tasks);

	        List<Task> result = taskService.getAllTasksOrderedByPriority();

	        assertNotNull(result);
	        
	        assertEquals(5, result.size());

	        List<Task> expectedTasks = Arrays.asList(task2, task4, task1, task5, task3);

	        for (int i = 0; i < expectedTasks.size(); i++) {
	            Task expectedTask = expectedTasks.get(i);
	            Task actualTask = result.get(i);

	            assertEquals(expectedTask.getId(), actualTask.getId());
	            assertEquals(expectedTask.getDescription(), actualTask.getDescription());
	            assertEquals(expectedTask.getPriority(), actualTask.getPriority());
	        }

	        verify(taskRepository, times(1)).findAllOrderedByPriority();
	    }
	   
	    
	}