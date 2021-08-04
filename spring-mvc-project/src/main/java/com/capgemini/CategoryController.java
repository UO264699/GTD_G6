package com.capgemini;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.capgemini.model.Category;

import com.capgemini.services.CategoriesService;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoriesService categoriesService;

	@RequestMapping(value = "categories/add")
	public String addCategorie(Category category) throws SQLException {
		
		
		categoriesService.addCategory(category);
		
		
		return "redirect:/tasks/list";
		
	}

}
