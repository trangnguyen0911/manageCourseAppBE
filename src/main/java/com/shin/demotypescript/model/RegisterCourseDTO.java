package com.shin.demotypescript.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * RegisterCourseDTO
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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RegisterCourseDTO {
    private Long courseID;
    priate Long studentID;
    private String username;
    privat String courseName;
    private String email;
    private String instructorEmail;
    private int duration;
    private String description;
    private LocalDate registerDate;
	
    /**
     * Show information of student, course, registered course
     */
    @Override
    public String toString() {
        return "RegisterCourseDTO [courseID=" + courseID + ", studentID=" + studentID + ", username=" + username
	        + ", courseName=" + courseName + ", instructorEmail=" + instructorEmail + ", duration=" + duration
                + ", description=" + description + ", registerDate=" + registerDate + "]";
    }	
}
