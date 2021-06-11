package com.shin.demotypescript.utils;

/**
 * ErrorCode
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
public class ErrorCode {
	// same name error
	public static final String SAME_NAME = "Err11";
	
	// same email error
	public static final String SAME_EMAIL = "Err12";
	
	// same user name error
	public static final String SAME_USER_NAME = "Err13";
	
	// not exist error
	public static final String IN_ACTIVE = "Err01";	
	
	// exist error
	public static final String EXIST_ACTIVE = "Err10";
	
	// no error
	public static final String SUCCESS = "NoErr";
	
	// token unable to get user name
	public static final String ILLEGAL_ARGUMENT_EXCEPTION = "JWT_TOKEN_UNABLE_TO_GET_USERNAME";
	
	//token expired error
	public static final String EXPIRED_JWT_EXCEPTION = "JWT_TOKEN_EXPIRED";
	
	// token not start with bearer string error
	public static final String WARNNING_JWT = "JWT_TOKEN_DOES_NOT_START_WITH_BEARER_STRING";
	
	// unauthoried error
	public static final String UNAUTHORIZED = "You would need to provide the Jwt Token to Access This resource";
	
}
