package com.capgemini.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.capgemini.model.User;
import com.capgemini.services.UsersService;

@Component
public class LoginValidators {

	@Autowired
	private UsersService userServices;
	
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		User user = (User) target;
		
//		if(userServices.getUserByLogin(user.getLogin()).equals("")) {
//			errors.rejectValue("login", "user.login", "El usuario no esta registrador");
//		}

		
	}
}
