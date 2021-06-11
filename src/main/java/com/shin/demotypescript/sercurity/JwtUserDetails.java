package com.shin.demotypescript.sercurity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * JwtUserDetails
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
public class JwtUserDetails implements UserDetails {

  private static final long serialVersionUID = 5155720064139820502L;

  /**
   * id of user login 
   */
  private final Long id;
  
  /**
   * user name of user login
   */
  private final String username;
  
  /**
   * password when user login
   */
  private final String password;
  
  /**
   * role of user login
   */
  private final String role;
  
  /**
   * collection of authorities
   */
  private final Collection<? extends GrantedAuthority> authorities;

  /**
   * initial JwtUserDetails
   * @param id
   * @param username
   * @param password
   * @param role
   */
  public JwtUserDetails(Long id, String username, String password, String role) {
    this.id = id;
    this.username = username;
    this.password = password;

    List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
    authorities.add(new SimpleGrantedAuthority(role));

    this.authorities = authorities;
    this.role = role;
  }

  /**
   * get id
   * @return id
   */
  @JsonIgnore
  public Long getId() {
    return id;
  }

  /**
   * get user name
   * @return user name
   */
  @Override
  public String getUsername() {
    return username;
  }
  
  /**
   * get role
   * @return role
   */
  @JsonIgnore
  public String getRole() {
	  return role;
  }

  /**
   * check account non expired
   * @return boolean
   */
  @JsonIgnore
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  /**
   * check account not locked
   * @return boolean
   */
  @JsonIgnore
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  /**
   * check credentials non expired
   * @return boolean
   */
  @JsonIgnore
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  /**
   * get password
   * @return password
   */
  @JsonIgnore
  @Override
  public String getPassword() {
    return password;
  }

  /**
   * get authorities
   * @return authorities
   */
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  /**
   * check enabled
   */
  @Override
  public boolean isEnabled() {
    return true;
  }
}