package com.cts.taskManager.service;
import java.util.List;

import com.cts.taskManager.model.Task;

public interface TaskService {
	
	List<Task> findAllTasks();
	
	Task saveTask(Task task);
	
	Task getTaskByID(String id);
	
	Task updateTask(String id, Task task);
	
	List<Task> updateEndTask(String id);
	
	List<Task> searchTaskListByInputValues(Task searchTask);

}
