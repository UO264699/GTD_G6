package com.capgemini.controllers;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import com.capgemini.model.Category;
import com.capgemini.model.User;
import com.capgemini.services.CategoriesService;
import com.capgemini.services.TasksService;
import com.capgemini.validators.CategoriesValidator;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoriesService categoriesService;
	
	@Autowired
	private TasksService tasksService;
	
	@Autowired
	private CategoriesValidator categoriesValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(categoriesValidator);
	}

	/**
	 * 
	 * Método que añade una nueva categoría
	 * 
	 * @param category categoría a añadir
	 * @param result resultado de las validaciones
	 * @param httpSession sesión actual del usuario autenticado
	 * @return
	 */
	
	@RequestMapping(value = "/categories/add")
	public String addCategory(@Validated Category category, BindingResult result, HttpSession httpSession) {
		
		if (result.hasErrors()) {																																									
			return "redirect:/categories/list";
		}
		
		User user = (User) httpSession.getAttribute("user");
		
		if(user == null) {
			return "redirect:/login";
		}

		category.setUser_id(user.getId());
		
		categoriesService.addCategory(category);
		

		
		return "redirect:/categories/list";
		
	}
	
	/**
	 * 
	 * Método que borra una categoría
	 * 
	 * @param id id de la categoría a borrar
	 * @param httpSession sesión actual del usuario autenticado
	 * @return
	 */
	@RequestMapping(value = "/categories/delete/{id}")
	public String deleteCategory(@PathVariable int id, HttpSession httpSession)  {
		
		if(httpSession.getAttribute("user") == null) {
			
			return "redirect:/login";
		}

		
		categoriesService.deleteCategory(id);
		
		
		return "redirect:/categories/list";
		
	}
	/**
	 * 
	 * Método que devuelve la vista del listado de tareas de la categoría cuyo id
	 * se pasa por parámetro
	 * 
	 * @param model
	 * @param id id de la categoría de las tareas a listar
	 * @param httpSession sesión actual del usuario autenticado
	 * @return
	 */
	
	@RequestMapping(value = "/categories/{id}")
	public String taskCategories(Model model, @PathVariable int id, HttpSession httpSession) {
		
		User user = (User) httpSession.getAttribute("user");
		
		if(user == null) {
			
			return "redirect:/login";
		}

	
		int idUser = user.getId();
		
		model.addAttribute("tasksList",tasksService.listTaskByCategory(idUser, id));
		
		
		return "taskListCategories";
	}
	

	/**
	 * 
	 * Método que devuelve la vista del listado de categorías
	 * 
	 * @param model
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/categories/list")
	public String getCategories(Model model, HttpSession httpSession) {
		
		User user = (User) httpSession.getAttribute("user");
		
		if(httpSession.getAttribute("user") == null) {
			
			return "redirect:/login";
		}

		model.addAttribute("categoryEdit", new Category());
		model.addAttribute("category", new Category());
		model.addAttribute("categories",categoriesService.getCategories(user.getId()));
		
		return "categoriesList";
		
	}
	/**
	 * 
	 * Método que edita la categoría que se pasa por parámetro
	 * 
	 * @param category categoría editada
	 * @param result resultado de las validaciones
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/categories/edit")
	public String updateCategories(@Validated Category category, BindingResult result, Model model) {
		
		
		if (result.hasErrors()) {																																									
			return "redirect:/categories/list";
		}
		
		
		
		categoriesService.editCategorie(category);
		
		return "redirect:/categories/list";
	}

	
	
}
