package com.capgemini.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.User;
import com.capgemini.persistence.UsersRepository;


@Service
public class UsersService {
	
	@Autowired
	private UsersRepository usersRepository;


	public UsersService() {
		// TODO Auto-generated constructor stub
	}
	
	
	public List<User> getUsers() throws SQLException{
		

		List<User> users = new ArrayList<User>();
		List<Object> users1 = usersRepository.findAll();
		
		for(Object u : users1) {
			
			User user = (User) u;
			
			users.add(user);
		}
	
		
		return users;
		
		
	}
	
	

}
