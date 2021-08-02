package com.capgemini.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.User;
import com.capgemini.persistence.UsersRepository;
import com.capgemini.persistence.dto.UserDto;


@Service
public class UsersService {
	
	@Autowired
	private UsersRepository usersRepository;


	public UsersService() {
		// TODO Auto-generated constructor stub
	}

	public User createNewUser(User newUser) throws SQLException {
		usersRepository.add(newUser);

		return newUser;
	}
	
	
	public List<User> getUsers() throws SQLException{
		List<User> users = new ArrayList<User>();
		List<Object> users1 = usersRepository.findAll();
		
		for(Object u : users1) {
			
			UserDto udto = (UserDto) u;
			
			User user = new User(udto.id, udto.login, udto.email, udto.password, udto.isAdmin, udto.status,udto.tasks, udto.categories);
			
			users.add(user);
		}
	
		
		return users;
		
		
	}
	
	public void deleteUser(int id) throws SQLException {
		
		usersRepository.delete(id);
	}
	

}
