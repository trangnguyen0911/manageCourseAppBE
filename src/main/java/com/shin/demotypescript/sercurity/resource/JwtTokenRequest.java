package com.shin.demotypescript.sercurity.resource;

import java.io.Serializable;

/**
 * JwtTokenRequest
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
public class JwtTokenRequest implements Serializable {

	private static final long serialVersionUID = -5616176897013108345L;

	/**
	 * user name get from request
	 */
	private String username;
	
	/**
	 * password get from request
	 */
	private String password;

	/**
	 * initial JwtTokenRequest
	 */
	public JwtTokenRequest() {
		super();
	}

	/**
	 * initial JwtTokenRequest
	 * @param username
	 * @param password
	 */
	public JwtTokenRequest(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	/**
	 * get user name
	 * @return user name
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * set user name
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * get password
	 * @return password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * set password
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}