package com.shin.demotypescript.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shin.demotypescript.model.RegisterCourseDTO;
import com.shin.demotypescript.service.impl.RegisterCourseServiceImpl;

/**
 * RegisterCourseController
 * 
 * Version 1.0
 * 
 * Date 01-6-2021
 * 
 * Copyright
 * 
 * Modification Logs: 
 * DATE       AUTHOR      DESCRIPTION
 * ----------------------------------- 
 * 01-6-2021 TrangNTT46   Create
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/registercourse")
public class RegisterCourseController {
	@Autowired
	private RegisterCourseServiceImpl service;

	/**
	 * controller to get list of registered courses
	 * 
	 * @return List<RegisterCourseDTO>
	 */
	@GetMapping("/all")
	public List<RegisterCourseDTO> getAllCourses() {
	    return service.getAllRegisterCourse();
	}

	/**
	 * controller to sort list of registered courses
	 * 
	 * @param field
	 * @param type
	 * @return List<RegisterCourseDTO>
	 */
	@GetMapping("/sort/{field}/{type}")
	public List<RegisterCourseDTO> getAllSortedCourses(@PathVariable String field, @PathVariable boolean type) {
	    return service.getAllSortedRegisterCourse(field, type);
	}

	/**
	 * controller to search registered courses
	 * 
	 * @param contentSearch
	 * @return List<RegisterCourseDTO>
	 */
	@GetMapping("/search/{contentSearch}")
	public List<RegisterCourseDTO> getAllSearchedCourses(@PathVariable String contentSearch) {
	    return service.getAllFilterRegisterCourse(contentSearch);
	}

	/**
	 * controller to get list of registered courses by user name
	 * 
	 * @param username
	 * @return List<RegisterCourseDTO>
	 */
	@GetMapping("/{username}/courses")
	public List<RegisterCourseDTO> getAllCoursesByUsername(@PathVariable String username) {
	    return service.getRegisterCourseByUsername(username);
	}

	/**
	 * controller to search list of registered courses by user name and content
	 * search
	 * 
	 * @param contentSearch
	 * @param username
	 * @return List<RegisterCourseDTO>
	 */
	@GetMapping("/{username}/search/{contentSearch}")
	public List<RegisterCourseDTO> getSearchedCoursesByUsername(@PathVariable String contentSearch,
			@PathVariable String username) {
	    return service.getFilterRegisterCourseByUsername(username, contentSearch);
	}

	/**
	 * controller to sort registered course by user name, field, type
	 * 
	 * @param username
	 * @param field
	 * @param type
	 * @return List<RegisterCourseDTO>
	 */
	@GetMapping("/{username}/sort/{field}/{type}")
	public List<RegisterCourseDTO> getSortedCoursesByUsername(@PathVariable String username, @PathVariable String field,
			@PathVariable boolean type) {
	    return service.getSortedRegisterCourseByUsername(username, field, type);
	}

	/**
	 * controller to save a new register course
	 * 
	 * @param username
	 * @param courseID
	 * @return ResponseEntity<String>
	 */
	@PostMapping("/{username}/{courseID}")
	public ResponseEntity<String> saveCourse(@PathVariable String username, @PathVariable long courseID) {
	    String status = service.saveRegisterCourse(username, courseID);

	    return new ResponseEntity<>(status, HttpStatus.OK);

	}

	/**
	 * controller to delete a registered course
	 * 
	 * @param username
	 * @param dto
	 * @return ResponseEntity<String>
	 */
	@PostMapping("/{username}/cancel")
	public ResponseEntity<String> deleteRegisteredCourse(@PathVariable String username,
			@RequestBody RegisterCourseDTO dto) {
	    String status = service.deleteRegisterCourse(dto);

	    return new ResponseEntity<>(status, HttpStatus.OK);
	}
}
