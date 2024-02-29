package com.task.taskmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task.taskmanagementsystem.model.Task;
import com.task.taskmanagementsystem.response.MessageResponse;
import org.springframework.http.ResponseEntity;
import com.task.taskmanagementsystem.service.TaskService;

//import java.util.Optional;

@RestController
@RequestMapping("/api/task")
public class TaskController {
	 @Autowired
		private TaskService taskService;

	    @GetMapping("/")
	    public ResponseEntity<MessageResponse> getAllTasks() {
	        return taskService.getAllTasks(); // This will fetch all tasks from the 'task' table
	    }
	    @GetMapping("/{id}")
		public ResponseEntity< MessageResponse> getTaskById(@PathVariable Long id) {
			return taskService.getTaskById(id);
		}
//MessageResponse
	    @PostMapping("/")
	    public ResponseEntity< MessageResponse> createTask(@RequestBody Task taskRequest) {
	    	return taskService.createTask(taskRequest);
	    }
	    
	    @PutMapping("/{id}")
	   public ResponseEntity<MessageResponse> updateTask(@RequestBody Task taskRequest, @PathVariable Long id) {
	      
	      return taskService.updateTask(taskRequest, id);
	    }
	    
	    @PutMapping("/{id}/complete")
	    public ResponseEntity< MessageResponse> markTaskAsCompleted( @PathVariable Long id, @RequestParam("complete") Boolean isComplete) {
			return taskService.markTaskAsCompleted(id, isComplete);
	    	
	    }
	    
	    @DeleteMapping("/{id}")
	    public ResponseEntity< MessageResponse> deleteTask(@PathVariable Long id) {
	    	return taskService.deleteTask(id);
	    }
	    	
}
