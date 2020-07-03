package com.security.example.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.security.example.entity.User;
import com.security.example.repository.UserRepository;
import com.security.example.service.UserServiceImpl;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

	private int i = 0;
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return !userServiceImpl.checkIfEmailExist(value);
	}

}
