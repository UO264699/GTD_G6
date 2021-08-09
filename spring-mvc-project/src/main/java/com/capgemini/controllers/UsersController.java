package com.capgemini.controllers;

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

	
	/**
	 * M�todo que devuelve las vistas de la p�gina inicial
	 * 
	 * @return vistas de la p�gina inicial
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcomePage() {
		return "redirect:/login";
	}
	
	/**
	 * M�todo que devuelve las vistas del login.
	 * 
	 * @param model
	 * @return las vistas de la pantalla de login
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}
	
	/**
	 * M�todo que devuelve las vistas de la pantalla de registro
	 * 
	 * @param model las vistas de la pantalla de registro
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	
	/**
	 * M�todo que termina la sesi�n del usuario autenticado
	 * 
	 * @param session Sesi�n actual del usuario autenticado
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		
		session.setAttribute("user", null);
		
		return "redirect:/login";
	}
	
	/**
	 * 
	 * M�todo que autentica a un usuario.
	 * 
	 * @param user Usuario a autenticar
	 * @param session Sesi�n actual del usuario autenticado
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String authenticate(User user,HttpSession session) {
		
		User user2 = usersService.getUserByLogin(user.getLogin());
		
		System.out.println(user2.getStatus());
		if(user2.getStatus() == "ENABLED") {
			
			if(user.getLogin().equals(user2.getLogin())) {
				if(user.getPassword().equals(user2.getPassword())) {
					session.setAttribute("user", user2);
					return "redirect:/users/list";
				} else {
					return "redirect:/login";
				}
			} else {
				return "redirect:/login";
			}
		} else return "redirect:/login";
		
	}
	
    /**
     * M�todo que devuelve las vistas del listado de usuarios
     * 
     * @param model
     * @param httpSession sesi�n actual del usuario autenticado
     * @return las vistas del listado de usuarios
     */
	@RequestMapping(value = "/users/list")
	public String getUsers(Model model, HttpSession httpSession)  {
		
		
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
    
	/**
	 * 
	 * M�todo que borra el usuario cuyo id se pasa por par�metro en la URL
	 * 
	 * @param id id del usuario a borrar
	 * @param httpSession sesi�n actual del usuario autenticado
	 * @return las vistas del listado de usuario
	 */
	@RequestMapping(value = "users/delete/{id}")
	public String deleteUser(@PathVariable int id,HttpSession httpSession)  {
		

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

	/**
	 * 
	 * M�todo que registra un nuevo usuario en el sistema
	 * 
	 * @param user Usuario a registrar
	 * @param result Resultado de las validaciones
	 * @param model
	 * @return las vistas de la p�gina inicial del sistema
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String addNewUsers(@Validated User user, BindingResult result, Model model)  {

		if (result.hasErrors()) {																																									
			return "register";
		}
		
		user.setStatus(UserStatus.ENABLED);
		usersService.createNewUser(user);

		return "redirect:/";
	}

	/**
	 * 
	 * M�todo que modifica el estado del usuario cuyo id se pasa por
	 * par�metro en la url
	 * 
	 * @param id id del usuario a modificar
	 * @param httpSession sesi�n actual del usuario autenticado
	 * @return vistas del listado de usuarios
	 */
	@RequestMapping(value = "users/changeStatus/{id}")
	public String changeStatus(@PathVariable int id,HttpSession httpSession)  {
		

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
