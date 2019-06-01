package com.fitness.prototype.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.fitness.prototype.dao.ClientRepo;


public class UniqueUsernameValidator
	implements ConstraintValidator<UniqueUsername,String> {

	@Autowired
	private ClientRepo clientRepo;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if(clientRepo==null) {
			return true;
		}
		if(value == "") {
			return true;
		}
		
		return ((value!=null && clientRepo.findClientByUsername(value.substring(0, 1).toUpperCase()
						+ value.substring(1).toLowerCase())==null));
	}

}
