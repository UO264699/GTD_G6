package com.capgemini;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



import com.capgemini.services.UsersService;

@Controller
public class UsersController {

	public UsersController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private UsersService usersService;
	
	@RequestMapping(value = "/users/list")
	public String getUsers(Model model) throws SQLException {
		
		model.addAttribute("users",usersService.getUsers());
		
		return "users";
		
	}
	
	@RequestMapping(value = "users/delete/{id}")
	public String deleteUser(@PathVariable int id) throws SQLException {
		
		usersService.deleteUser(id);
		
		
		return "redirect:/users/list";
		
	}

}
