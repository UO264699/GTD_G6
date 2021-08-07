package com.capgemini.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.capgemini.model.User;
import com.capgemini.services.UsersService;

@Component
public class UserValidators implements Validator {

	@Autowired
	private UsersService userServices;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}
	
	
	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		
		System.out.println(user.getLogin());

		ValidationUtils.rejectIfEmpty(errors, "login", "user.login", "El campo nombre no puede estar vacio");
		ValidationUtils.rejectIfEmpty(errors, "email", "user.email", "El campo email no puede estar vacio");
		ValidationUtils.rejectIfEmpty(errors, "password", "user.password", "El campo password no puede estar vacio");
		ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "user.confirmPassword", "El campo repetir password no puede estar vacio");
	
		
		if(!user.getConfirmPassword().equals(user.getPassword())) {
			errors.rejectValue("confirmPassword", "user.confirmPassword", "La contraseña tiene que ser igual");
		}
		
		if(user.getLogin() != "") {
			if(userServices.getUserByLogin(user.getLogin()).getLogin() != null) {
				errors.rejectValue("login", "user.login", "Ya hay un usuario con ese userName");
			}
		}
		
		if(user.getEmail() != "") {
			if(userServices.getUserByEmail(user.getEmail()).getEmail() != null) {
				errors.rejectValue("email", "user.email", "Ya hay un usuario con ese email");
			}	
		}

		
	}
}
