package com.jingjusi.mediaweb.controller;

import com.github.pagehelper.PageInfo;
import com.jingjusi.mediaweb.common.domain.Course;
import com.jingjusi.mediaweb.common.domain.CourseExample;
import com.jingjusi.mediaweb.common.utils.CommonResult;
import com.jingjusi.mediaweb.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@Api(value="课程controller",tags={"课程操作接口"})
public class StudyController {
    @Autowired
    CourseService courseService;
    @RequestMapping(value = "/study/{page}")
    public String tteesstt(@ApiParam(name = "page_no", value = "页码", required = true)@PathVariable Integer page,Model model) {
        PageInfo<Course> coursePageInfo = courseService.findCourseByCourseName("",page,20);
        Map<String,Object> map = new HashMap<>();
        map.put("课程列表",coursePageInfo);
        model.addAttribute("list_course",new ArrayList<>(coursePageInfo.getList()));
        return "courseList";

    }

    @RequestMapping(value = "/study/add")
    public CommonResult<String> addCourse(String className, String speaker, String summary, String indexImage) {
        Course course = new Course();
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
    public CommonResult<String> updateCourse(Long id, String className, String speaker, String summary, String indexImage) {
        CourseExample course = new CourseExample();
        String message = courseService.updateCourseById(
                id,className,speaker,null,summary,null,indexImage);
        return new CommonResult<>(200,message, className);
    }
}
