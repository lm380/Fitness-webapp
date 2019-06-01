package com.fitness.prototype.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/showLogin")
	public String showLogin() {
		return "fancy-login";
	}
}
