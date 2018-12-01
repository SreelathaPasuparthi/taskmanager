package com.cts.taskManager.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cts.taskManager.TaskManagerApplication;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TaskManagerApplication.class )
@SpringBootTest

public class TaskControllerTest{
	
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
    @Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	@Test
	public void verifyfindAllTasks() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/tasks").
				accept(MediaType.APPLICATION_JSON)).
		andExpect(status().isOk());
	}

	@Test
	public void verifysaveTask() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/tasks").contentType(MediaType.APPLICATION_JSON)
				.content("{\"taskName\" : \"TaskTest\",\"priority\" : 10,\"startDate\":\"2018-11-29T00:00:00.000+0000\"}").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").exists()).andExpect(jsonPath("$.taskName").exists());
	}
	
	@Test
	public void verifyTaskById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/tasks/5c008ad7f657d80d7084ab1e").
				accept(MediaType.APPLICATION_JSON)).
		andExpect(jsonPath("$.id").exists());
	}

	
	
}
