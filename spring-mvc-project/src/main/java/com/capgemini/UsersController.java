package com.capgemini;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.capgemini.services.UsersService;

@Controller
public class UsersController {

	public UsersController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private UsersService usersService;
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String getUsers(Model model) throws SQLException {
		
		model.addAttribute("users",usersService.getUsers());
		
		
		usersService.getUsers().forEach((p)-> {
			
			System.out.println(p.getEmail());
		});
		
		return "users";
		
	}

}
