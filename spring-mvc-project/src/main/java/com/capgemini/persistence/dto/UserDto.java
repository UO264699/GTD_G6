package com.capgemini.persistence.dto;

import java.util.List;

import com.capgemini.model.Category;
import com.capgemini.model.Task;



public class UserDto {

	public int id;
	public String login;
	public String email;
	public String password;
	public String confirmPassword;
	public boolean isAdmin;
	public String status;
	public List<Task> tasks;
	public List<Category> categories;
	
	

}
