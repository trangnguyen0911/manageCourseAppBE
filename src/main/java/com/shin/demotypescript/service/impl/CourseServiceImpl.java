package com.shin.demotypescript.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.shin.demotypescript.model.Course;
import com.shin.demotypescript.repository.CourseRepository;
import com.shin.demotypescript.repository.RegisterCourseRepository;
import com.shin.demotypescript.service.CourseService;
import com.shin.demotypescript.utils.ErrorCode;

/**
 * CourseServiceImpl
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
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseRepository repo;

    @Autowired
    private RegisterCourseRepository registerCourseRepo;

    /**
     * get all courses
     * @return List<Course>
     */
    public List<Course> getAllCourse() {
        List<Course> courses = repo.findActiveCourses();
	    
        return courses;
    }

    /**
     * get filter courses
     * @param contentSearch
     * @return List<Course>
     */
    public List<Course> getFilterCourse(String contentSearch) {
        List<Course> courses = repo.findSearchedCourses(contentSearch);

        return courses;
    }

    /**
     *  get sorted courses
     *  @param String field
     *  @param boolean type
     *  @return List<Course>
     */
    public List<Course> getSortedCourse(String field, boolean type) {
        List<Course> courses = null;
	    
        if (type) {
            courses = repo.findByStatus(1, Sort.by(Sort.Direction.ASC, field));
        } else {
            courses = repo.findByStatus(1, Sort.by(Sort.Direction.DESC, field));
        }

        return courses;
    }

    /*
     * add new course
     * @param course
     * @return String
     */
    public String saveCourse(Course course) {
        Course existCourse = repo.findByCourseNameIgnoreCaseAndStatus(course.getCourseName(), 1);

        if (existCourse != null) {
            // exist active course with same course name => not save
            return ErrorCode.SAME_NAME;
        } else {
            // save success
            repo.save(course);
            return ErrorCode.SUCCESS;
        }
    }

    /**
     * get course by id
     * @param id
     * @return Course
     */
    public Course getCourseById(long id) {
        return repo.findById(id).get();
    }

    /**
     * update course
     * @param id
     * @param course
     * @return string
     */
    public String updateCourse(long id, Course course) {
        Course oldCourse = repo.findById(id).get();
        // check active course
        if (oldCourse == null || oldCourse.getStatus() == 0) {
            // inactive course
            return ErrorCode.IN_ACTIVE;
        }

        Course existCourse = repo.findByCourseNameIgnoreCaseAndStatus(course.getCourseName(), 1);
        if (existCourse != null && existCourse.getCourseID() != id) {
            // exist active course with same course name => not save
            return ErrorCode.SAME_NAME;
        } else {
            // save success
            oldCourse.setCourseName(course.getCourseName());
            oldCourse.setDescription(course.getDescription());
            oldCourse.setDuration(course.getDuration());
            oldCourse.setFee(course.getFee());
            oldCourse.setInstructorEmail(course.getInstructorEmail());
            repo.save(oldCourse);
            return ErrorCode.SUCCESS;
        }
    }

    /**
     * delete logic course
     * @param id
     * @return string
     */
    public String deleteCourse(long id) {
        Course course = repo.findById(id).get();
        int countRegisterCourse = registerCourseRepo.countRegisterCourseByCourseId(id);

        // check active course
        if (course == null || course.getStatus() == 0) {
            // inactive course
            return ErrorCode.IN_ACTIVE;
        } else if (countRegisterCourse > 0) {
            return countRegisterCourse + "";
        } else {
            course.setStatus(0);
            repo.save(course);
            return ErrorCode.SUCCESS;
        }
    }

    /**
     * confirm exist course before update
     * @param id
     * @return string
     */
    public String confirmUpdateCourse(long id) {
        Course course = repo.findById(id).get();

        // check active course
        if (course == null || course.getStatus() == 0) {
            // inactive course
            return ErrorCode.IN_ACTIVE;
        } else {
            return ErrorCode.SUCCESS;
        }
    }
}
