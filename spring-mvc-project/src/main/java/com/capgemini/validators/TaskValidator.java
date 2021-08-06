package com.capgemini.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.capgemini.model.Task;
import com.capgemini.model.User;
import com.capgemini.services.TasksService;

@Component
public class TaskValidator implements Validator {

	@Autowired
	TasksService taskService;
	
	public boolean supports(Class<?> clazz) {
		return Task.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		Task task = (Task) target;
		
		if(task.getUser_id() == (taskService.getTaskById(task.getId()).getUser_id())) {
			if(task.getTitle().equals(taskService.getTaskById(task.getId()).getTitle())) {
				errors.rejectValue("title", "task.title", "Ya hay una task con ese titulo");
			}
		}
		
	}
}
