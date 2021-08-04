package com.capgemini;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import com.capgemini.model.Category;

import com.capgemini.services.CategoriesService;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoriesService categoriesService;

	@RequestMapping(value = "categories/add")
	public String addCategory(Category category) throws SQLException {
		
		
		categoriesService.addCategory(category);
		
		
		return "redirect:/categories/list";
		
	}
	
	@RequestMapping(value = "categories/delete/{id}")
	public String deleteCategory(@PathVariable int id) throws SQLException {
		
		categoriesService.deleteCategory(id);
		
		
		return "redirect:/categories/list";
		
	}
	
	@RequestMapping(value = "/categories/list")
	public String getCategories(Model model) throws SQLException {
		
		model.addAttribute("categories",categoriesService.getCategories());
		
		return "categoriesList";
		
	}

}
