package com.nauth.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nauth.todo.entity.Task;
import com.nauth.todo.service.TaskService;

@RestController
@RequestMapping("api/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	
	@PostMapping("/create")
	public Task createTask(@RequestBody Task task) {
		return taskService.createTask(task);
		
	
		}
	
	@GetMapping
	public List<Task> getAllTasksOrderedByPriority() {
        return taskService.getAllTasksOrderedByPriority();
    }
	
	
	@GetMapping("/get/{id}")
	public Task getTaskById(@PathVariable("id") Long id) {
		return taskService.getTaskById(id);
		
	}
	
	@PutMapping("/update/{id}")
	public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }
    
	
	@DeleteMapping("/delete/{id}")
	public String deleteTaskById(@PathVariable("id") Long id) {
		taskService.deleteTask(id);
		return "Tarea #"+id + " eliminada con exito";
		
	}
	
}