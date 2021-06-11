package com.shin.demotypescript.sercurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shin.demotypescript.model.UserDTO;
import com.shin.demotypescript.repository.StudentRepository;

/**
 * JwtInMemoryUserDetailsService
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
@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {
    @Autowired 
    private StudentRepository studentRepo;

    /**
     * Load user by user name when login
     * @param username
     * @throws UsernameNotFoundException
     * @return UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user = studentRepo.findUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
        }

        return new JwtUserDetails(user.getStudentID(), user.getUsername(), user.getPassword(), user.getRole());
    }
}
