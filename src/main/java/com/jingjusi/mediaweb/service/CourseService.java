package com.jingjusi.mediaweb.service;

import com.github.pagehelper.PageInfo;
import com.jingjusi.mediaweb.common.domain.Course;
import com.jingjusi.mediaweb.common.domain.User;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
public interface CourseService {
    public String addCourse(Course course);
    public String addCourse(String class_name, String speaker, Date open_time, String summary, String remarks, String url);
    public String deleteCourseById(Long courseId);
    public String updateCourseById(Long courseId, String class_name, String speaker, Date open_time, String summary, String remarks, String url);
    public Course findCourseById(Long courseId);
    public PageInfo<Course> findCourseByCourseName(String courseName, Integer pageNo, Integer pageSize);
    public PageInfo<Course> findCourseBySpeaker(String speaker, Integer pageNo, Integer pageSize);
    public PageInfo<Course> findCourseBySummary(String summary, Integer pageNo, Integer pageSize);
    public String addUserToCourse(Long userId, Long courseId, String role);
    public String deleteUserFromCourse(Long userId, Long courseId);
    public String updateUserInCourse(Long userId, Long courseId, String role);
    public PageInfo<User> findCourseByUser(Long userId, Integer pageNo, Integer pageSize);
    public PageInfo<User> findUserByCourseRole(Long courseId, String role, Integer pageNo, Integer pageSize);
}
