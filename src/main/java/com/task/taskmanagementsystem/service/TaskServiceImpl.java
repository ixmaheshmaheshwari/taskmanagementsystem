package com.task.taskmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task.taskmanagementsystem.model.Task;
import com.task.taskmanagementsystem.repository.TaskRepository;
import com.task.taskmanagementsystem.response.MessageResponse;



@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public ResponseEntity<MessageResponse> getAllTasks() {
		
		
        List<Task> result = taskRepository.findAll();
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Task not found with id: ", null));
        } else {
            return ResponseEntity.ok(new MessageResponse("Successfully Fetched record", result));
        }
		    
	}
	
	

	@Override
    public ResponseEntity<MessageResponse> getTaskById(Long id) {
		if(id == null) {
			return ResponseEntity.badRequest().body(new MessageResponse("Couldn't find record with the provided id", null));
			}
        Optional<Task> result = taskRepository.findById(id); // crud repository
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Task not found with id: " + id, null));
        } else {
            return ResponseEntity.ok(new MessageResponse("Successfully Fetched record", result.get()));
        }
		    }



	@Override
	public ResponseEntity<MessageResponse> createTask(Task taskRequest) {
		// TODO Auto-generated method stub
		Task result=taskRepository.save(taskRequest);
		return ResponseEntity.ok(new MessageResponse("Task Added",result));
	}
	@Override
	 @Transactional
	public ResponseEntity<MessageResponse> updateTask(Task taskRequest, Long id) {

		if (taskRequest == null) {
            return ResponseEntity.ofNullable(new MessageResponse("Todo data cannot be null", null));
            		
        }
//
        
        if (id == null || id <= 0) {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Task not found with id: " + id,null));        }
//
//        
        Optional<Task> existingTaskOptional = taskRepository.findById(id);
        if (existingTaskOptional.isEmpty()) {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Task not found with id: " + id,null));        }
//
        Task existingTask = existingTaskOptional.get();
//
        if (taskRequest.getTitle() != null) {
        	existingTask.setTitle(taskRequest.getTitle());
        }

        if (taskRequest.getDescription() != null) {
            existingTask.setDescription(taskRequest.getDescription());
        }

        if (taskRequest.getDueDate() != null) {
        	existingTask.setDueDate(taskRequest.getDueDate());
        }

        Boolean completed = taskRequest.isCompleted();
        if (completed != null) {
            existingTask.setCompleted(completed);
        }
        Task task = taskRepository.save(existingTask);

        return ResponseEntity.ok(new MessageResponse("Task Upadated Successfully",task));

	}
	
	@Override
	public ResponseEntity<MessageResponse> markTaskAsCompleted(Long id,Boolean isComplete) {
		if(isComplete== null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Task not found with id: " + id,null));		}
		
		Optional<Task> existingTaskOptional = taskRepository.findById(id);
		if (existingTaskOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Task not found with id: " + id,null));        }
		 Task existingTask = existingTaskOptional.get();
		 existingTask.setCompleted(isComplete);
		 return ResponseEntity.ok(new MessageResponse("Task Updated Successfully.",existingTask));
	 
    }



	@Override
	 public ResponseEntity<MessageResponse> deleteTask(Long id) {
        boolean isDeleted = taskRepository.existsById(id);
        if (isDeleted) {
        	Optional<Task> Optionaltask=taskRepository.findById(id);
        	Task task=Optionaltask.get();
        	taskRepository.delete(task);
            return ResponseEntity.ok(new MessageResponse("Task deleted successfully",null));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Task not found with id: " + id,null));
        }
    }
	 
     
}
	



	
    

	
