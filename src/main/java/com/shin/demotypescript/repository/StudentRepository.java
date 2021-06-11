package com.shin.demotypescript.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shin.demotypescript.model.Student;
import com.shin.demotypescript.model.UserDTO;

/**
 * StudentRepository
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
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	/**
	 * search list of students
	 * @param contentSearch
	 * @return List<Student>
	 */
	@Query(
			value = "SELECT * FROM student "
					+ "WHERE status = 1 "
					+ "AND fullname LIKE %:contentSearch% "
					+ "OR CAST(year_of_birth AS varchar) LIKE %:contentSearch%", nativeQuery = true)
	public List<Student> findSearchedStudents(@Param("contentSearch") String contentSearch);
	
	/**
	 * find active students
	 * @return List<Student
	 */
	@Query(
			value = "SELECT * FROM student "
					+ "WHERE status = 1", nativeQuery = true)
	public List<Student> findActiveStudents();
	
	/**
	 * find student by email and status
	 * @param email
	 * @param status
	 * @return Student
	 */
	public Student findByEmailIgnoreCaseAndStatus(String email, int status);
	
	/**
	 * find student by user name and status
	 * @param username
	 * @param status
	 * @return Student
	 */
	public Student findByUsernameIgnoreCaseAndStatus(String username, int status);
	
	/**
	 * find user information to log in
	 * @param username
	 * @return UserDTO
	 */
	@Query(value = "SELECT new com.shin.demotypescript.model.UserDTO (s.studentID, "
			+ "s.username, s.password, s.role) FROM Student s WHERE s.username = :username AND s.status = 1")
	public UserDTO findUserByUsername(@Param("username") String username);
}
