package com.capgemini;

import java.sql.SQLException;

import com.capgemini.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		
		return "users";
		
	}
	
	@RequestMapping(value = "users/delete/{id}")
	public String deleteUser(@PathVariable int id) throws SQLException {
		
		usersService.deleteUser(id);
		tasksService.deleteTask(id);
		categoriesService.deleteCategory(id);
		
		return "redirect:/users/list";
	}

	@RequestMapping(value = "users/add", method = RequestMethod.POST)
	public String addNewUsers(@RequestBody User user) throws SQLException {
		
		usersService.createNewUser(user);

		return "redirect:/users/list";
	}
	

	@RequestMapping(value = "users/changeStatus/{id}")
	public String changeStatus(@PathVariable int id) throws SQLException {
		
		usersService.changeStatus(id);
	
		return "redirect:/users/list";
		
	}
	
	

}
