package com.capgemini;

import java.io.Console;
import java.sql.SQLException;

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

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new UserValidators());
	}

	@Autowired
	private UsersService usersService;

	@Autowired
	private TasksService tasksService;

	@Autowired
	private CategoriesService categoriesService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcomePage(Model model) {
		model.addAttribute("user", new User()); // the Category object is used as a template to generate the form
		return "register";
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
