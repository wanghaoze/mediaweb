package com.jingjusi.mediaweb;

import com.jingjusi.mediaweb.common.domain.Course;
import com.jingjusi.mediaweb.mapper.CourseMapper;
import com.jingjusi.mediaweb.service.CourseService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan(basePackages = "com.jingjusi.mediaweb.mapper")
public class CourseTests {
    @Autowired
    CourseService courseService;

    @Test
    void addCourses(){
        for (int i = 0; i < 10; i++) {
            Course course = new Course();
            course.setIndexImage("baidu.com");
            course.setRemarks(i+"re");
            course.setSummary(i+"sm");
            course.setSpeaker("xiao"+i);
            course.setClassName(i+"");
            courseService.addCourse(course);
        }
    }

    @Test
    void addUserToCourse() {
        for (int i = 0; i < 10; i++) {
            courseService.addUserToCourse(123L+i,22L,"ROLE_STUDENT");
        }
    }
}
