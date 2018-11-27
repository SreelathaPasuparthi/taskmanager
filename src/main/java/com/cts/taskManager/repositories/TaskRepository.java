package com.cts.taskManager.repositories;

import java.util.List;

import com.cts.taskManager.model.Task;

public interface TaskRepository {
	
	Task findByID(String id);
	 
	Task save(Task task);
	
	List<Task> findAll();
	
	List<Task> findTaskListBySearch(Task searchTask);
	

}
