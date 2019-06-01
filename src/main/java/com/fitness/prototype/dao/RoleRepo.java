package com.fitness.prototype.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitness.prototype.entity.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

	Role findByRole(String string);

}
