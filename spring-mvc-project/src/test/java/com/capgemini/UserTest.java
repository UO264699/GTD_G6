package com.capgemini;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.capgemini.persistence.UsersRepository;
import com.capgemini.persistence.dto.UserDto;

class UserTest {
	

	private UsersRepository usersRepository = new UsersRepository();
	
	public UserTest() {
		// TODO Auto-generated constructor stub
	}

	@AfterEach
	void tearDown() throws Exception {
		
		
		
	}

	@Test
	void testAddUser() {
		

		UserDto userDto = new UserDto();
		
		userDto.isAdmin = true;
		userDto.email = "admin@admin.com";
		userDto.login = "admin";
		userDto.password = "admin";
		userDto.status = "ENABLED";
		
		usersRepository.add(userDto);
		
		List<Object> users = usersRepository.findAll();
	
		
		assertEquals(userDto.login, ((UserDto)users.get(users.size()-1)).login);
		
	}
	
	@Test
	void updateUser() {
		

		UserDto userDto = usersRepository.findByLogin("admin");
		
		usersRepository.updateStatus(userDto.id);
		
		userDto = usersRepository.findByLogin("admin");
		
		assertEquals("DISABLED",userDto.status );
		
		usersRepository.updateStatus(userDto.id);
		
		userDto = usersRepository.findByLogin("admin");
		
		assertEquals("ENABLED",userDto.status );
		
	}
	
	@Test
	void testDeleteUser() {
		

		UserDto userDto = usersRepository.findByLogin("admin");
		
		usersRepository.delete(userDto.id);
		
		List<Object> users = usersRepository.findAll();
	
		
		assertNotEquals(userDto.login, ((UserDto)users.get(users.size()-1)).login);
		
	}
	
	
	

}
