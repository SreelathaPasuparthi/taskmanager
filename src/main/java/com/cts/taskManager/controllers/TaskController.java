package com.cts.taskManager.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.taskManager.model.Task;
import com.cts.taskManager.service.TaskService;

@RestController
public class TaskController {
	@Autowired
	TaskService taskService;
	
	@RequestMapping(method=RequestMethod.GET, value="/tasks")
	public List<Task> task(){
		return taskService.finAllTasks();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/tasks")
	public Task saveTask(@RequestBody Task task) {
		return taskService.saveTask(task);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/tasks/{id}")
	public Task viewTask(@PathVariable String id) {
		return taskService.getTaskByID(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/tasks/{id}")
	public Task updateTask(@PathVariable String id , @RequestBody Task task) {
		return taskService.updateTask(id, task);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/tasks/endtask/{id}")
	public  Iterable<Task> updateEndTask(@PathVariable String id) {
		return taskService.updateEndTask(id);
	}
	
	
	
	@RequestMapping(method=RequestMethod.POST, value="/tasks/searchtask")
	public List<Task> searchTaskDetails( @RequestBody Task task) {
		return taskService.searchTaskListByInputValues(task);
	}

}
