package com.capgemini.persistence.dto;

import java.util.List;

import com.capgemini.model.Category;
import com.capgemini.model.Task;
import com.capgemini.model.UserStatus;

public class UserDto {

	public int id;
	public String login;
	public String email;
	public String password;
	public Boolean isAdmin;
	public UserStatus status;
	public List<Task> tasks;
	public List<Category> categories;

}
