package com.capgemini.model;

import java.util.List;

public class User {

	private int id;
	private String login;
	private String email;
	private String password;
	private boolean isAdmin;
	private UserStatus status;
	private List<Task> tasks;
	private List<Category> categories;
	
	public User() {
		
	}

	public User(int id,String login, String email, String password, boolean isAdmin, UserStatus status, List<Task> tasks, List<Category> categories) {
		this.id = id;
		this.login = login;
		this.email = email;
		this.password = password;
		this.setIsAdmin(isAdmin);
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

	public 	String getLogin() {
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

	

	public String getStatus() {
		return status.toString();
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

	public boolean isIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	
	public String getAdmin() {
		if(isIsAdmin())
			return "Sí";
		else
			return "No";
	}

}
