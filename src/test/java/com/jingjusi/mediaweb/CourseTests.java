package com.jingjusi.mediaweb;

import com.jingjusi.mediaweb.common.domain.Course;
import com.jingjusi.mediaweb.common.utils.FileUtils;
import com.jingjusi.mediaweb.common.utils.VideoUtil;
import com.jingjusi.mediaweb.mapper.CourseMapper;
import com.jingjusi.mediaweb.service.CourseService;
import org.apache.commons.lang.math.RandomUtils;
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
        String[] courses = {"净居寺云端讲堂 -- 中级班","净居寺云端讲堂 -- 讲经班","净居寺云端讲堂 -- 研修班","净居寺云端讲堂 -- 随学班",
        "净居寺云端讲堂 -- 初学班","净居寺云端讲堂 -- 公开课"};
        String[] speakers = {"张三","王二","李红","陈一","张三","王二"};
        for (int i = 0; i < 6; i++) {
            Course course = new Course();
            course.setIndexImage("../static/dist/img/photo"+(i%4+1)+".jpg");
            course.setRemarks("（评论）测试");
            course.setSummary(courses[i]);
            course.setSpeaker(speakers[i]);
            course.setClassName(courses[i]);
            courseService.addCourse(course);
        }
    }

    @Test
    void addUserToCourse() {
        for (int i = 0; i < 10; i++) {
            courseService.addUserToCourse(123L+i,22L,"ROLE_STUDENT");
        }
    }

    @Test
    void delete() {
        FileUtils.deleteFile("/static/video/01-项目演示.mp4");
    }
    @Test
    void sadf() {
        System.out.println(RandomUtils.nextInt(4));
}
}
