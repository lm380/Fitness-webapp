package com.fitness.prototype.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fitness.prototype.entity.Client;
import com.fitness.prototype.entity.Exercises;
import com.fitness.prototype.service.CustomUserDetails;
import com.fitness.prototype.service.ExerciseService;

@Controller
@RequestMapping("/exercise")
public class ExerciseController {
	

	private final ExerciseService exerciseService;

	public ExerciseController(ExerciseService exerciseService) {
		this.exerciseService = exerciseService;
	}

	@GetMapping("/exerciseFormForAdd")
	public String addExercise(Model model, @AuthenticationPrincipal CustomUserDetails clientDetails) {
		Client theClient = clientDetails.getClient();
		Exercises theExercise = new Exercises();
		theExercise.setCreator(theClient);
		model.addAttribute("theClient",theClient);
		model.addAttribute("newExercise", theExercise);
		return "exercise-form";
		
	}
	
	@PostMapping("/addExercise")
	public String saveExercise(@Valid @ModelAttribute("newExercise") Exercises exercise, BindingResult bindingResult,
			Model model) {
		if(bindingResult.hasErrors()) {
			return "exercise-form";
		}
		Client theClient = exercise.getCreator();
		if(theClient.getNumOfExercisesCreated()==10) {
			String message = "you've already added the maximum number of exercises";
			model.addAttribute("message",message);
			return "exercise-form";
		}
		
		theClient.addCreatedExercise(exercise);
		exerciseService.save(exercise);
			return "redirect:/client/currentRoutine";
	
		
	}
//	
//	@GetMapping("/deleteExercise")
//	public String deleteExercise(@RequestParam("id") int exerciseId) {
//
//		model.addAttribute("newExercise", theExercise);
//		return "exercise-form";
//		
//	}
}

