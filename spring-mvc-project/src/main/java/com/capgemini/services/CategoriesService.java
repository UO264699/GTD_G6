package com.capgemini.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.persistence.CategoriesRepository;


@Service
public class CategoriesService {

	@Autowired
	private CategoriesRepository categoriesRepository;
	

	public void deleteCategory(int id) throws SQLException {
		categoriesRepository.delete(id);
	}

	public List<Category> getCategories() throws SQLException {

		List<Object> categories = categoriesRepository.findAll();
		List<Category> categories1 = new ArrayList<>();

		for(Object u : categories) {
			Category category = (Category) u;

			Category category2 = new Category(category.id, category.name, category.user_id);

			categories1.add(category2);
		}
		return categories1;
	}

}
