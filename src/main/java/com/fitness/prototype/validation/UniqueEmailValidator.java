package com.fitness.prototype.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.fitness.prototype.dao.ClientRepo;


public class UniqueEmailValidator
	implements ConstraintValidator<UniqueEmail,String> {

	@Autowired
	private ClientRepo clientRepo;



	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		//not sure why this works
		//i think it's to do with hibernate using the repo and flushing it before clientservice uses it?
		if(clientRepo==null) {
			return true;
		}

		return ((value!=null && clientRepo.findByEmail(value.toLowerCase())==null));
	}

}
