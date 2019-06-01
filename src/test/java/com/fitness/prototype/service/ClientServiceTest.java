package com.fitness.prototype.service;

import com.fitness.prototype.dao.ClientRepo;
import com.fitness.prototype.dao.RoleRepo;
import com.fitness.prototype.entity.Client;
import com.fitness.prototype.entity.Exercises;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ClientServiceTest {

    @Mock
    private ClientRepo clientRepo;

    @Mock
    private ExerciseService exerciseService;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private RoleRepo roleRepo;

    @Mock
    private NutritionService nutritionService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void setExerciseService_Should_Not_Add_Created_Exercises_On_Sign_Up() {
        Client client = new Client();
        Client superUser = new Client();
        List<Exercises> createdExercises = new ArrayList<>();
        List<Exercises> usedExercises = new ArrayList<>();
        createdExercises.add(new Exercises());
        usedExercises.add(new Exercises());
        usedExercises.add(new Exercises());

        ClientService clientService = new ClientService(clientRepo,exerciseService,bCryptPasswordEncoder,roleRepo,nutritionService);

        when(clientRepo.findById(1L)).thenReturn(java.util.Optional.of(superUser));
        when(exerciseService.findByCreator(superUser)).thenReturn(usedExercises);
        when(exerciseService.findByCreator(client)).thenReturn(createdExercises);

        clientService.setExerciseService(client);
        assertEquals(2, (client.getExercises().size() + client.getExercisesUsed().size()));

    }

    @Test
    public void setExerciseService_Should_Add_Created_Exercises_After_They_Are_Created() {
        Client client = new Client();
        Client superUser = new Client();
        client.setExercises(new ArrayList<>());
        List<Exercises> createdExercises = new ArrayList<>();
        List<Exercises> usedExercises = new ArrayList<>();
        createdExercises.add(new Exercises());
        usedExercises.add(new Exercises());
        usedExercises.add(new Exercises());

        ClientService clientService = new ClientService(clientRepo,exerciseService,bCryptPasswordEncoder,roleRepo,nutritionService);

        when(clientRepo.findById(1L)).thenReturn(java.util.Optional.of(superUser));
        when(exerciseService.findByCreator(superUser)).thenReturn(usedExercises);
        when(exerciseService.findByCreator(client)).thenReturn(createdExercises);

        clientService.setExerciseService(client);
        assertEquals(3, (client.getExercises().size() + client.getExercisesUsed().size()));

    }

    @Test
    public void update() {
    }

    @Test
    public void save() {
    }

    @Test
    public void findClientByUsername() {
    }
}