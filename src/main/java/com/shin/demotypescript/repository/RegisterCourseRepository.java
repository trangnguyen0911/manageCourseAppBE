package com.shin.demotypescript.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;

import com.shin.demotypescript.model.RegisterCourse;
import com.shin.demotypescript.model.RegisterCourseDTO;
import com.shin.demotypescript.model.RegisterCourseId;

/**
 * RegisterCourseRepository
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
public interface RegisterCourseRepository extends JpaRepository<RegisterCourse, RegisterCourseId> {
    /**
     * find all registered courses
     * @return List<RegisterCourseDTO>
     */
    @Query(value = "SELECT new com.shin.demotypescript.model.RegisterCourseDTO (r.courseID, r.studentID, "
            + "s.username, c.courseName, s.email, c.instructorEmail, " + "c.duration, c.description, r.registerDate) "
            + "FROM RegisterCourse r " + "JOIN Student s ON r.studentID = s.studentID "
            + "JOIN Course c ON r.courseID = c.courseID " + "WHERE r.status = 1")
    public List<RegisterCourseDTO> findAllRegisterCourse();
	 
    /**
     * find all registered course and sort by register date
     * @param sort
     * @return List<RegisterCourseDTO>
     */
    @Query(value = "SELECT new com.shin.demotypescript.model.RegisterCourseDTO (r.courseID, r.studentID, "
            + "s.username, c.courseName, s.email, c.instructorEmail, " + "c.duration, c.description, r.registerDate) "
            + "FROM RegisterCourse r " + "JOIN Student s ON r.studentID = s.studentID "
            + "JOIN Course c ON r.courseID = c.courseID " + "WHERE r.status = 1")
    public List<RegisterCourseDTO> findAllSortedRegisterCourseByDate(Sort sort);
	
    /**
     * find all registered course and sort by user name
     * @return List<RegisterCourseDTO>
     */
    @Query(value = "SELECT new com.shin.demotypescript.model.RegisterCourseDTO (r.courseID, r.studentID, "
            + "s.username, c.courseName, s.email, c.instructorEmail, " + "c.duration, c.description, r.registerDate) "
            + "FROM RegisterCourse r " + "JOIN Student s ON r.studentID = s.studentID "
            + "JOIN Course c ON r.courseID = c.courseID " + "WHERE r.status = 1 ORDER BY s.username")
    public List<RegisterCourseDTO> findAllSortedRegisterCourseByUsername();

    /**
     * find all registered course by user name
     * @param username
     * @return List<RegisterCourseDTO>
     */
    @Query(value = "SELECT new com.shin.demotypescript.model.RegisterCourseDTO (r.courseID, r.studentID, "
            + "s.username, c.courseName, s.email, c.instructorEmail, " + "c.duration, c.description, r.registerDate) "
            + "FROM RegisterCourse r " + "JOIN Student s ON r.studentID = s.studentID "
            + "JOIN Course c ON r.courseID = c.courseID " + "WHERE s.username = :username " + "AND r.status = 1")
    public List<RegisterCourseDTO> findRegisterCourseByUsername(@Param("username") String username);
	
    /**
     * find all registered course by user name and sort by course name ASC
     * @param username
     * @return find all registered course by user name
     */
    @Query(value = "SELECT new com.shin.demotypescript.model.RegisterCourseDTO (r.courseID, r.studentID, "
            + "s.username, c.courseName, s.email, c.instructorEmail, " + "c.duration, c.description, r.registerDate) "
            + "FROM RegisterCourse r " + "JOIN Student s ON r.studentID = s.studentID "
            + "JOIN Course c ON r.courseID = c.courseID " + "WHERE s.username = :username " + "AND r.status = 1 ORDER BY c.courseName")
    public List<RegisterCourseDTO> findRegisterCourseByUsernameSortByCourseName(@Param("username") String username);
	
    /**
     * find registered course by user name and sort by instructor email ASC
     * @param username
     * @return List<RegisterCourseDTO>
     */
    @Query(value = "SELECT new com.shin.demotypescript.model.RegisterCourseDTO (r.courseID, r.studentID, "
            + "s.username, c.courseName, s.email, c.instructorEmail, " + "c.duration, c.description, r.registerDate) "
            + "FROM RegisterCourse r " + "JOIN Student s ON r.studentID = s.studentID "
            + "JOIN Course c ON r.courseID = c.courseID " + "WHERE s.username = :username " + "AND r.status = 1 ORDER BY c.instructorEmail")
    public List<RegisterCourseDTO> findRegisterCourseByUsernameSortByMentorEmail(@Param("username") String username);

    /**
     * search all registered courses
     * @param contentSearch
     * @return List<RegisterCourseDTO>
     */
    @Query(value = "SELECT DISTINCT new com.shin.demotypescript.model.RegisterCourseDTO (r.courseID, r.studentID, "
            + "s.username, c.courseName, s.email, c.instructorEmail, " + "c.duration, c.description, r.registerDate) "
            + "FROM RegisterCourse r " + "JOIN Student s ON r.studentID = s.studentID "
            + "JOIN Course c ON r.courseID = c.courseID " + "WHERE r.status = 1 "
            + "AND (s.username LIKE %:contentSearch% "
            + "OR c.courseName LIKE %:contentSearch% " 
            + "OR c.instructorEmail LIKE %:contentSearch% "
            + "OR c.description LIKE %:contentSearch% " + "OR CAST(c.duration AS string) LIKE %:contentSearch% "
            + "OR CAST(r.registerDate AS string) LIKE %:contentSearch%)")
    public List<RegisterCourseDTO> findAllSearchedRegisterCourse(String contentSearch);

    /**
     * search all registered courses by user name depend on content search
     * @param username
     * @param contentSearch
     * @return List<RegisterCourseDTO>
     */
    @Query(value = "SELECT DISTINCT new com.shin.demotypescript.model.RegisterCourseDTO (r.courseID, r.studentID, "
            + "s.username, c.courseName, s.email, c.instructorEmail, " + "c.duration, c.description, r.registerDate) "
            + "FROM RegisterCourse r " + "JOIN Student s ON r.studentID = s.studentID "
            + "JOIN Course c ON r.courseID = c.courseID " + "WHERE s.username = :username " + "AND r.status = 1 "
            + "AND (c.courseName LIKE %:contentSearch% " + "OR c.instructorEmail LIKE %:contentSearch% "
            + "OR c.description LIKE %:contentSearch% " + "OR CAST(c.duration AS string) LIKE %:contentSearch% "
            + "OR CAST(r.registerDate AS string) LIKE %:contentSearch%)")
    public List<RegisterCourseDTO> findSearchedRegisterCourseByUsername(@Param("username") String username,
            String contentSearch);

    /**
     * count active registered cours
     * @param courseID
     * @return integer
     */
    @Query(value = "SELECT count(*) FROM register_course " + "WHERE course_id = :courseID "
            + "AND status = 1", nativeQuery = true)
    public int countRegisterCourseByCourseId(@Param("courseID") long courseID);

    /**
     * delete logic registered course
     * @param courseID
     */
    @Query(value = "UPDATE register_course " + "SET status = 0 " + "WHERE course_id = :courseID", nativeQuery = true)
    public void deleteRegisterCourseByCourseId(@Param("courseID") long courseID);

    /**
     * delete logic registered course
     * @param studentID
     */
    @Query(value = "UPDATE register_course " + "SET status = 0 " + "WHERE student_id = :studentID", nativeQuery = true)
    public void deleteRegisterCourseByStudentId(@Param("studentID") long studentID);

    /**
     * count active registered course
     * @param username
     * @param courseID
     * @param status
     * @return integer
     */
    @Query(value = "SELECT count(*) " + "FROM register_course r " + "JOIN student s ON s.student_id = r.student_id "
            + "WHERE r.course_id = :courseID " + "AND s.user_name = :username "
            + "AND r.status = :status ", nativeQuery = true)
    public int countActiveRegisterCourseByUsername(@Param("username") String username, @Param("courseID") long courseID,
            @Param("status") long status);

    /**
     * find active registered course
     * @param courseID
     * @param studentID
     * @param registerDate
     * @return RegisterCourse
     */
    @Query(value = "SELECT * FROM register_course r " + "WHERE r.course_id = :courseID "
            + "AND r.student_id = :studentID " + "AND r.register_date = :registerDate "
            + "AND r.status = 1 ", nativeQuery = true)
    public RegisterCourse findActiveRegisterCourseById(@Param("courseID") long courseID,
            @Param("studentID") long studentID, @Param("registerDate") LocalDate registerDate);
}
