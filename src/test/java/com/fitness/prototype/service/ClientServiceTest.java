package com.fitness.prototype.service;

import com.fitness.prototype.dao.ClientRepo;
import com.fitness.prototype.dao.RoleRepo;
import com.fitness.prototype.entity.Client;
import com.fitness.prototype.entity.Exercises;
import com.fitness.prototype.entity.Role;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;
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
        Client superUser = new Client();
        superUser.setId(1L);
        Client client = new Client();
        client.setId(2L);
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
        Client superUser = new Client();
        superUser.setId(1L);
        Client client = new Client();
        client.setId(2L);
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
    public void client_password_should_be_encoded(){
        Client client = new Client();
        String test = "test";
        client.setPassword(test);

        ClientService clientService = new ClientService(clientRepo,exerciseService,bCryptPasswordEncoder,roleRepo,nutritionService);

        clientService.setPasswordService(client);
        assertEquals(bCryptPasswordEncoder.encode(test), client.getPassword());
    }

    @Test
    public void clients_who_register_should_have_client_access_only(){
        Client client = new Client();
        ClientService clientService = new ClientService(clientRepo,exerciseService,bCryptPasswordEncoder,roleRepo,nutritionService);

        HashSet<Role> set = new HashSet<>();
        Role clientRole = new Role();
        set.add(clientRole);

        when(roleRepo.findByRole("CLIENT")).thenReturn(clientRole);

        clientService.setRoleService(client);
        assertEquals(set, client.getRoles());
    }

    @Test
    public void emails_should_be_saved_as_lower_case(){
        Client client = new Client();
        ClientService clientService = new ClientService(clientRepo,exerciseService,bCryptPasswordEncoder,roleRepo,nutritionService);

        String testEmail = "TeSt@eMail.cOm";
        client.setEmail(testEmail);

        clientService.setEmailService(client);

        assertEquals(testEmail.toLowerCase(),  client.getEmail());
    }

    @Test
    public void exception_should_be_thrown_if_username_is_less_than_2_characters() {
        Client client = new Client();
        ClientService clientService = new ClientService(clientRepo,exerciseService,bCryptPasswordEncoder,roleRepo,nutritionService);

        String testUsername = "";
        client.setUsername(testUsername);
        try {
            clientService.setUsernameService(client);
            fail("the method should've thrown an exception");
        }catch(Exception e) {
            assertEquals("username must be longer than 1 letter" , e.getMessage());
        }

    }

    @Test
    public void username_should_be_saved_with_uppercase_followed_by_lowercase_characters() {
        Client client = new Client();
        ClientService clientService = new ClientService(clientRepo,exerciseService,bCryptPasswordEncoder,roleRepo,nutritionService);

        String testUsername = "uSErnaMe";
        String expected = "Username";
        client.setUsername(testUsername);
        try {
            clientService.setUsernameService(client);
        }catch(Exception e) {
            e.printStackTrace();
        }
        assertEquals(expected, client.getUsername());
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