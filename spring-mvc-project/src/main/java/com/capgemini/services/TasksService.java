package com.capgemini.services;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.persistence.TasksRepository;

@Service
public class TasksService {
	
	@Autowired
	private TasksRepository tasksRepository;

	public void deleteTask(int id) throws SQLException {
		
		tasksRepository.delete(id);
	}

}
