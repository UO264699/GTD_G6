package com.capgemini.validators;

import org.springframework.stereotype.Component;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.capgemini.model.User;

@Component
public class UserValidators implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}
	
	
	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		
		ValidationUtils.rejectIfEmpty(errors, "login", "user.login", "El campo nombre no puede estar vacio");
		ValidationUtils.rejectIfEmpty(errors, "email", "user.email", "El campo email no puede estar vacio");
		ValidationUtils.rejectIfEmpty(errors, "password", "user.password", "El campo password no puede estar vacio");
	}
}
