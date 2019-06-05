package com.fitness.prototype.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fitness.prototype.dao.ExerciseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.fitness.prototype.dao.ClientRepo;
import com.fitness.prototype.dao.RoleRepo;
import com.fitness.prototype.entity.Client;
import com.fitness.prototype.entity.Exercises;
import com.fitness.prototype.entity.Role;
@Service
public class ClientService {

	public static final long SUPER_USER_ID = 1L;
	private final ClientRepo clientRepo;

	private final ExerciseService exerciseService;

	private final BCryptPasswordEncoder passwordEncoder;

	private final RoleRepo roleRepo;

	private final NutritionService nutritionService;

	public ClientService(ClientRepo clientRepo, ExerciseService exerciseService, BCryptPasswordEncoder passwordEncoder, RoleRepo roleRepo, NutritionService nutritionService) {
		this.clientRepo = clientRepo;
		this.exerciseService = exerciseService;
		this.passwordEncoder = passwordEncoder;
		this.roleRepo = roleRepo;
		this.nutritionService = nutritionService;
	}

	public void setExerciseService(Client theClient) {
		List<Exercises>clientExerciseList = exerciseService.findByCreator(clientRepo.findById(SUPER_USER_ID).get());
		theClient.setExercisesUsed(clientExerciseList);
		if(theClient.getExercises()!=null && theClient.getId() != 1L) {
			List<Exercises> createdExerciseList = exerciseService.findByCreator(theClient);
			theClient.setExercises(createdExerciseList);
		}
		else{
			theClient.setExercises(new ArrayList<>());
		}

	}
	
	public void update(Client theClient) {
		clientRepo.save(theClient);
	}
	
	public void save(Client theClient) throws Exception {
		setPasswordService(theClient);
		setRoleService(theClient);
		int calories =(int) nutritionService.calculateCalories(theClient.getGender(),
				 theClient.getAge(), theClient.getWeight(), theClient.getHeight(),
				 theClient.getActivityLevel(), theClient.getNutritionGoal());
		theClient = nutritionService.setNutritionInfo(theClient, calories);
		setExerciseService(theClient);
		setUsernameService(theClient);
		setEmailService(theClient);
		clientRepo.save(theClient);
	}
	
	protected void setPasswordService(Client theClient) {
		String password = theClient.getPassword();
		String encPassword = passwordEncoder.encode(password);
		theClient.setPassword(encPassword);
	}
	
	protected void setRoleService(Client theClient) {
		Set<Role> roles = new HashSet<>();
		roles.add(roleRepo.findByRole("CLIENT"));
		theClient.setRoles(roles);
	}

	protected void setEmailService(Client theClient) {
		String email = theClient.getEmail().toLowerCase();
		theClient.setEmail(email);
	}
	
	protected void setUsernameService(Client theClient) throws Exception{
		String username = theClient.getUsername();
		if(username.length()<2){
			throw new Exception("username must be longer than 1 letter");
		}
			String formatUsername = username.substring(0, 1).toUpperCase() + username.substring(1).toLowerCase();
			theClient.setUsername(formatUsername);
	}

	public Client findClientByUsername(String username) {
		return clientRepo.findClientByUsername(username);
	}


}
