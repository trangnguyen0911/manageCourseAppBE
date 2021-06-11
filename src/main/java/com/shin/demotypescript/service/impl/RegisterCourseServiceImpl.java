package com.shin.demotypescript.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.shin.demotypescript.model.Course;
import com.shin.demotypescript.model.RegisterCourse;
import com.shin.demotypescript.model.RegisterCourseDTO;
import com.shin.demotypescript.model.Student;
import com.shin.demotypescript.repository.RegisterCourseRepository;
import com.shin.demotypescript.service.RegisterCourseService;
import com.shin.demotypescript.utils.ErrorCode;

/**
 * RegisterCourseServiceImpl
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
public class RegisterCourseServiceImpl implements RegisterCourseService{
    @Autowired
    private RegisterCourseRepository repo;

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private CourseServiceImpl courseService;

    /**
     * get all register courses
     * @return List<RegisterCourseDTO>
     */
    public List<RegisterCourseDTO> getAllRegisterCourse() {
        List<RegisterCourseDTO> registerCourseDTOs = repo.findAllRegisterCourse();

        return registerCourseDTOs;
    }

    /**
     * get all sorted register courses
     * @param field
     * @param type
     * @return List<RegisterCourseDTO>
     */
    public List<RegisterCourseDTO> getAllSortedRegisterCourse(String field, boolean type) {
        List<RegisterCourseDTO> registerCourseDTOs = repo.findAllRegisterCourse();

        if ("username".equals(field)) {
            registerCourseDTOs = repo.findAllSortedRegisterCourseByUsername();
        } else if ("registerDate".equals(field)) {
            if (type) {
                registerCourseDTOs = repo.findAllSortedRegisterCourseByDate(Sort.by(Sort.Direction.ASC, field));
            } else {
                registerCourseDTOs = repo.findAllSortedRegisterCourseByDate(Sort.by(Sort.Direction.DESC, field));
            }
        }
        return registerCourseDTOs;
    }

    /**
     * get filter register courses
     * @param contentSearch
     * @return List<RegisterCourseDTO>
     */
    public List<RegisterCourseDTO> getAllFilterRegisterCourse(String contentSearch) {
        List<RegisterCourseDTO> registerCourseDTOs = repo.findAllSearchedRegisterCourse(contentSearch);

        return registerCourseDTOs;
    }

    /**
     * get all register courses by user name
     * @param username
     * @return List<RegisterCourseDTO>
     */
    public List<RegisterCourseDTO> getRegisterCourseByUsername(String username) {
        List<RegisterCourseDTO> registerCourseDTOs = repo.findRegisterCourseByUsername(username);

        return registerCourseDTOs;
    }

    /**
     * get filter register courses by user name
     * @param username
     * @param contentSearch
     * @return List<RegisterCourseDTO>
     */
    public List<RegisterCourseDTO> getFilterRegisterCourseByUsername(String username, String contentSearch) {
        List<RegisterCourseDTO> registerCourseDTOs = repo.findSearchedRegisterCourseByUsername(username, contentSearch);

        return registerCourseDTOs;
    }

    /**
     * get register courses by user name and sort
     * @param username
     * @param field
     * @param type
     * @return List<RegisterCourseDTO>
     */
    public List<RegisterCourseDTO> getSortedRegisterCourseByUsername(String username, String field, boolean type) {
        List<RegisterCourseDTO> registerCourseDTOs = null;

        if ("courseName".equals(field)) {
            registerCourseDTOs = repo.findRegisterCourseByUsernameSortByCourseName(username);
        } else {
            if (type) {
                registerCourseDTOs = repo.findRegisterCourseByUsernameSortByMentorEmail(username);
            } else {
                registerCourseDTOs = repo.findRegisterCourseByUsernameSortByMentorEmail(username);
            }
        }
        return registerCourseDTOs;
    }

    /**
     * add new register courses
     * @param username
     * @param courseID
     * @return string
     */
    public String saveRegisterCourse(String username, long courseID) {
        Course course = courseService.getCourseById(courseID);

        if (course == null || course.getStatus() == 0) {
            // inactive course => not save
            return ErrorCode.IN_ACTIVE;
        }

        int countActiveRegisterCourse = repo.countActiveRegisterCourseByUsername(username, courseID, 1);
        if (countActiveRegisterCourse > 0) {
            // exist active register course => not save
            return ErrorCode.EXIST_ACTIVE;
        }

        Student student = studentService.getStudentByUsername(username);
        RegisterCourse registerCourse = new RegisterCourse(student, course, student.getStudentID(), courseID);

        repo.save(registerCourse);
        return ErrorCode.SUCCESS;
    }

    /**
     * delete logic register courses
     * @param dto
     * @return String
     */
    public String deleteRegisterCourse(RegisterCourseDTO dto) {
        RegisterCourse registerCourse = repo.findActiveRegisterCourseById(dto.getCourseID(), dto.getStudentID(),
        dto.getRegisterDate());

        // check active register courses
        if (registerCourse == null || registerCourse.getStatus() == 0) {
            // inactive register courses
            return ErrorCode.IN_ACTIVE;
        } else {
            registerCourse.setStatus(0);
            repo.save(registerCourse);
            return ErrorCode.SUCCESS;
        }
    }
}
