package com.shin.demotypescript.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shin.demotypescript.model.Role;

/**
 * RoleController
 * 
 * Version 1.0
 * 
 * Date 01-6-2021
 * 
 * Copyright
 * 
 * Modification Logs: 
 * DATE        AUTHOR   DESCRIPTION
 * ----------------------------------- 
 * 01-6-2021 TrangNTT46 Create
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
	/**
	 * find role by name
	 * @param name
	 * @return Role
	 */
	Role findByName(String name);
}
