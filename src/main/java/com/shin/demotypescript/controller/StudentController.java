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

import com.shin.demotypescript.model.Student;
import com.shin.demotypescript.service.impl.StudentServiceImpl;

/**
 * StudentController
 * 
 * Version 1.0
 * 
 * Date 01-6-2021
 * 
 * Copyright
 * 
 * Modification Logs: 
 * DATE        AUTHOR     DESCRIPTION
 * ----------------------------------- 
 * 01-6-2021  TrangNTT46    Create
 */
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/users")
public class StudentController {
    @Autowired
    private StudentServiceImpl service;

    /**
    * controller to get list of students
    * @return List<Student>
    */
    @GetMapping("/all")
    public List<Student> getAllStudents(){
	return service.getAllStudents();
    }
	
    /**
    * controller to get student by user name
    * @param username
    * @return student
    */
    @GetMapping("/{username}")
    public Student getStudentByUsername(@PathVariable String username){
        return service.getStudentByUsername(username);
    }

    /**
    * controller to search list of students
    * @param contentSearch
    * @return List<Student>
    */
    @GetMapping("/search/{contentSearch}")
    public List<Student> getSearchedStudents(@PathVariable String contentSearch){
	return service.getFilterStudent(contentSearch);
    }
	
    /**
    * controller to add new student
    * @param student
    * @param result
    * @return ResponseEntity<String>
    */
    @PostMapping("/add")
    public ResponseEntity<String> saveStudent(@RequestBody @Validated Student student, BindingResult result){	
        if (result.hasErrors()) {
	    String errorMessage = "";
	    for (ObjectError objectError : result.getAllErrors()) {
	        errorMessage += objectError.getDefaultMessage() + ". ";	
	    }
	    return new ResponseEntity<>(errorMessage, HttpStatus.OK);
	}
		
        String status = service.saveStudent(student);
		
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
	
    /**
    * controller to update a student
    * @param username
    * @param student
    * @param result
    * @return ResponseEntity<String>
    */
    @PostMapping("/{username}/edit")
    public ResponseEntity<String> updateStudent(@PathVariable String username, @RequestBody @Validated Student student, BindingResult result){		
        if (result.hasErrors()) {
            String errorMessage = "";
            for (ObjectError objectError : result.getAllErrors()) {
                errorMessage += objectError.getDefaultMessage() + ". ";	
            }
            return new ResponseEntity<>(errorMessage, HttpStatus.OK);
        }
		
        String status = service.updateStudent(username, student);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
