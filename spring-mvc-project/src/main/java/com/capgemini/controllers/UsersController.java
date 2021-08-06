package com.capgemini.controllers;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import com.capgemini.model.User;
import com.capgemini.model.UserStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.capgemini.services.CategoriesService;
import com.capgemini.services.TasksService;
import com.capgemini.services.UsersService;
import com.capgemini.validators.UserValidators;

@Controller
public class UsersController {

	@Autowired
	private UserValidators userValidator;

	@Autowired
	private UsersService usersService;

	@Autowired
	private TasksService tasksService;

	@Autowired
	private CategoriesService categoriesService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(userValidator);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcomePage() {
		return "redirect:/login";
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}
	

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		
		session.setAttribute("user", null);
		
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String authenticate(User user,HttpSession session) {
		
		User user2 = usersService.getUserByLogin(user.getLogin());
	
		
		

		System.out.println(user.getLogin());
		System.out.println(user2.getLogin());

		
		if(user.getLogin().equals(user2.getLogin())) {
			if(user.getPassword().equals(user2.getPassword())) {
				session.setAttribute("user", user2);
				return "redirect:/users/list";
			} else {
				System.out.println("TONTISIMO");
				return "redirect:/login";
			}
		} else {
			System.out.println("TONTO");
			return "redirect:/login";
		}
		

		
	
	}

	@RequestMapping(value = "/users/list")
	public String getUsers(Model model, HttpSession httpSession) throws SQLException {
		
		
		User user = (User) httpSession.getAttribute("user");
		
		if(user == null) {
			
			return "redirect:/login";
		}
		else if(!user.isIsAdmin()) {
			
			return "redirect:/tasks/list";
		}

		model.addAttribute("users", usersService.getUsers());

		return "usersList";

	}

	@RequestMapping(value = "users/delete/{id}")
	public String deleteUser(@PathVariable int id,HttpSession httpSession) throws SQLException {
		

		User user = (User) httpSession.getAttribute("user");
		
		if(user == null) {
			
			return "redirect:/login";
		}
		else if(!user.isIsAdmin()) {
			
			return "redirect:/tasks/list";
		}


		usersService.deleteUser(id);
		tasksService.deleteTask(id);
		categoriesService.deleteCategory(id);

		return "redirect:/users/list";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String addNewUsers(@Validated User user, BindingResult result, Model model) throws SQLException {

		if (result.hasErrors()) {																																									
			return "register";
		}
		
		user.setStatus(UserStatus.ENABLED);
		usersService.createNewUser(user);

		return "redirect:/";
	}

	@RequestMapping(value = "users/changeStatus/{id}")
	public String changeStatus(@PathVariable int id,HttpSession httpSession) throws SQLException {
		

		User user = (User) httpSession.getAttribute("user");
		
		if(user == null) {
			
			return "redirect:/login";
		}
		else if(!user.isIsAdmin()) {
			
			return "redirect:/tasks/list";
		}


		usersService.changeStatus(id);

		return "redirect:/users/list";

	}



}
