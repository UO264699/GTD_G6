package com.capgemini.model;

import java.util.List;

public class User {

	public int id;
	private String login;
	public String email;
	public String password;
	public Boolean isAdmin;
	public UserStatus status;
	public List<Task> tasks;
	public List<Category> categories;

	public User(int id,String login, String email, String password, Boolean isAdmin, UserStatus status, List<Task> tasks, List<Category> categories) {
		this.id = id;
		this.login = login;
		this.email = email;
		this.password = password;
		this.isAdmin = isAdmin;
		this.status = status;
		this.tasks = tasks;
		this.categories = categories;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getAdmin() {
		return isAdmin;
	}

	public void setAdmin(Boolean admin) {
		isAdmin = admin;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}


}
