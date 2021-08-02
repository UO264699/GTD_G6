package com.capgemini.services;

import java.sql.SQLException;

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

}
