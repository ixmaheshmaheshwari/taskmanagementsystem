package com.task.taskmanagementsystem.repository;

import com.task.taskmanagementsystem.model.Task;


import org.springframework.data.jpa.repository.JpaRepository;
public interface TaskRepository extends JpaRepository<Task, Long> {

	
}
