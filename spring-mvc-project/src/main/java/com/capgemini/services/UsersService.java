package com.capgemini.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.capgemini.model.User;
import com.capgemini.model.UserStatus;
import com.capgemini.persistence.CategoriesRepository;
import com.capgemini.persistence.TasksRepository;
import com.capgemini.persistence.UsersRepository;
import com.capgemini.persistence.dto.CategoryDto;
import com.capgemini.persistence.dto.UserDto;

/**
 * 
 * Clase que representa el servicio de Usuarios con todos los m�todos
 * relacionados con los usuarios.
 * 
 * 
 * @author andrefer
 *
 */
@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private CategoriesRepository categoriesRepository;
	
	@Autowired
	private TasksRepository tasksRepository;

	

	/**
	 * 
	 * M�todo que crea un nuevo usuario
	 *  
	 * @param newUser usuario a crear
	 * @return usuario creado
	 */
	public User createNewUser(User newUser)  {


		UserDto u = new UserDto();

		u.id = newUser.getId();
		u.email = newUser.getEmail();
		u.login = newUser.getLogin();
		u.isAdmin = newUser.isIsAdmin();
		u.password = newUser.getPassword();
		u.status = newUser.getStatus();

		
		usersRepository.add(u);
		
		u = usersRepository.findByLogin(u.login);
		
		CategoryDto category = new CategoryDto();
		
		category.name = "Inbox";
		category.user_id = u.id;
		
		categoriesRepository.add(category);

		return newUser;
	}

	/**
	 * 
	 * M�todo que tiene un listado de todos los usuarios del sistema.
	 * 
	 * 
	 * @return lista de todos los usuarios del sistema
	 */
	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		List<Object> users1 = usersRepository.findAll();

		for (Object u : users1) {

			UserDto udto = (UserDto) u;

			User user = new User(udto.id, udto.login, udto.email, udto.password, udto.isAdmin, getStatus(udto),
					udto.tasks, udto.categories);

			users.add(user);
		}
		
		return users;

	}
	/**
	 * 
	 * M�todo que elimina un usuario del sistema
	 * 
	 * 
	 * @param id id del usuario a eliminar
	 */
	public void deleteUser(int id)  {
		
		tasksRepository.delete(id);
		usersRepository.delete(id);
		categoriesRepository.delete(id);
		

	}

	
	/**
	 * 
	 * M�todo que actualiza el estado de un usuario.
	 * 
	 * @param id id del usuario a actualizar.
	 */
	public void changeStatus(int id)  {


		usersRepository.updateStatus(id);
	}

	public UserStatus getStatus(UserDto u) {
		
		if(u.status == null)
			
			return UserStatus.ENABLED;


		if (u.status.equals("DISABLED"))
			return UserStatus.DISABLED;
		else
			return UserStatus.ENABLED;
	}


	
	/**
	 * M�todo que devuelve el usuario cuyo nombre de usuario se pasa
	 * por par�metro
	 * 
	 * @param login nombre de usuario
	 * @return usuario 
	 */
	public User getUserByLogin(String login) {
		
			UserDto udto = usersRepository.findByLogin(login);

			User user = new User(udto.id, udto.login, udto.email, udto.password, udto.isAdmin, getStatus(udto),
					udto.tasks, udto.categories);
			
			user.setConfirmPassword("a");

			
		return user;
	}
	
	/**
	 * 
	 * M�todo que devuelve un usuario cuyo email se pasa  por par�metro
	 * 
	 * @param email email del usuario
	 * @return usuario 
	 */
	public User getUserByEmail(String email) {
		
		UserDto udto = usersRepository.findByEmail(email);

		User user = new User(udto.id, udto.login, udto.email, udto.password, udto.isAdmin, getStatus(udto),
				udto.tasks, udto.categories);
		
		user.setConfirmPassword("a");

		
	return user;
}
}
