package com.jingjusi.mediaweb.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jingjusi.mediaweb.common.domain.*;
import com.jingjusi.mediaweb.mapper.CourseMapper;
import com.jingjusi.mediaweb.mapper.CourseUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CourceServiceImpl implements CourseService{
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    CourseUserMapper courseUserMapper;

    @Override
    public String addCourse(Course course) {
        try {
            courseMapper.insert(course);
            return "添加成功";
        } catch (Exception e) {
            System.out.println(e);
            return "添加失败";
        }
    }

    @Override
    public String addCourse(String class_name, String speaker, Date open_time, String summary, String remarks, String url) {
        Course course = new Course();
        course.setClassName(class_name);
        course.setSpeaker(speaker);
        course.setOpenTime(open_time);
        course.setSummary(summary);
        course.setRemarks(remarks);
        course.setIndexImage(url);
        try {
            courseMapper.insert(course);
            return "添加成功";
        } catch (Exception e) {
            System.out.println(e);
            return "添加失败";
        }
    }

    @Override
    public String deleteCourseById(Long courseId) {
        try {
            courseMapper.deleteByPrimaryKey(courseId);
            return "删除成功";
        } catch (Exception e) {
            System.out.println(e);
            return "删除失败";
        }
    }

    @Override
    public String updateCourseById(Long courseId, String class_name, String speaker, Date open_time, String summary, String remarks, String url) {
        try {
            Course course = new Course();
            if (class_name != null) course.setClassName(class_name);
            if (speaker != null) course.setSpeaker(speaker);
            if (open_time != null) course.setOpenTime(open_time);
            if (summary != null) course.setSummary(summary);
            if (remarks != null) course.setRemarks(remarks);
            if (url != null) course.setIndexImage(url);
            CourseExample courseExample = new CourseExample();
            courseExample.createCriteria().andIdEqualTo(courseId);
            courseMapper.updateByExampleSelective(course, courseExample);
            return "更新成功";
        } catch (Exception e) {
            System.out.println(e);
            return "更新失败";
        }
    }

    @Override
    public Course findCourseById(Long courseId) {
        try {
            Course course = courseMapper.selectByPrimaryKey(courseId);
            return course;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public PageInfo<Course> findCourseByCourseName(String courseName, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        try {
            CourseExample courseExample = new CourseExample();
            courseExample.createCriteria().andClassNameLike("%"+courseName+"%");
            List<Course> courses = new ArrayList<>(courseMapper.selectByExample(courseExample));
            return new PageInfo<>(courses);
        } catch (Exception e) {
            System.out.println(e);
            return new PageInfo<Course>();
        }
    }

    @Override
    public PageInfo<Course> findCourseBySpeaker(String speaker, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        try {
            CourseExample courseExample = new CourseExample();
            courseExample.createCriteria().andSpeakerLike("%"+speaker+"%");
            List<Course> courses = new ArrayList<>(courseMapper.selectByExample(courseExample));
            return new PageInfo<>(courses);
        } catch (Exception e) {
            System.out.println(e);
            return new PageInfo<Course>();
        }
    }

    @Override
    public PageInfo<Course> findCourseBySummary(String summary, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        try {
            CourseExample courseExample = new CourseExample();
            courseExample.createCriteria().andSummaryLike("%"+summary+"%");
            List<Course> courses = new ArrayList<>(courseMapper.selectByExample(courseExample));
            return new PageInfo<>(courses);
        } catch (Exception e) {
            System.out.println(e);
            return new PageInfo<Course>();
        }
    }

    @Override
    public String addUserToCourse(Long userId, Long courseId, String role) {
        try {
            CourseUser courseUser= new CourseUser();
            courseUser.setCourseId(courseId);
            courseUser.setUserId(userId);
            courseUser.setRoles(role);
            courseUserMapper.insert(courseUser);
            return "添加成功";
        } catch (Exception e) {
            System.out.println(e);
            return "添加失败";
        }
    }

    @Override
    public String deleteUserFromCourse(Long userId, Long courseId) {
        return null;
    }

    @Override
    public String updateUserInCourse(Long userId, Long courseId, String role) {
        return null;
    }

    @Override
    public PageInfo<User> findCourseByUser(Long userId, Integer pageNo, Integer pageSize) {
        return null;
    }

    @Override
    public PageInfo<User> findUserByCourseRole(Long courseId, String role, Integer pageNo, Integer pageSize) {
        return null;
    }
}
