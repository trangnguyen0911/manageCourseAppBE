package com.shin.demotypescript.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;

import com.shin.demotypescript.model.Course;

/**
 * CourseRepository
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
public interface CourseRepository extends JpaRepository<Course, Long> {
	/**
	 * search list of courses
	 * @param contentSearch
	 * @return List<Course>
	 */
	@Query(value = "SELECT * FROM course " + "WHERE status = 1 " + "AND (course_name LIKE %:contentSearch% "
			+ "OR description LIKE %:contentSearch% " + "OR instructor_email LIKE %:contentSearch% "
			+ "OR course_name LIKE %:contentSearch% " + "OR CAST(duration AS char) LIKE %:contentSearch% "
			+ "OR CAST(fee AS char) LIKE %:contentSearch%)", nativeQuery = true)
	public List<Course> findSearchedCourses(@Param("contentSearch") String contentSearch);

	/**
	 * find all active courses
	 * @return List<Course>
	 */
	@Query(value = "SELECT * FROM course WHERE status = 1 ORDER BY course_id DESC", nativeQuery = true)
	public List<Course> findActiveCourses();
	
	/**
	 * sort list of active courses
	 * @param status
	 * @param sort
	 * @return List<Course>
	 */
	public List<Course> findByStatus(int status, Sort sort);

	/**
	 * find course by name and status
	 * @param courseName
	 * @param status
	 * @return Course
	 */
	public Course findByCourseNameIgnoreCaseAndStatus(String courseName, int status);

}
