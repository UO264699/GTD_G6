package com.capgemini.validators;

import org.junit.experimental.categories.Categories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.capgemini.model.Category;

import com.capgemini.services.CategoriesService;

@Component
public class CategoriesValidator implements Validator {

	@Autowired
	private CategoriesService categoriesService;
	
	public boolean supports(Class<?> clazz) {
		return Category.class.equals(clazz);
	}
	
	public void validate(Object target, Errors errors) {
		Category categories = (Category) target;
		
		if(categories.getName() != "") {
			
			if(categories.getUser_id() == categoriesService.getCategoryByName(categories.getName()).getUser_id()) {
				if(categories.getName().equals(categoriesService.getCategoryByName(categories.getName()).getName())) {
					errors.rejectValue("name", "category.name", "Ya hay una Categoria con ese nombre");
				} 
			} 
		
		}
		

		
	}
}
