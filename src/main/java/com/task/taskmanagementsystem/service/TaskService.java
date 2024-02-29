package com.task.taskmanagementsystem.service;


import org.springframework.http.ResponseEntity;

import com.task.taskmanagementsystem.model.Task;
import com.task.taskmanagementsystem.response.MessageResponse;


public interface TaskService {

	public ResponseEntity<MessageResponse> getAllTasks();

	public ResponseEntity<MessageResponse> getTaskById(Long id);
	//taskRequest
	public ResponseEntity<MessageResponse> createTask(Task taskRequest);


	public ResponseEntity<MessageResponse> updateTask(Task taskRequest, Long id);
	//Boolean isComplete
	  public ResponseEntity<MessageResponse> markTaskAsCompleted(Long id,Boolean isComplete);

	  public ResponseEntity<MessageResponse> deleteTask( Long id);

	

		

}


