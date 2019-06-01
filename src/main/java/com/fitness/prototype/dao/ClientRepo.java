package com.fitness.prototype.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitness.prototype.entity.Client;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {

	Client findByEmail(String email);

	Client findClientByUsername(String username);
	

	
}
