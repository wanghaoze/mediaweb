package com.jingjusi.mediaweb.controller;

import com.github.pagehelper.PageInfo;
import com.jingjusi.mediaweb.common.domain.Course;
import com.jingjusi.mediaweb.common.domain.CourseExample;
import com.jingjusi.mediaweb.common.domain.Image;
import com.jingjusi.mediaweb.common.domain.User;
import com.jingjusi.mediaweb.common.utils.CommonResult;
import com.jingjusi.mediaweb.common.utils.FileUtils;
import com.jingjusi.mediaweb.service.CourseService;
import com.jingjusi.mediaweb.service.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@RestController
@Api(value="课程controller",tags={"课程操作接口"})
public class CourseController {
    @Autowired
    CourseService courseService;
    @Autowired
    ImageService imageService;
    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @RequestMapping(value = "/manage/courseUpload")
    public CommonResult<String> addCourse(@RequestParam(value = "courseName")String className,
                                          @RequestParam(value = "speaker")String speaker,
                                          @RequestParam(value = "summary")String summary,
                                          @RequestParam(value = "file") MultipartFile file
                        ,HttpServletRequest request) {

            try {
                Course course = new Course();
                String fileName = file.getOriginalFilename();  // 文件名
                if (file.isEmpty()) {
                    course.setIndexImage("static/dist/img/photo"+(RandomUtils.nextInt(4) %4+1)+".jpg");
                } else {
                    String imgPath = uploadFolder+"static\\image\\";
                    Image image = FileUtils.saveImage(file,imgPath);
                    imageService.addImage(image);
                }
                course.setClassName(className);
                course.setSpeaker(speaker);
                course.setSummary(summary);
                course.setIndexImage("static/image/"+file.getOriginalFilename());
                course.setOpenTime(new Date());
                course.setRemarks("");
                String message = courseService.addCourse(course);
                System.out.println(course.getClassName()+"开设成功");
                return new CommonResult<>(200,message, className);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("保存失败");
                return new CommonResult<>(400,"保存失败");
            }



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
