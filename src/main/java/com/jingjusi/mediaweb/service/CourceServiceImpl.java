package com.jingjusi.mediaweb.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jingjusi.mediaweb.common.domain.*;
import com.jingjusi.mediaweb.mapper.CourseMapper;
import com.jingjusi.mediaweb.mapper.CourseUserMapper;
import com.jingjusi.mediaweb.mapper.UserMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourceServiceImpl implements CourseService{
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    CourseUserMapper courseUserMapper;
    @Autowired
    UserMapper userMapper;

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
            CourseUserExample courseUserExample = new CourseUserExample();
            courseUserExample.createCriteria().andUserIdEqualTo(userId).andCourseIdEqualTo(courseId);
            List<CourseUser>courseUsers = courseUserMapper.selectByExample(courseUserExample);
            if (!courseUsers.isEmpty()) {
                return "用户已存在！";
            }
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
        try{
            CourseUserExample courseUserExample = new CourseUserExample();
            courseUserExample.createCriteria().andUserIdEqualTo(userId).andCourseIdEqualTo(courseId);
            List<CourseUser> courseUsers =  new ArrayList<>(courseUserMapper.selectByExample(courseUserExample));
            if (!courseUsers.isEmpty()) {
                try{
                    CourseUser courseUser = courseUsers.get(0);
                    courseUserMapper.deleteByPrimaryKey(courseUser.getId());
                } catch (Exception e) {
                    System.out.println(e);
                    return "数据存在但删除失败";
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            return "数据不存在，删除失败";
        }
        return "删除失败";
    }

    @Override
    public String updateUserInCourse(Long userId, Long courseId, String role, Boolean mode) {
        //mode==true 为增， mode==false 为删
        try {
            CourseUserExample courseUserExample = new CourseUserExample();
            courseUserExample.createCriteria().andCourseIdEqualTo(courseId).andUserIdEqualTo(userId);
            List<CourseUser> courseUsers = new ArrayList<>(courseUserMapper.selectByExample(courseUserExample));
            if (!courseUsers.isEmpty()) {
                try{
                    CourseUser courseUser = courseUsers.get(0);
                    List<GrantedAuthority> authorities = Arrays.stream(courseUser.getRoles().split(","))
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());
                    if (mode) {
                        authorities.add(new SimpleGrantedAuthority(role));
                    } else {
                        int index = authorities.indexOf(new SimpleGrantedAuthority(role));
                        if (index!=-1) {
                            authorities.remove(index);
                        }
                    }
                    String roles = StringUtils.join(authorities,",");
                    System.out.println(roles);
                    courseUser.setRoles(roles);
                    courseUserMapper.updateByPrimaryKey(courseUser);
                    return "更新成功";
                } catch (Exception e) {
                    System.out.println(e);
                    return "数据存在但更新失败";
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            return "更新失败";
        }
        return "更新失败";
    }

    @Override
    public PageInfo<Course> findCourseByUser(Long userId, Integer pageNo, Integer pageSize) {
        try{
            PageHelper.startPage(pageNo,pageSize);
            CourseUserExample courseUserExample = new CourseUserExample();
            courseUserExample.createCriteria().andUserIdEqualTo(userId);
            List<CourseUser> courseUsers = new ArrayList<>(courseUserMapper.selectByExample(courseUserExample));
            List<Course> courses = new ArrayList<>();
            for (CourseUser courseUser:courseUsers) {
                courses.add(courseMapper.selectByPrimaryKey(courseUser.getCourseId()));
            }
            return new PageInfo<>(courses);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public PageInfo<User> findUserByCourseRole(Long courseId, String role, Integer pageNo, Integer pageSize) {
        try{
            PageHelper.startPage(pageNo,pageSize);
            CourseUserExample courseUserExample = new CourseUserExample();
            courseUserExample.createCriteria().andCourseIdEqualTo(courseId);
            List<CourseUser> courseUsers = new ArrayList<>(courseUserMapper.selectByExample(courseUserExample));
            List<User> users = new ArrayList<>();
            for (CourseUser courseUser:courseUsers) {
                users.add(userMapper.selectByPrimaryKey(courseUser.getUserId()));
            }
            return new PageInfo<>(users);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
