package com.capgemini.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.persistence.CategoriesRepository;
import com.capgemini.persistence.TasksRepository;
import com.capgemini.persistence.dto.CategoryDto;


@Service
public class CategoriesService {

	@Autowired
	private CategoriesRepository categoriesRepository;
	
	@Autowired
	private TasksRepository tasksRepository;


	public void deleteCategoryByUserId(int id)  {
		categoriesRepository.deleteByUserId(id);
	}

	public List<Category> getCategories() {

		List<Object> categories = categoriesRepository.findAll();
		List<Category> categories1 = new ArrayList<>();

		for(Object u : categories) {
			Category category = (Category) u;

			Category category2 = new Category(category.getId(), category.getName(), category.getUser_id());

			categories1.add(category2);
		}
		return categories1;
	}
	
	public void addCategory(Category category)  {
		
		CategoryDto categoryDto = new CategoryDto();
		
		categoryDto.user_id = category.getId();
		categoryDto.name = category.getName();
		
		this.categoriesRepository.add(categoryDto);
		
	}
	
	public void deleteCategory(int id)  {
		this.tasksRepository.deleteByCategoryId(id);
		this.categoriesRepository.delete(id);
		
		
	}
	
	public void editCategorie(Category category) {	
		this.categoriesRepository.updateCategory(category.getId(), category.getName());
	}


}
