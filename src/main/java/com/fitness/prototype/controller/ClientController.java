package com.fitness.prototype.controller;

import com.fitness.prototype.entity.Client;
import com.fitness.prototype.service.ClientService;
import com.fitness.prototype.service.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/client")
public class ClientController {


	private final ClientService clientService;

	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	@GetMapping("/updateWorkoutGoals")
	public String updateWorkoutGoals(@AuthenticationPrincipal CustomUserDetails clientDetails, Model model) {
		Client client = clientService.findClientByUsername(clientDetails.getClient().getUsername());
		model.addAttribute("client", client);
		return "workout-goal-form";
	}

	@PostMapping("/processWorkoutGoals")
	public String updateWorkoutGoals(@ModelAttribute("client") Client client) {
		if (client.getExercisesUsed() == null) {
			clientService.setExerciseService(client);
		}
		clientService.update(client);
		return "redirect:/";

	}

	@RequestMapping("/currentRoutine")
	public String showWorkoutRoutine(@AuthenticationPrincipal CustomUserDetails clientDetails, Model model) {
		Client theClient = clientService.findClientByUsername(clientDetails.getClient().getUsername());
		String targetMuscle = theClient.getTargetMuscle();
		model.addAttribute("theClient", theClient);
		model.addAttribute("targetMuscle", targetMuscle);
		if (theClient.getExercisesUsed().isEmpty()) {
			return "redirect:/client/updateWorkoutGoals";
		} else if (theClient.getGymGoal().equals("aesthetics")) {
			return "aesthetics-routine";
		} else {
			return "strength-routine";
		}
	}

}
