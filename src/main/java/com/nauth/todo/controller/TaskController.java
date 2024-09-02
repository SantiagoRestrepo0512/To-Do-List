package com.nauth.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nauth.todo.entity.Task;
import com.nauth.todo.service.TaskService;

@RestController
@RequestMapping("api/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	public Task createTask(Task task) {
	return taskService.createTask(task);
		
	
	
	
	
		}
	
}