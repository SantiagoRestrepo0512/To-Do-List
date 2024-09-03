package com.nauth.todo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
	    public void testCreateTask_ValidPriority() {
	       
	        Task task = new Task();
	        task.setPriority(2);
	        when(taskRepository.save(any(Task.class))).thenReturn(task);

	        Task result = taskService.createTask(task);

	        assertNotNull(result);
	        assertEquals(2, result.getPriority());
	        verify(taskRepository, times(1)).save(task);
	    }
}
