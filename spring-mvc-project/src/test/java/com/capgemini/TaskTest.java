package com.capgemini;



import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.Date;
import java.util.List;


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

	
	/**
	 * Método que crea un nuevo usuario 
	 * 
	 * @return usuario creado
	 */
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

	/**
	 * 
	 * Añade una tarea y comprueba ha sido añadida.
	 */
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
	
	/**
	 * 
	 * Actualiza una tarea y comprueba que ha sido actualizada
	 * 
	 */
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
	
	
	/**
	 * 
	 * Marca una tarea como finalizada y comprueba que ha sido así
	 */
	@Test
	void testFinishTask() {
		
		UserDto u = usersRepository.findByLogin("prueba");
		
		
		TaskDto task= (TaskDto) tasksRepository.findByUserId(u.id).get(0);
		
		tasksRepository.updateFinished(task.id);
		
		task= (TaskDto) tasksRepository.findByUserId(u.id).get(0);
		
		assertNotNull(task.finished);
		
	}
	
	/**
	 * Borra una tarea y comprueba que ha sido borrada
	 */
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
