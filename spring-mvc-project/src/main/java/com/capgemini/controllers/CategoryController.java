package com.capgemini.controllers;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import com.capgemini.model.Category;
import com.capgemini.model.User;
import com.capgemini.services.CategoriesService;
import com.capgemini.services.TasksService;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoriesService categoriesService;
	
	@Autowired
	private TasksService tasksService;


	@RequestMapping(value = "/categories/add")
	public String addCategory(Category category, HttpSession httpSession) {

		User user = (User) httpSession.getAttribute("user");
		
		if(user == null) {
			
			return "redirect:/login";
		}

		category.setUser_id(user.getId());
		
		categoriesService.addCategory(category);
		
		
		return "redirect:/categories/list";
		
	}
	
	@RequestMapping(value = "/categories/delete/{id}")
	public String deleteCategory(@PathVariable int id, HttpSession httpSession)  {
		
		if(httpSession.getAttribute("user") == null) {
			
			return "redirect:/login";
		}

		
		categoriesService.deleteCategory(id);
		
		
		return "redirect:/categories/list";
		
	}
	
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
	

	
	@RequestMapping(value = "/categories/list")
	public String getCategories(Model model, HttpSession httpSession) {
		
		User user = (User) httpSession.getAttribute("user");
		
		if(httpSession.getAttribute("user") == null) {
			
			return "redirect:/login";
		}

		
		model.addAttribute("categories",categoriesService.getCategories(user.getId()));
		
		return "categoriesList";
		
	}
	
	@RequestMapping(value = "/categories/edit")
	public String updateCategories(Category category) {
		
		categoriesService.editCategorie(category);
		
		return "redirect:/categories/list";
	}

	
	
}
