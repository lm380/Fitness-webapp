package com.fitness.prototype.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fitness.prototype.entity.Client;
import com.fitness.prototype.service.ClientService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
	private final ClientService clientService;

	public RegistrationController(ClientService clientService) {
		this.clientService = clientService;
	}

	@GetMapping("/form")
	public String registration(Model model) {
		model.addAttribute("theClient", new Client());
		return "client-form";
	}

	@PostMapping("/formProcessing")
	public String registration(@Valid @ModelAttribute("theClient") Client theClient, BindingResult bindingResult) throws Exception {
		if (bindingResult.hasErrors()) {
			return "client-form";
		}

		clientService.save(theClient);
		return "redirect:/";
	}

}
