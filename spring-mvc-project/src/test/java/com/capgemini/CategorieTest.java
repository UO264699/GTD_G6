package com.capgemini;


import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

import com.capgemini.persistence.CategoriesRepository;

import com.capgemini.persistence.UsersRepository;
import com.capgemini.persistence.dto.CategoryDto;
import com.capgemini.persistence.dto.UserDto;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class CategorieTest {
	private UsersRepository usersRepository = new UsersRepository();

	private CategoriesRepository categoriesRepository = new CategoriesRepository();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	
	UserDto addUser() {
		
		UserDto userDto = new UserDto();
		
		userDto.isAdmin = true;
		userDto.email = "prueba@prueba.com";
		userDto.login = "prueba";
		userDto.password = "prueba";
		userDto.status = "ENABLED";
		
		usersRepository.add(userDto);
		
		userDto = usersRepository.findByLogin(userDto.login);
		
		
		
		return userDto;
		
	}
	
	@Test
	void testEditCategorie() {
		
		UserDto u = usersRepository.findByLogin("prueba");
		
		CategoryDto category = (CategoryDto) categoriesRepository.findbyUserid(u.id).get(0);
		
		
		category.name = "Categoria de prueba actualizada";
		
		categoriesRepository.updateCategory(category.id,category.name);
		
		
		CategoryDto categoryDto = categoriesRepository.findbyid(category.id);
		
		assertEquals(category.name,categoryDto.name);
		

		
	}

	@Test
	void testAddCategory() {
		
		UserDto u = addUser();
		
		CategoryDto category = new CategoryDto();
		
		category.name = "Categoria de prueba";
		category.user_id = u.id;
		
		categoriesRepository.add(category);
		
		CategoryDto categoryDto = categoriesRepository.findbyname(category.name);
		
		assertEquals(category.name,categoryDto.name);
		
	    
	}
	
	
	@Test
	void testRemoveCategory() {
		
		UserDto u = usersRepository.findByLogin("prueba");
		
	
		categoriesRepository.deleteByUserId(u.id);
		usersRepository.delete(u.id);
		
		List<Object> categories = categoriesRepository.findbyUserid(u.id);
		
		
		
		assertEquals(0,categories.size());
		
	}
}
