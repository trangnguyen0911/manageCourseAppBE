package com.shin.demotypescript.sercurity.resource;

import java.io.Serializable;

/**
 * JwtTokenResponse
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
 * 01-6-2021 TrangNTT46 Create
 */
public class JwtTokenResponse implements Serializable {
    private static final long serialVersionUID = 8317676219297719109L;

    /**
     * token send to client
     */
    private final String token;
	
    /**
     * role send to client
     */
    private final String role;

    /**
     * initial JwtTokenResponse
     * @param token
     * @param role
     */
    public JwtTokenResponse(String token, String role) {
        this.token = token;
        this.role = role;
    }

    /**
     * get token
     * @return token
     */
    public String getToken() {
        return this.token;
    }
	
    /**
     * get role
     * @return role
     */
    public String getRole() {
        return this.role;
    }
}
