package com.shin.demotypescript.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shin.demotypescript.model.Course;
import com.shin.demotypescript.service.impl.CourseServiceImpl;

/**
 * CourseController
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
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/courses")
public class CourseController {
	@Autowired
	private CourseServiceImpl service;

	/**
	 * controller to get list of all courses
	 * 
	 * @return List<Course>
	 */
	@GetMapping("/all")
	public List<Course> getAllCourses() {
	    return service.getAllCourse();
	}

	/**
	 * controller to search courses
	 * 
	 * @param content search
	 * @return List<Course>
	 */
	@GetMapping("/search/{contentSearch}")
	public List<Course> getSearchedCourses(@PathVariable String contentSearch) {
	    return service.getFilterCourse(contentSearch);
	}

	/**
	 * controller sort courses
	 * 
	 * @param field, type
	 * @return List<Course>
	 */
	@GetMapping("/sort/{field}/{type}")
	public List<Course> getSortedCourses(@PathVariable String field, @PathVariable boolean type) {
	    return service.getSortedCourse(field, type);
	}

	/**
	 * controller to add new course
	 * 
	 * @param course
	 * @param result
	 * @return ResponseEntity<String>
	 */
	@PostMapping("/add")
	public ResponseEntity<String> saveCourse(@RequestBody @Validated Course course, BindingResult result) {
	    // in case invalid object, send error message to client
	    if (result.hasErrors()) {
	        String errorMessage = "";
	        for (ObjectError objectError : result.getAllErrors()) {
			    errorMessage += objectError.getDefaultMessage() + ". ";
			}
	        return new ResponseEntity<>(errorMessage, HttpStatus.OK);
		}

	    String status = service.saveCourse(course);
	    return new ResponseEntity<>(status, HttpStatus.OK);
	}

	/**
	 * controller to update course
	 * @param id
	 * @param course
	 * @param result
	 * @return ResponseEntity<String>
	 */
	@PostMapping("/course/edit/{id}")
	public ResponseEntity<String> updateCourse(@PathVariable long id, @RequestBody @Validated Course course,
			BindingResult result) {
		// in case invalid object, send error message to client
	    if (result.hasErrors()) {
	        String errorMessage = "";
	        for (ObjectError objectError : result.getAllErrors()) {
				errorMessage += objectError.getDefaultMessage() + ". ";
			    }
	        return new ResponseEntity<>(errorMessage, HttpStatus.OK);
		}

	    String status = service.updateCourse(id, course);

	    return new ResponseEntity<>(status, HttpStatus.OK);
	}

	/**
	 * controller to delete course
	 * @param id
	 * @return ResponseEntity<String>
	 */
	@PostMapping("/course/delete/{id}")
	public ResponseEntity<String> deleteCourse(@PathVariable long id) {
	    String status = service.deleteCourse(id);

	    return new ResponseEntity<>(status, HttpStatus.OK);
	}

	/**
	 * controller to confirm course is active or not
	 * @param id
	 * @return ResponseEntity<String>
	 */
	@PostMapping("/course/confirm/{id}")
	public ResponseEntity<String> confirmUpdateCourse(@PathVariable long id) {
	    String status = service.confirmUpdateCourse(id);

	    return new ResponseEntity<>(status, HttpStatus.OK);
	}
}
