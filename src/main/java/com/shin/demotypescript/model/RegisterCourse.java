package com.shin.demotypescript.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * RegisterCourse
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
@Entity
@Table(name="register_course")
@Getter
@Setter
@NoArgsConstructor
@IdClass(RegisterCourseId.class)
public class RegisterCourse {	
    @Id
    @Column(name = "student_id")
    private Long studentID;

    @Id
    @Column(name = "course_id")
    private Long courseID;

    @ManyToOne 
    @JoinColumn(name="student_id", insertable = false, updatable = false)
    @JsonIgnore
    private Student student;

    @ManyToOne 
    @JoinColumn(name="course_id", insertable = false, updatable = false)
    @JsonIgnore
    private Course course;
	
    @Id
    @Column(name = "register_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate registerDate;
	
    @Id
    @Column(name = "register_time", nullable = false)
    @DateTimeFormat(pattern = "hh:mm:ss")
    @NotNull
    private LocalTime registerTime;
	
    @Column(name = "status", nullable = false)
    @Digits(integer = 10, fraction = 0)
    private int status;

    /**
     * student register a course
     * @param student
     * @param course
     */
    public RegisterCourse(Student student, Course course) {
        super();
        this.student = student;
        this.course = course;
        this.registerDate = LocalDate.now();
        this.registerTime = LocalTime.now();
        this.status = 1;
    }
	
    /**
     * student register a course
     * @param student
     * @param course
     * @param studentID
     * @param courseID
     */
    public RegisterCourse(Student student, Course course, long studentID, long courseID) {
        super();
        this.student = student;
        this.course = course;
        this.studentID = studentID;
        this.courseID = courseID;
        this.registerDate = LocalDate.now();
        this.registerTime = LocalTime.now();
        this.status = 1;
    }
	
    /**
     * student register a course
     * @param studentID
     * @param courseID
     */
    public RegisterCourse(long studentID, long courseID) {
        super();
        this.studentID = studentID;
        this.courseID = courseID;
        this.registerDate = LocalDate.now();
        this.registerTime = LocalTime.now();
        this.status = 1;
    }	
}
