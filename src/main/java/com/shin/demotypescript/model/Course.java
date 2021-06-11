package com.shin.demotypescript.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.shin.demotypescript.utils.ValidateMessage;

/**
 * Course
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
@Table(name="course")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id", nullable = false)
	private Long courseID;
	
	@Column(name = "course_name", length = 50, nullable = false)
	@NotBlank(message = ValidateMessage.NOT_BLANK +  "course name")
	@Length(max = 50, message = ValidateMessage.LENGTH_COURSE_NAME)
	private String courseName;
	
	@NotBlank(message = ValidateMessage.NOT_BLANK +  "description")
	@Column(name = "description", length = 200, nullable = false)
	@Length(max = 200, message = ValidateMessage.LENGTH_DESCRIPTION)
	private String description;
	
	@Email(message = ValidateMessage.EMAIL)
	@Column(name = "instructor_email", length = 100, nullable = false)
	@NotBlank(message = ValidateMessage.NOT_BLANK + "instructor's email")
	private String instructorEmail;
	
	@Column(name = "duration", nullable = false)
	@Min(value = 0L, message = "Duration" + ValidateMessage.POSITIVE_NUMBER)
	@Digits(integer = 10, fraction = 0, message = "Duration" + ValidateMessage.POSITIVE_NUMBER)
	@NotNull(message = ValidateMessage.NOT_BLANK + "duration")
	private int duration;
	
	@Column(name = "fee", nullable = false)
	@Min(value = 0L, message = "Fee" + ValidateMessage.POSITIVE_NUMBER)
	@Digits(integer = 10, fraction = 0, message = "Fee" + ValidateMessage.POSITIVE_NUMBER)
	@NotNull(message = ValidateMessage.NOT_BLANK + "fee")
	private int fee;
	
	@Column(name = "status", nullable = false)
	@Digits(integer = 10, fraction = 0)
	private int status;
	
	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<RegisterCourse> registerCourses;

	/**
	 * initial new course
	 * @param courseName
	 * @param description
	 * @param email
	 * @param duration
	 * @param fee
	 */
	public Course(String courseName, String description, String email, Integer duration, Integer fee) {
		super();
		this.courseName = courseName;
		this.description = description;
		this.instructorEmail = email;
		this.duration = duration;
		this.fee = fee;
		this.status = 1;
	}

	/**
	 * show course information
	 */
	@Override
	public String toString() {
		return "Course [courseName=" + courseName + ", description=" + description + ", instructorEmail="
				+ instructorEmail + ", duration=" + duration + ", fee=" + fee + "]";
	}
}

