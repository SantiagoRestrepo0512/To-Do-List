package com.nauth.todo.repository;
import com.nauth.todo.entity.Task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
	
	@Query("SELECT tasks FROM Task tasks ORDER BY tasks.priority ASC")
    List<Task> findAllOrderedByPriority();
}
