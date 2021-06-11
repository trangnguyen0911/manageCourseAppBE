package com.shin.demotypescript.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shin.demotypescript.utils.ValidateMessage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Student
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
@Table(name = "student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", nullable = false)
    private Long studentID;

    @NotBlank(message = ValidateMessage.NOT_BLANK +  "user name")
    @Column(name = "user_name", length = 20, nullable = false)
    @Length(max = 20, message = ValidateMessage.LENGTH_USER_NAME)
    private String username;

    @NotBlank(message = ValidateMessage.NOT_BLANK +  "student's name")
    @Column(name = "fullname", length = 50, nullable = false)
    @Length(max = 50, message = ValidateMessage.LENGTH_FULL_NAME)
    private String fullname;

    @Column(name = "email", length = 50, nullable = false)
    @Email(message = ValidateMessage.EMAIL)
    @NotBlank(message = ValidateMessage.NOT_BLANK +  "email")
    private String email;

    @Column(name = "birth_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate birthdate;

    @Column(name = "status", nullable = false)
    @Digits(integer = 10, fraction = 0)
    private int status;

    // Male: 0, Female: 1
    @Column(name = "gender", nullable = false)
    @Digits(integer = 3, fraction = 0)
    private int gender;

    @NotBlank(message = ValidateMessage.NOT_BLANK +  "password")
    @Column(name = "password", length = 200, nullable = false)
    private String password;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<RegisterCourse> registerCourses;
	
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    @JoinColumn(name = "role_id")
    private Role role;

    /**
     * initial a new student
     * @param username
     * @param fullname
     * @param birthDate
     * @param gender
     * @param password
     */
    public Student(String username, String fullname, LocalDate birthDate, int gender, String password) {
        super();
        this.username = username;
        this.fullname = fullname;
        this.birthdate = birthDate;
        this.gender = gender;
        this.password = password;
        this.status = 1;
    }

    /**
     * show information of student
     */
    @Override
    public String toString() {
        return "Student [studentID=" + studentID + ", username=" + username + ", fullname=" + fullname + ", email="
                + email + ", birthdate=" + birthdate + ", status=" + status + ", gender=" + gender + ", password="
                + password + ", registerCourses=" + registerCourses + ", roles=" + role + "]";
    }
}
