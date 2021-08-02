package com.capgemini.services;

import com.capgemini.model.Category;
import com.capgemini.model.User;
import com.capgemini.persistence.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategories() throws SQLException {

        List<Object> categories = categoryRepository.findAll();

        for(Object u : categories) {

            Category category = (Category) u;

            System.out.println(category.id);


        }


        return null;


    }

}
