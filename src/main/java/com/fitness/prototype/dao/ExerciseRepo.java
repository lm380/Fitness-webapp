package com.fitness.prototype.dao;

import com.fitness.prototype.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitness.prototype.entity.Exercises;

import java.util.List;

@Repository
public interface ExerciseRepo extends JpaRepository<Exercises, Long> {

    List<Exercises> findByCreator(Client creator);

}
