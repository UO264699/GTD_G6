package com.capgemini;



import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.capgemini.persistence.CategoriesRepository;
import com.capgemini.persistence.TasksRepository;
import com.capgemini.persistence.UsersRepository;
import com.capgemini.persistence.dto.CategoryDto;
import com.capgemini.persistence.dto.TaskDto;
import com.capgemini.persistence.dto.UserDto;

class TaskTest {
	
	private UsersRepository usersRepository = new UsersRepository();
	private TasksRepository tasksRepository = new TasksRepository();
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
		
		CategoryDto category = new CategoryDto();
		
		category.name = "Inbox";
		category.user_id = userDto.id;
		
		categoriesRepository.add(category);
		
		return userDto;
		
	}

	@Test
	void testAddTask() {
		
		UserDto u = addUser();
		
		TaskDto task = new TaskDto();
		
		
		task.title = "Tarea de prueba";
		task.userId = u.id;
		task.categoryId = categoriesRepository.findbyname("Inbox").id;
		
		
		tasksRepository.add(task);
		
		TaskDto t = (TaskDto) tasksRepository.findByUserId(u.id).get(0);
		
		assertEquals(task.title,t.title );
		
	    
	}
	
	
	@Test
	void testUpdateTask() {
		
		UserDto u = usersRepository.findByLogin("prueba");
		
		TaskDto task = (TaskDto) tasksRepository.findByUserId(u.id).get(0);
		
		
		task.title = "Tarea de prueba actualizada";
		task.comments = "Comentario de prueba";
		task.planned = new Date();
		
		tasksRepository.updateTask(task);
		
		TaskDto t = (TaskDto) tasksRepository.findByUserId(u.id).get(0);
		
		
		assertEquals(task.title,t.title );
		assertEquals(task.comments,t.comments );
		assertNotNull(t.planned);
	}
	
	@Test
	void testDeleteTask() {
		
		UserDto u = usersRepository.findByLogin("prueba");
		
		
		tasksRepository.delete(u.id);
		categoriesRepository.deleteByUserId(u.id);
		usersRepository.delete(u.id);
		
		List<Object> tasks= tasksRepository.findByUserId(u.id);
		
		assertEquals(0, tasks.size());
		
	}

}
