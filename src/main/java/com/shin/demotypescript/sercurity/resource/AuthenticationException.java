package com.shin.demotypescript.sercurity.resource;

/**
 * AuthenticationException
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
public class AuthenticationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * define authentication exception
     * @param message
     * @param cause
     */
    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
