package com.shin.demotypescript.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shin.demotypescript.model.Role;
import com.shin.demotypescript.model.Student;
import com.shin.demotypescript.repository.RoleRepository;
import com.shin.demotypescript.repository.StudentRepository;
import com.shin.demotypescript.service.StudentService;
import com.shin.demotypescript.utils.ErrorCode;

/**
 * StudentServiceImpl
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
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository repo;
	
    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder bCryptPassword;

    /**
     * get all active students
     * @return List<Student> 
     */
    public List<Student> getAllStudents() {
        List<Student> students = repo.findActiveStudents();
	
        return students;
    }

    /**
     * search list of students
     * @param contentSearch
     * @return List<Student> 
     */
    public List<Student> getFilterStudent(String contentSearch) {
        List<Student> students = repo.findSearchedStudents(contentSearch);
	    
        return students;
    }

    /**
     * add new student
     * @param student
     * @return status of action
     */
    public String saveStudent(Student student) {
        Student existEmailStudent = repo.findByEmailIgnoreCaseAndStatus(student.getEmail(), 1);
        Student existUserNameStudent = repo.findByUsernameIgnoreCaseAndStatus(student.getUsername(), 1);

        if (existEmailStudent != null && existUserNameStudent != null) {
            // exist active student with same email and user name => not save
            return ErrorCode.SAME_EMAIL + ErrorCode.SAME_USER_NAME;
        } else if (existEmailStudent != null) {
            // exist active student with same email => not save
            return ErrorCode.SAME_EMAIL;
        } else if (existUserNameStudent != null) {
            return ErrorCode.SAME_USER_NAME;
        } else {
            // save success
            student.setPassword(bCryptPassword.encode(student.getPassword()));
            Role userRole = roleRepo.findByName("USER");
            student.setRole(userRole);
            repo.save(student);
            return ErrorCode.SUCCESS;
        }
    }

    /**
     * get student by id
     * @param id
     * @return Student
     */
    public Student getStudentById(long id) {
        return repo.findById(id).get();
    }

    /**
     * get student by user name
     * @param username
     * @return Student
     */
    public Student getStudentByUsername(String username) {
        return repo.findByUsernameIgnoreCaseAndStatus(username, 1);
    }

    /**
     * update student
     * @param username
     * @param student
     * @return status of action
     */
    public String updateStudent(String username, Student student) {
        // check active student
        Student existStudent = this.getStudentByUsername(username);

        if (existStudent == null || existStudent.getStatus() == 0) {
            // inactive student
            return ErrorCode.IN_ACTIVE;
        }

        Student existEmailStudent = repo.findByEmailIgnoreCaseAndStatus(student.getEmail(), 1);

        if (existEmailStudent != null && !existEmailStudent.getUsername().equalsIgnoreCase(username)) {
            // exist active student with same email => not save
            return ErrorCode.SAME_EMAIL;
        } else {
            existStudent.setFullname(student.getFullname());
            existStudent.setEmail(student.getEmail());
            existStudent.setBirthdate(student.getBirthdate());
            existStudent.setGender(student.getGender());
            repo.save(existStudent);

            return ErrorCode.SUCCESS;
        }
    }
}
