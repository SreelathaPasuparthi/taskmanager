package com.cts.taskManager.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "tasks")
public class Task {
	@Id
	String id;
	@Field(value = "TASK_NAME")
	String taskName;
	@Field(value = "PRIORITY")
	Long priority;
	@Field(value = "START_DATE")
	Date startDate;
	@Field(value = "END_DATE")
	Date endDate;
	@Field(value = "PARENT_ID")
	String parentID;
	String parentTaskName;
	Long priorityFrom;
	Long priorityTo;
	

	public Task() {
	}

	public Task(String taskName, Long priority, 
			Date startDate, Date endDate, String parentID) {
		this.taskName = taskName;
		this.priority = priority;
		this.startDate = startDate;
		this.endDate = endDate;
		this.parentID = parentID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}


	public Long getPriority() {
		return priority;
	}

	public void setPriority(Long priority) {
		this.priority = priority;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public String getParentTaskName() {
		return parentTaskName;
	}

	public void setParentTaskName(String parentTaskName) {
		this.parentTaskName = parentTaskName;
	}

	public Long getPriorityFrom() {
		return priorityFrom;
	}

	public void setPriorityFrom(Long priorityFrom) {
		this.priorityFrom = priorityFrom;
	}

	public Long getPriorityTo() {
		return priorityTo;
	}

	public void setPriorityTo(Long priorityTo) {
		this.priorityTo = priorityTo;
	}



	
}
