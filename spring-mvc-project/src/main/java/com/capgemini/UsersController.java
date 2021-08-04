package com.capgemini;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import com.capgemini.services.CategoriesService;
import com.capgemini.services.TasksService;
import com.capgemini.services.UsersService;

@Controller
public class UsersController {

	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private TasksService tasksService;
	
	@Autowired
	private CategoriesService categoriesService;
	
	@RequestMapping(value = "/users/list")
	public String getUsers(Model model) throws SQLException {
		
		model.addAttribute("users",usersService.getUsers());
		
		return "usersList";
		
	}
	
	@RequestMapping(value = "users/delete/{id}")
	public String deleteUser(@PathVariable int id) throws SQLException {
		
		usersService.deleteUser(id);
		tasksService.deleteTask(id);
		categoriesService.deleteCategory(id);
		
		return "redirect:/users/list";
		
	}
	

	@RequestMapping(value = "users/changeStatus/{id}")
	public String changeStatus(@PathVariable int id) throws SQLException {
		
		usersService.changeStatus(id);
	
		return "redirect:/users/list";
		
	}
	
	

}
