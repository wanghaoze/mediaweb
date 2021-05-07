package com.jingjusi.mediaweb.controller;

import com.github.pagehelper.PageInfo;
import com.jingjusi.mediaweb.common.domain.Course;
import com.jingjusi.mediaweb.common.domain.User;
import com.jingjusi.mediaweb.common.domain.Video;
import com.jingjusi.mediaweb.common.domain.VideoExample;
import com.jingjusi.mediaweb.common.utils.CommonResult;
import com.jingjusi.mediaweb.common.utils.FileUtils;
import com.jingjusi.mediaweb.service.CourseService;
import com.jingjusi.mediaweb.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

@Controller
@Api(value="公共controller",tags={"公共操作接口"})
public class CommonController {
    @Value("${file.uploadFolder}"+"video//")
    private String videoFolder;
    @Autowired
    CourseService courseService;
    @Autowired
    VideoService videoService;

    @RequestMapping(value = "/errors", method = {RequestMethod.POST, RequestMethod.GET})
    public String error() {
        return "errors";
    }

    @RequestMapping(value = "manage/{id}", method = {RequestMethod.POST, RequestMethod.GET})
    public String video_up(@ApiParam(name = "id",value = "用户id",required = true)@PathVariable String id) {

        return "upload";
    }

    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.GET})
    public String index(ModelMap model)
    {
        model.addAttribute("kcgl", "课程管理");
        return "upload";
    }
    @RequestMapping(value = "/manage/courses")
    public String courses(Model model) {
        PageInfo<Course> coursePageInfo = courseService.findCourseByCourseName("",0,20);
        List<Course> courseList = coursePageInfo.getList();
        model.addAttribute("courseList",courseList);
        return "courseManagement";
    }
    @RequestMapping(value = "/manage/videos")
    public String videos(Model model) {
        model.addAttribute("");
        PageInfo<Video> videoPageInfo = videoService.getVideosByName("",0,20);
        Map<String,Object> map = new HashMap<>();
        map.put("课程列表",videoPageInfo);
        model.addAttribute("list_video",new ArrayList<>(videoPageInfo.getList()));
        return "videoManagement";
    }

    @RequestMapping(value = "/videos/add", method = {RequestMethod.POST, RequestMethod.GET})
    public String addvideopage(HttpServletRequest request, ModelMap model)
    {
        model.addAttribute("kcgl", "课程管理");
        String user = (String) request.getSession().getAttribute("user");
        model.addAttribute("user",user);
        return "upload";
    }
    @RequestMapping(value = "/course/{cid}/video/{vid}")
    public String watch(@ApiParam(name = "courseId",value = "课程id",required = true)@PathVariable Long cid,
                        @ApiParam(name = "num",value = "节数",required = true)Integer num,
                        @ApiParam(name = "videoId",value = "视频id",required = true)@PathVariable Long vid,
                        Model model) {
        Video videoDetails = videoService.getVideoInfoById(vid);
        model.addAttribute("num",num);
        int pageNo = num/20;
        PageInfo<Video> videoPageInfo = videoService.getVideosByCourse(cid,pageNo,20);
        List<Video> videoList = videoPageInfo.getList();
        model.addAttribute("videoList",videoList);
        model.addAttribute("videoDetails",videoDetails);
        return "videoDetail";
    }
    @RequestMapping(value = "/manage/video/{vid}")
    public String updateVideo(@ApiParam(name = "videoId",value = "视频id",required = true)@PathVariable Long vid,
                        Model model) {
        Video videoDetails = videoService.getVideoInfoById(vid);
        model.addAttribute("videoDetails",videoDetails);
        return "updateVideo";
    }
    @RequestMapping(value = "/manage/update/video", method = {RequestMethod.POST, RequestMethod.GET})
    public CommonResult<String> vud(@RequestParam(value = "视频id")Long vid,
                                    @RequestParam(value = "视频名称")String videoName,
                                    @RequestParam(value = "主讲人")String speaker,
                                    @RequestParam(value = "视频时长")Date videoTime,
                                    @RequestParam(value = "视频文件")MultipartFile file,
                                    HttpServletRequest request) {

        String file_name = "";
        System.out.println(request.getMethod());
        if (file.isEmpty()&& speaker.isEmpty() && videoTime==null) {
            return new CommonResult<>(300,"未选择更新项");
        } else {
            VideoExample videoExample = new VideoExample();
            videoExample.createCriteria().andSpeakerEqualTo(speaker).andVideoTimeEqualTo(videoTime).andVideoNameEqualTo(videoName);
            Video video = new Video();
            if (!videoName.isEmpty()){
                video.setVideoName(videoName);
                file_name = videoName;
            }
            if (!speaker.isEmpty()) {
                video.setSpeaker(speaker);
            }
            if (!file.isEmpty()) {
                String res = FileUtils.saveVideo(file,videoFolder);
                if (!file_name.equals(""))file_name = file.getOriginalFilename();
            }
            videoService.updateVideoById(vid,video);
            return new CommonResult<>(200,"上传成功",file_name);
        }
    }
    @RequestMapping(value = "/study/course/{num}")
    public String videoList(@ApiParam(name = "course_no", value = "课程号", required = true)@PathVariable Long num, Model model) {
        PageInfo<Video> videoPageInfo = videoService.getVideosByCourse(num,0,20);
        Map<String,Object> map = new HashMap<>();
        map.put("课程列表",videoPageInfo);
        model.addAttribute("list_course",new ArrayList<>(videoPageInfo.getList()));
        return "videoList";
    }
}
