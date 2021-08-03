package com.capgemini.services;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.Task;
import com.capgemini.persistence.TasksRepository;
import com.capgemini.persistence.dto.TaskDto;

@Service
public class TasksService {
	
	@Autowired
	private TasksRepository tasksRepository;

	public void deleteTask(int id) throws SQLException {
		
		tasksRepository.delete(id);
	}
	
	public void addTask(Task t,int userid, int categoryid) throws SQLException {
		
		TaskDto taskDto = new TaskDto();
		
		taskDto.title = t.getTittle();
		taskDto.userId = t.getUser_id();
		
	    tasksRepository.add(taskDto);
		
	}

}
