package com.capgemini.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.Task;
import com.capgemini.model.User;
import com.capgemini.persistence.TasksRepository;
import com.capgemini.persistence.dto.TaskDto;
import com.capgemini.persistence.dto.UserDto;

@Service
public class TasksService {
	
	@Autowired
	private TasksRepository tasksRepository;

	public void deleteTask(int id) throws SQLException {
		
		tasksRepository.delete(id);
	}
	
	public void addTask(Task t,int userid, int categoryid) throws SQLException {
		
		TaskDto taskDto = new TaskDto();
		
		taskDto.id = t.getId();
		taskDto.title = t.getTitle();
		taskDto.userId = t.getUser_id();
		taskDto.categoryId = categoryid;
		taskDto.userId = userid;
		
	    tasksRepository.add(taskDto);
		
	}
	
	public List<Task> listTasks() throws SQLException {
		
		List<Task> tasks = new ArrayList<Task>();
		List<Object> tasks1 = this.tasksRepository.findAll();
		
		for(Object t : tasks1) {
			
			TaskDto tdto = (TaskDto) t;
			
			
			Task task = new Task(tdto.id, tdto.title,tdto.comments,tdto.created,tdto.planned,tdto.finished,tdto.userId,tdto.categoryId);
			
			tasks.add(task);
		}
	
		
		return tasks;
	}

}
