package com.shin.demotypescript.service;

import java.util.List;

import com.shin.demotypescript.model.RegisterCourseDTO;

/**
 * RegisterCourseService
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
public interface RegisterCourseService {
    /**
     * get all register courses
     * 
     * @return List<RegisterCourseDTO>
     */
    public List<RegisterCourseDTO> getAllRegisterCourse();

    /**
     * get all sorted register courses
     * 
     * @param field
     * @param type
     * @return List<RegisterCourseDTO>
     */
    public List<RegisterCourseDTO> getAllSortedRegisterCourse(String field, boolean type);

    /**
     * get filter register courses
     * 
     * @param contentSearch
     * @return List<RegisterCourseDTO>
     */
    public List<RegisterCourseDTO> getAllFilterRegisterCourse(String contentSearch);

    /**
     * get all register courses by user name
     * 
     * @param username
     * @return List<RegisterCourseDTO>
     */
    public List<RegisterCourseDTO> getRegisterCourseByUsername(String username);

    /**
     * get filter register courses by user name
     * 
     * @param username
     * @param contentSearch
     * @return List<RegisterCourseDTO>
     */
    public List<RegisterCourseDTO> getFilterRegisterCourseByUsername(String username, String contentSearch);

    /**
     * get register courses by user name and sort
     * 
     * @param username
     * @param field
     * @param type
     * @return List<RegisterCourseDTO>
     */
    public List<RegisterCourseDTO> getSortedRegisterCourseByUsername(String username, String field, boolean type);

    /**
     * add new register courses
     * 
     * @param username
     * @param courseID
     * @return string
     */
    public String saveRegisterCourse(String username, long courseID);

    /**
     * delete logic register courses
     * 
     * @param dto
     * @return String
     */
    public String deleteRegisterCourse(RegisterCourseDTO dto);
}
