package com.jingjusi.mediaweb.controller;

import com.github.pagehelper.PageInfo;
import com.jingjusi.mediaweb.common.domain.Course;
import com.jingjusi.mediaweb.common.domain.CourseExample;
import com.jingjusi.mediaweb.common.domain.User;
import com.jingjusi.mediaweb.common.utils.CommonResult;
import com.jingjusi.mediaweb.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(value="课程controller",tags={"课程操作接口"})
public class StudyController {
    @Autowired
    CourseService courseService;

    @RequestMapping(value = "/study/add")
    public CommonResult<String> addCourse(String className, String speaker, String summary, @RequestParam(value = "file") MultipartFile file
                        ,HttpServletRequest request) {
        Course course = new Course();
        String fileName = file.getOriginalFilename();  // 文件名
        course.setClassName(className);
        course.setSpeaker(speaker);
        course.setSummary(summary);
        course.setIndexImage(indexImage);
        course.setOpenTime(new Date());
        course.setRemarks("");
        String message = courseService.addCourse(course);
        return new CommonResult<>(200,message, className);
    }

    @RequestMapping(value = "/study/update")
    public CommonResult<String> updateCourse(Long id, String className, String speaker, String summary,
                                             String indexImage,
                                             HttpServletRequest request) {
        CourseExample course = new CourseExample();
        String message = courseService.updateCourseById(
                id,className,speaker,null,summary,null,indexImage);
        return new CommonResult<>(200,message, className);
    }

    @RequestMapping(value = "/manage/course/delete", method = RequestMethod.POST)
    public CommonResult<String> deletecourse(Long id) {
        Course course = courseService.findCourseById(id);
        String imgpath = course.getIndexImage();

        String message = courseService.deleteCourseById(id);
        System.out.println(message);
        return new CommonResult<>(200,message);
    }
}
