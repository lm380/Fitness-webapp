package com.fitness.prototype.service;

import com.fitness.prototype.dao.ExerciseRepo;
import com.fitness.prototype.entity.Client;
import com.fitness.prototype.entity.Exercises;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExerciseService {

	private final ExerciseRepo exerciseRepo;

	public ExerciseService(ExerciseRepo exerciseRepo) {
		this.exerciseRepo = exerciseRepo;
	}

	public List<Exercises> findAll() {
			List<Exercises> exercises = exerciseRepo.findAll();
			return exercises;
		}

	public List<Exercises> findByCreator(Client creator){
		return exerciseRepo.findByCreator(creator);
	}

	public void save(Exercises exercise) {
		exerciseRepo.save(exercise);
		
	}

	public void delete(Exercises exercise) {
		exerciseRepo.delete(exercise);
	}
}
