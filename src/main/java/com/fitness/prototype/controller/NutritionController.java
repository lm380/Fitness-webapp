package com.fitness.prototype.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fitness.prototype.entity.Client;
import com.fitness.prototype.service.ClientService;
import com.fitness.prototype.service.CustomUserDetails;
import com.fitness.prototype.service.NutritionService;

@Controller
@RequestMapping("/nutrition")
public class NutritionController {

	private final NutritionService nutritionService;

	private ClientService clientService;

	public NutritionController(NutritionService nutritionService, ClientService clientService) {
		this.nutritionService = nutritionService;
		this.clientService = clientService;
	}

	@RequestMapping("/showForm")
	public String showForm(@AuthenticationPrincipal CustomUserDetails clientDetails, Model model) {
		Client client = clientService.findClientByUsername(clientDetails.getClient().getUsername());
		model.addAttribute("client", client);
		return "nutritionForm";
	}

	@RequestMapping("/processForm")
	public String processForm(@Valid @ModelAttribute("client") Client client, BindingResult bindingResult) {
		if (bindingResult.hasFieldErrors("height") || bindingResult.hasFieldErrors("weight")) {
			return "nutritionForm";
		}
		int calories = (int) nutritionService.calculateCalories(client.getGender(), client.getAge(),
				client.getWeight(), client.getHeight(), client.getActivityLevel(), client.getNutritionGoal());
		client = nutritionService.setNutritionInfo(client, calories);
		clientService.update(client);
		return "redirect:/";
	}

	@RequestMapping("/targets")
	public String showTargets(@AuthenticationPrincipal CustomUserDetails clientDetails, Model model) {
		Client client = clientService.findClientByUsername(clientDetails.getClient().getUsername());
		model.addAttribute("theClient",client);
		return "nutritionInfo";
	}
}
