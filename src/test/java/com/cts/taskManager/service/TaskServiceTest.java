package com.cts.taskManager.service;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cts.taskManager.model.Task;
import com.cts.taskManager.repositories.TaskRepository;
import com.cts.taskManager.service.impl.TaskServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class TaskServiceTest {
	
	@Mock
	private TaskRepository taskRepository;
	
	@InjectMocks
	private TaskServiceImpl taskService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void testfindAllTasks() {
		List<Task> taskList = new ArrayList<Task>();
		taskList.add(new Task("Taskl",Long.valueOf(10),new Date(),null,null));
		taskList.add(new Task("Task2",Long.valueOf(5),new Date(),null,null));
		taskList.add(new Task("Taskl",Long.valueOf(20),new Date(),null,null));
		 when(taskRepository.findAll()).thenReturn(taskList);
		 List<Task> result = taskService.findAllTasks();
		 assertEquals(3,result.size());
		
	}
	
	@Test
	public void testSaveTask() {
		Task task = new Task("Taskl",Long.valueOf(10),new Date(),null,null);
		 when(taskRepository.save(task)).thenReturn(task);
		 Task result = taskService.saveTask(task);
		 assertEquals("Taskl",result.getTaskName());
		
	}
	

	
	

}
