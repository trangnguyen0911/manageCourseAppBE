package com.shin.demotypescript.service;

import java.util.List;

import com.shin.demotypescript.model.Course;

/**
 * CourseService
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
public interface CourseService {
    /**
     * get all courses
     * 
     * @return List<Course>
     */
    public List<Course> getAllCourse();

    /**
     * get filter course    
     * 
     * @param contentSearch
     * @return List<Course>
     */
    public List<Course> getFilterCourse(String contentSearch);

    /**
     * get sorted courses
     * 
     * @param String  field
     * @param boolean type
     * @return List<Course>
     */
    public List<Course> getSortedCourse(String field, boolean type);

    /**
     * add new course
     * 
     * @param course
     * @return String
     */
    public String saveCourse(Course course);

    /**
     * get course by id
     * 
     * @param id
     * @return Course
     */
    public Course getCourseById(long id);

    /**
     * update course
     * 
     * @param id
     * @param course
     * @return string
     */
    public String updateCourse(long id, Course course);

    /**
     * delete logic course
     * 
     * @param id
     * @return string
     */
    public String deleteCourse(long id);

    /**
     * confirm exist course before update
     * 
     * @param id
     * @return string
     */
    public String confirmUpdateCourse(long id);
}
