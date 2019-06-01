package com.fitness.prototype.service;

import com.fitness.prototype.dao.ClientRepo;
import com.fitness.prototype.dao.RoleRepo;
import com.fitness.prototype.entity.Client;
import com.fitness.prototype.entity.Exercises;
import com.fitness.prototype.entity.Role;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class AdminService {

	private final ClientRepo clientRepo;

	private final ExerciseService exerciseService;

	private final BCryptPasswordEncoder passwordEncoder;

	private final RoleRepo roleRepo;

	private final NutritionService nutritionService;

	public AdminService(ClientRepo clientRepo, ExerciseService exerciseService, BCryptPasswordEncoder passwordEncoder,
						RoleRepo roleRepo, NutritionService nutritionService) {
		this.clientRepo = clientRepo;
		this.exerciseService = exerciseService;
		this.passwordEncoder = passwordEncoder;
		this.roleRepo = roleRepo;
		this.nutritionService = nutritionService;
	}


	private void setExerciseService(Client theClient) {
		List<Exercises> exercises = exerciseService.findAll();
		List<Exercises>clientExerciseList = new ArrayList<>();
		List<Exercises>createdExerciseList = new ArrayList<>();
		exercises.stream().forEach((Exercises tempExercise) -> {
				if (tempExercise.getCreator().getId()==1) {
				clientExerciseList.add(tempExercise);
				}
				if (tempExercise.getCreator().getId() == theClient.getId()) {
					createdExerciseList.add(tempExercise);
				}

			});

		theClient.setExercisesUsed(clientExerciseList);
		theClient.setExercises(createdExerciseList);
	}
	
	public void update(Client theClient) {
		clientRepo.save(theClient);
	}
	
	public void save(Client theClient, String role) {
		setPasswordService(theClient);
		setRoleService(theClient, role);
		int calories =(int) nutritionService.calculateCalories(theClient.getGender(),
				 theClient.getAge(), theClient.getWeight(), theClient.getHeight(),
				 theClient.getActivityLevel(), theClient.getNutritionGoal());
		theClient = nutritionService.setNutritionInfo(theClient, calories);
		setExerciseService(theClient);
		setUsernameService(theClient);
		setEmailService(theClient);
		clientRepo.save(theClient);
	}
	
	public void setPasswordService(Client theClient) {
		String password = theClient.getPassword();
		String encPassword = passwordEncoder.encode(password);
		theClient.setPassword(encPassword);
	}
	
	public void setRoleService(Client theClient, String role) {
		Set<Role> roles = new HashSet<>();
		if(role.equals("CLIENT")) {
		roles.add(roleRepo.findByRole("CLIENT"));
		}else {
			roles.add(roleRepo.findByRole("ADMIN"));
		}
		theClient.setRoles(roles);
	}
	
	public void setEmailService(Client theClient) {
		String email = theClient.getEmail().toLowerCase();
		theClient.setEmail(email);
	}
	
	public void setUsernameService(Client theClient) {
		String username = theClient.getUsername();
		String formatUsername = username.substring(0, 1).toUpperCase() + username.substring(1).toLowerCase();
		theClient.setUsername(formatUsername);
	}
	
	public boolean isUsernameInUse(String username) {
		return (clientRepo.findClientByUsername(username) != null);
	}
	
	public Client findByEmail(String email) {
		return clientRepo.findByEmail(email);
	}

	public Client findClientByUsername(String username) {
		return clientRepo.findClientByUsername(username);
	}

	public List<Client> findAll() {
		return clientRepo.findAll();
	}

	public Optional<Client> findById(Long theId) {
		return clientRepo.findById(theId);
	}

	public void deleteById(Long clientId) {
		clientRepo.deleteById(clientId);
		
	}
	
	
	
	

}
