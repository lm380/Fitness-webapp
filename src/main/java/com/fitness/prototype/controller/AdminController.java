package com.fitness.prototype.controller;

import com.fitness.prototype.entity.Client;
import com.fitness.prototype.service.AdminService;
import com.fitness.prototype.service.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("/admin")
@Controller
public class AdminController {


	private final AdminService adminService;

	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	@GetMapping("/list")
	public String listCustomers(Model theModel) {

		List<Client> theClients = adminService.findAll();

		CustomUserDetails currentUserDetails = (CustomUserDetails) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		Client currentUser = currentUserDetails.getClient();

		theModel.addAttribute("clients", theClients);
		theModel.addAttribute("currentUser", currentUser);

		return "list-clients";

	}

	@GetMapping("/addClient")
	public String addClientByAdmin(Model model) {
		model.addAttribute("theClient", new Client());
		return "admin-save-form";
	}

	@PostMapping("/saveClient")
	public String addClientByAdmin(@Valid @ModelAttribute("theClient") Client theClient, BindingResult bindingResult,
			@RequestParam("role") String role) {
		if (bindingResult.hasErrors()) {
			return "admin-save-form";
		}
		adminService.save(theClient, role);
		return "redirect:/admin/list";
	}

	@GetMapping("/updateClient")
	public String showFormForUpdate(@RequestParam("clientId") Long theId, Model theModel) {

		Optional<Client> theOClient = adminService.findById(theId);

		Client theClient = theOClient.get();

		theModel.addAttribute("theClient", theClient);

		return "admin-update-form";
	}

	@PostMapping("/updateClient")
	public String updateClientByAdmin(@ModelAttribute("theClient") Client theClient,
			@RequestParam("role") String role) {

		adminService.setRoleService(theClient, role);
		adminService.update(theClient);
		return "redirect:/admin/list";
	}

	@GetMapping("/deleteClient")
	public String deleteClientByAdmin(@RequestParam("clientId") Long clientId) {
		adminService.deleteById(clientId);
		return "redirect:/admin/list";
	}
}
