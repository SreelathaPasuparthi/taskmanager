package com.cts.taskManager.repositories.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.cts.taskManager.model.Task;
import com.cts.taskManager.repositories.TaskRepository;

@Repository
public class TaskRepositoryImpl implements TaskRepository{
	
	@Autowired
	private MongoTemplate mongoTemplate;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	@Override
	public Task findByID(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		return mongoTemplate.findOne(query, Task.class);
	}
	
	public Task findByTaskName(String name) {
		Query query = new Query();
		query.addCriteria(Criteria.where("taskName").is(name));
		return mongoTemplate.findOne(query, Task.class);
	}


	@Override
	public Task save(Task task) {
		mongoTemplate.save(task);
		return task;
	}

	@Override
	public List<Task> findAll() {
		return mongoTemplate.findAll(Task.class);

	}

	@Override
	public List<Task> findTaskListBySearch(Task searchTask) {
		Query query = new Query();
		if(searchTask.getTaskName()!=null && !searchTask.getTaskName().isEmpty()) {
			query.addCriteria(Criteria.where("taskName").is(searchTask.getTaskName()));
		}
		if(searchTask.getParentTaskName() != null && !searchTask.getParentTaskName().isEmpty()) {
			Task parentTask = findByTaskName(searchTask.getParentTaskName());
			query.addCriteria(Criteria.where("parentID").is(parentTask.getId()));
		}
		
		if(searchTask.getPriorityFrom() != null && searchTask.getPriorityTo() != null) {
			query.addCriteria(Criteria.where("priority").gt(searchTask.getPriorityFrom())
					.orOperator(Criteria.where("priority").lt(searchTask.getPriorityTo())));
		}
		if(searchTask.getStartDate() != null) {
			Date startDatePreviousDate = getFormattedDate(searchTask.getStartDate(),-1);
			Date startDateNextDate = getFormattedDate(searchTask.getStartDate(),+1);
				query.addCriteria(Criteria.where("startDate").gt(startDatePreviousDate)
						.andOperator(Criteria.where("startDate").lt(startDateNextDate)));
		}
				
		if(searchTask.getEndDate() != null) {
			Date endDatePreviousDate = getFormattedDate(searchTask.getEndDate(),-1);
			Date endDateNextDate = getFormattedDate(searchTask.getEndDate(),+1);
				query.addCriteria(Criteria.where("endDate").gt(endDatePreviousDate)
						.andOperator(Criteria.where("endDate").lt(endDateNextDate)));
		}
		
		return mongoTemplate.find(query , Task.class);
	}

	private Date getFormattedDate(Date inputDate, int i) {
	 Date formattedInputDate =null; 
      try {
    	  String inputDateStringFormat = dateFormat.format(inputDate);
    	  Date formattedDate = dateFormat.parse(inputDateStringFormat);
          Calendar cal = Calendar.getInstance();
          cal.setTime(formattedDate);
          cal.add(Calendar.DATE,i);
          String formattedDateString = dateFormat.format(cal.getTime());
          formattedInputDate = dateFormat.parse(formattedDateString);
      }
      catch(Exception e) {
    	  
      }
	return formattedInputDate;
	}

}
