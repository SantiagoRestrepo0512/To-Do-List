package com.nauth.todo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauth.todo.entity.Task;
import com.nauth.todo.repository.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;

	public Task createTask(Task task) {
		return taskRepository.save(task);
	}
	
	public Task getUserById(Long id) {
		Optional<Task> optionalTask = taskRepository.findById(id);
		return optionalTask.get();
	}
	
	public List<Task> getAllTasks(){
		return taskRepository.findAll();
	}
	
	public void deleteTask(Long id) {
		taskRepository.deleteById(id);
	}
	
	public Task updateTask(Long id, Task task) {
       
        Optional<Task> optionalTask = taskRepository.findById(id);
         	Task existingTask = optionalTask.get();
            existingTask.setDescription(task.getDescription());
            existingTask.setCompleted(task.getCompleted());
            return taskRepository.save(existingTask);
       

}
}
