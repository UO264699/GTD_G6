package com.capgemini.controllers;

import java.io.Console;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import com.capgemini.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	public String welcomePage(Model model) {
		model.addAttribute("user", new User()); // the Category object is used as a template to generate the form
		return "register";
	}
	

	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String authenticate(Model model, @RequestParam("login") String username,
			@RequestParam("password") String password, HttpSession session, BindingResult result) {
		
		if (result.hasErrors()) {																																									
			return "redirect:/login";
		}
		
		session.setAttribute("username", username);
		
		
		return "redirect:/users/list";
	}

	@RequestMapping(value = "/users/list")
	public String getUsers(Model model) throws SQLException {

		model.addAttribute("users", usersService.getUsers());

		return "usersList";

	}

	@RequestMapping(value = "users/delete/{id}")
	public String deleteUser(@PathVariable int id) throws SQLException {

		usersService.deleteUser(id);
		tasksService.deleteTask(id);
		categoriesService.deleteCategory(id);

		return "redirect:/users/list";
	}

	@RequestMapping(value = "users/add", method = RequestMethod.POST)
	public String addNewUsers(@Validated User user, BindingResult result, Model model) throws SQLException {

		if (result.hasErrors()) {																																									
			return "redirect:/";
		}

		usersService.createNewUser(user);

		return "redirect:/users/list";
	}

	@RequestMapping(value = "users/changeStatus/{id}")
	public String changeStatus(@PathVariable int id) throws SQLException {

		usersService.changeStatus(id);

		return "redirect:/users/list";

	}

	@RequestMapping(value = "users/{id}")
	public String getUserById(@PathVariable int id) throws SQLException {

		usersService.getUserById(id);

		return "redirect:/users/list";

	}

}
