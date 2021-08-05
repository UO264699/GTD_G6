package com.capgemini.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.capgemini.model.User;
import com.capgemini.model.UserStatus;
import com.capgemini.persistence.UsersRepository;
import com.capgemini.persistence.dto.UserDto;

@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepository;
	private UsersRepository categoriesRepository;
	private UsersRepository tasksRepository;

	

	public User createNewUser(User newUser)  {


		UserDto u = new UserDto();

		u.id = newUser.getId();
		u.email = newUser.getEmail();
		u.login = newUser.getLogin();
		u.isAdmin = newUser.isIsAdmin();
		u.password = newUser.getPassword();
		u.status = "ENABLED";

		
			usersRepository.add(u);
		

		return newUser;
	}

	/**
	 * 
	 * Obtiene un listado de todos los usuarios del sistema.
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
	 * Elimina un usuario del sistema
	 * 
	 * 
	 * @param id id del usuario a eliminar
	 */
	public void deleteUser(int id)  {
		
		usersRepository.delete(id);
		categoriesRepository.delete(id);
		tasksRepository.delete(id);

	}

	
	/**
	 * 
	 * Actualiza el estado de un usuario.
	 * 
	 * @param id id del usuario a actualizar.
	 */
	public void changeStatus(int id)  {


		usersRepository.updateStatus(id);
	}

	public UserStatus getStatus(UserDto u) {

		if (u.status.equals("DISABLED"))
			return UserStatus.DISABLED;
		else
			return UserStatus.ENABLED;
	}

	
	public UserDto getUserById(int id) throws SQLException {
		
		UserDto user = usersRepository.findById(id);
		
		return user;
	}
}
