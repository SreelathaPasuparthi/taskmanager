package com.cts.taskManager.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.taskManager.model.Task;
import com.cts.taskManager.repositories.TaskRepository;
import com.cts.taskManager.service.TaskService;
@Service
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	private TaskRepository taskRepository;

	@Override
	public List<Task> finAllTasks() {
		List<Task> taskList = taskRepository.findAll();
		updateParentTaskName(taskList);
		return taskList;
	}

	private void updateParentTaskName(List<Task> taskList) {
		for(Task task:taskList) {
			if(task.getParentID() != null)  {
				Task taskobject = taskRepository.findByID(task.getParentID());
				task.setParentTaskName(taskobject.getTaskName());
			}
		}
	}
	
	@Override
	public Task saveTask(Task task) {
		if(task.getParentTaskName() != null) {
			List<Task> parentTaskList = taskRepository.findAll();
			for(Task parentTask:parentTaskList) {
				if(parentTask.getTaskName() != null && parentTask.getTaskName() .equals(task.getParentTaskName()) ) {
					task.setParentID(parentTask.getId());
					break;
				}
			}
		}
		if(task.getParentTaskName() != null && !task.getParentTaskName().isEmpty() && task.getParentID() == null) {
			task.setParentTaskName(null);
		}
		taskRepository.save(task);
		return task;
	}

	@Override
	public Task getTaskByID(String id) {
		Task task = taskRepository.findByID(id);
		if(task.getParentID() != null)  {
			Task taskobject = taskRepository.findByID(task.getParentID());
			task.setParentTaskName(taskobject.getTaskName());
		}
		return task;
	}

	@Override
	public List<Task> updateEndTask(String id) {
		Task taskObject = taskRepository.findByID(id);
		taskObject.setEndDate(new Date());
		taskRepository.save(taskObject);
		List<Task> taskList =taskRepository.findAll();
		updateParentTaskName(taskList);
		return taskList;
	}

	@Override
	public Task updateTask(String id, Task updatedTask) {
		Task taskObject = taskRepository.findByID(id);
		if(updatedTask.getTaskName() != null)
			taskObject.setTaskName(updatedTask.getTaskName());
		if(updatedTask.getPriority() != null)
			taskObject.setPriority(updatedTask.getPriority());
		if(updatedTask.getStartDate() != null)
			taskObject.setStartDate(updatedTask.getStartDate());
		if(updatedTask.getEndDate() != null)
			taskObject.setEndDate(updatedTask.getEndDate());
		if(updatedTask.getParentTaskName() != null && !updatedTask.getParentTaskName().isEmpty()) {
			Iterable<Task> parentTaskList = taskRepository.findAll();
			for(Task parentTask:parentTaskList) {
				if(parentTask.getTaskName() != null && parentTask.getTaskName() .equals(updatedTask.getParentTaskName()) ) {
					taskObject.setParentID(parentTask.getId());
					break;
				}
			}
		}
		if((updatedTask.getParentTaskName() == null || (updatedTask.getParentTaskName() !=null && updatedTask.getParentTaskName().isEmpty())) && taskObject.getParentID() != null) {
			taskObject.setParentTaskName(null);
			taskObject.setParentID(null);
		}
		if(updatedTask.getParentTaskName() != null && !updatedTask.getParentTaskName().isEmpty() && taskObject.getParentID() == null) {
			taskObject.setParentTaskName(null);
		}
		taskRepository.save(taskObject);
		return taskObject;
	}

	@Override
	public List<Task> searchTaskListByInputValues(Task searchTask) {
		List<Task> taskList = taskRepository.findTaskListBySearch(searchTask);
		updateParentTaskName(taskList);
		return taskList;
	}

}
