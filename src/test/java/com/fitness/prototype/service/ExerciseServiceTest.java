package com.fitness.prototype.service;

import com.fitness.prototype.entity.Exercises;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ExerciseServiceTest {

    @Test
    public void findAll() {
        List<Exercises> exercises = new ArrayList<>();
        Exercises exercise = new Exercises();
        exercise.setExerciseName("test1");

    }

    @Test
    public void save() {
    }

    @Test
    public void delete() {
    }
}