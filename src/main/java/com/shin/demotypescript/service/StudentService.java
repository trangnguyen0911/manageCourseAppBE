package com.shin.demotypescript.service;

import java.util.List;

import com.shin.demotypescript.model.Student;

/**
 * StudentService
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
 * 01-6-2021 TrangNTT46   Create
 */
public interface StudentService {
    /**
     * get all active students
     * 
     * @return List<Student>
     */
    public List<Student> getAllStudents();

    /**
     * search list of students
     * 
     * @param contentSearch
     * @return List<Student>
     */
    public List<Student> getFilterStudent(String contentSearch);

    /**
     * add new student
     * 
     * @param student
     * @return status of action
     */
    public String saveStudent(Student student);

    /**
     * get student by id
     * 
     * @param id
     * @return Student
     */
    public Student getStudentById(long id);

    /**    
     * get student by user name
     * 
     * @param username
     * @return Student
     */
    public Student getStudentByUsername(String username);

    /**
     * update student
     * 
     * @param username
     * @param student
     * @return status of action
     */
    public String updateStudent(String username, Student student);
}
