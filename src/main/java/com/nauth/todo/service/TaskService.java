package com.nauth.todo.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.nauth.todo.entity.Task;
import com.nauth.todo.repository.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;

	public Task createTask(Task task) {
		validatePriority(task.getPriority());
		return taskRepository.save(task);
	}
	

	 public Task getTaskById(Long id) {
	        return taskRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarea con el ID " + id + " no encontrada"));
	    }
	

	  public List<Task> getAllTasksOrderedByPriority() {
	        List<Task> tasks = taskRepository.findAllOrderedByPriority();

	        tasks.sort(Comparator.comparingInt(Task::getPriority).thenComparingLong(Task::getId));

	        return tasks;
	    }
		
	
	public void deleteTask(Long id) {
		 if (!taskRepository.existsById(id)) {
			 throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarea con el ID " + id + " no encontrada");
		    }	
		    taskRepository.deleteById(id);
	}
	
	
	 public Task updateTask(Long id, Task updatedTask) {
	        
	        Optional<Task> optionalTask = taskRepository.findById(id);
	        
	        if (optionalTask.isPresent()) {
	            Task existingTask = optionalTask.get();
	            
	            if (updatedTask.getDescription() != null) {
	                existingTask.setDescription(updatedTask.getDescription());
	            }
	            
	            if (updatedTask.getCompleted() != null) {
	                existingTask.setCompleted(updatedTask.getCompleted());
	            }
	            
	            
	            if (updatedTask.getPriority() != null) {
	                existingTask.setPriority(updatedTask.getPriority());
	            }
	            
	            return taskRepository.save(existingTask);
	        } else {

	            throw new RuntimeException("Task not found with id: " + id);
	        }
	 }
	
	 private void validatePriority(Integer priority) {
	        if (priority == null || priority < 1 || priority > 3) {
	        	throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La prioridad debe estar en un rango de 1 a 3");
	        }
	
	
	 }
}
