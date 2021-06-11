package com.shin.demotypescript.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * UserDTO
 * 
 * Version 1.0
 * 
 * Date 01-6-2021
 * 
 * Copyright
 * 
 * Modification Logs: 
 * DATE        AUTHOR    DESCRIPTION
 * ----------------------------------- 
 * 01-6-2021  TrangNTT46    Create
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class UserDTO {
	private long studentID;
	private String username;
	private String password;
	private String role;
	
	/**
	 * initial userDTO
	 * @param studentID
	 * @param username
	 * @param password
	 * @param role
	 */
	public UserDTO (long studentID, String username, String password, Role role) {
		this.studentID = studentID;
		this.username = username;
		this.password = password;
		this.role = role.getName();
	}
}
