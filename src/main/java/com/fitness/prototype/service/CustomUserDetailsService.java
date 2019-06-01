package com.fitness.prototype.service;

import com.fitness.prototype.dao.ClientRepo;
import com.fitness.prototype.entity.Client;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	private final ClientRepo clientrepo;

	public CustomUserDetailsService(ClientRepo clientrepo) {
		this.clientrepo = clientrepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String formatUsername = username.substring(0, 1).toUpperCase() + username.substring(1).toLowerCase();
		Client client = clientrepo.findClientByUsername(formatUsername);
		CustomUserDetails userDetails;
		if(client != null) {
			userDetails = new CustomUserDetails();
			userDetails.setClient(client);
		}
		else {
			throw new UsernameNotFoundException("User with "+ username+ " doesn't exist");
		}
		return userDetails;
	
	}

}
