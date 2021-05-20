package com.jingjusi.mediaweb.controller;

import com.github.pagehelper.PageInfo;
import com.jingjusi.mediaweb.common.domain.*;
import com.jingjusi.mediaweb.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@Api(value="公共controller",tags={"公共操作接口"})
public class CommonController {
    @Autowired
    CourseService courseService;
    @Autowired
    VideoService videoService;
    @Autowired
    BookService bookService;
    @Autowired
    BorrowService borrowService;
    @Autowired
    MyTransactionService myTransactionService;
    @Autowired
    UserService userService;
    @Autowired
    TabletService tabletService;

    @RequestMapping(value = "/errors", method = {RequestMethod.POST, RequestMethod.GET})
    public String error() {
        return "errors";
    }


    @RequestMapping(value = "/mwatch/{vid}")
    public String wv(
            @ApiParam(name = "videoId",value = "视频id",required = true)@PathVariable Long vid,
            ModelMap model,HttpServletRequest request,String s) {
        User user = (User) request.getSession().getAttribute("user");
        if (user==null) {
            user = new User();
            user.setUsername("游客");
        }
        model.addAttribute("user",user);
        Video video =  videoService.getVideoInfoById(vid);
        model.addAttribute("video",video);

        return "manageWatch";
    }
    @RequestMapping(value = "manage/videos", method = {RequestMethod.POST, RequestMethod.GET})
    public String video_up(
                           HttpServletRequest request,Model model) {
        PageInfo<Course> coursePageInfo = courseService.findCourseByCourseName("",0,20);
        List<Course> courses = coursePageInfo.getList();
        model.addAttribute("courses",courses);
        PageInfo<Video> videoPageInfo = videoService.getVideosByName("",0,20);
        Map<String,Object> map = new HashMap<>();
        map.put("课程列表",videoPageInfo);
        model.addAttribute("list_video",new ArrayList<>(videoPageInfo.getList()));
        User user = (User) request.getSession().getAttribute("user");
        if (user==null) {
            user = new User();
            user.setUsername("游客");
        }
        model.addAttribute("user",user);
        return "videoManagement";
    }

    @RequestMapping(value = "/manage/courses")
    public String courses(HttpServletRequest request,Model model) {
        PageInfo<Course> coursePageInfo = courseService.findCourseByCourseName("",0,20);
        List<Course> courseList = coursePageInfo.getList();
        User user = (User) request.getSession().getAttribute("user");
        if (user==null) {
            user = new User();
            user.setUsername("游客");
        }
        model.addAttribute("courseList",courseList);
        model.addAttribute("user",user);
        return "courseManagement";
    }

    @RequestMapping(value = "/course/add")
    public String addCourse(HttpServletRequest request,Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user==null) {
            user = new User();
            user.setUsername("游客");
        }
        model.addAttribute("user",user);
        return "addCourse";
    }

    @RequestMapping(value = "/manage/uploadV")
    public String videos(HttpServletRequest request,Model model) {

        PageInfo<Course> coursePageInfo = courseService.findCourseByCourseName("",0,20);
        List<Course> courses = coursePageInfo.getList();
        model.addAttribute("courses",courses);
        PageInfo<Video> videoPageInfo = videoService.getVideosByName("",0,20);
        Map<String,Object> map = new HashMap<>();
        map.put("课程列表",videoPageInfo);
        model.addAttribute("list_video",new ArrayList<>(videoPageInfo.getList()));
        User user = (User) request.getSession().getAttribute("user");
        if (user==null) {
            user = new User();
            user.setUsername("游客");
        }
        model.addAttribute("user",user);
        return "uploadVideo";
    }

    @RequestMapping(value = "/study/{page}")
    public String tteesstt(@ApiParam(name = "page_no", value = "页码", required = true)@PathVariable Integer page,HttpServletRequest request,Model model) {
        PageInfo<Course> coursePageInfo = courseService.findCourseByCourseName("",page,20);
        Map<String,Object> map = new HashMap<>();
        map.put("课程列表",coursePageInfo);
        model.addAttribute("list_course",new ArrayList<>(coursePageInfo.getList()));
        User user = (User) request.getSession().getAttribute("user");
        if (user==null) {
            user = new User();
            user.setUsername("游客");
        }
        model.addAttribute("user",user);
        return "courseList";

    }

    @RequestMapping(value = "/course/{cid}")
    public String manageCourse(@ApiParam(name = "courseId",value = "课程id",required = true)@PathVariable Long cid,
                               HttpServletRequest request,
                               Model model) {
        Course course = courseService.findCourseById(cid);
        model.addAttribute("courseDetail", course);
        List<Video> videos = videoService.getVideosByCourse(cid,0,20).getList();
        model.addAttribute("list_video",videos);
        User user = (User) request.getSession().getAttribute("user");
        if (user==null) {
            user = new User();
            user.setUsername("游客");
        }
        model.addAttribute("user",user);
        return "courseDetail";
    }

    @RequestMapping(value = "/course/{cid}/video/{vid}")
    public String watch(@ApiParam(name = "courseId",value = "课程id",required = true)@PathVariable Long cid,
                        @ApiParam(name = "num",value = "节数",required = true)Integer num,
                        @ApiParam(name = "videoId",value = "视频id",required = true)@PathVariable Long vid,
                        HttpServletRequest request,
                        Model model) {
        Video videoDetails = videoService.getVideoInfoById(vid);
        model.addAttribute("num",num);
        int pageNo = num/20;
        PageInfo<Video> videoPageInfo = videoService.getVideosByCourse(cid,pageNo,20);
        List<Video> videoList = videoPageInfo.getList();
        User user = (User) request.getSession().getAttribute("user");
        if (user==null) {
            user = new User();
            user.setUsername("游客");
        }
        model.addAttribute("user",user);
        model.addAttribute("videoList",videoList);
        model.addAttribute("videoDetails",videoDetails);
        return "videoDetail";
    }
    @RequestMapping(value = "/manage/video/{vid}")
    public String updateVideo(@ApiParam(name = "videoId",value = "视频id",required = true)@PathVariable Long vid,
                              HttpServletRequest request,
                              Model model) {
        Video videoDetails = videoService.getVideoInfoById(vid);
        User user = (User) request.getSession().getAttribute("user");
        if (user==null) {
            user = new User();
            user.setUsername("游客");
        }
        model.addAttribute("user",user);
        model.addAttribute("videoDetails",videoDetails);
        return "updateVideo";
    }

    @RequestMapping(value = "/study/course/{num}")
    public String videoList(@ApiParam(name = "course_no", value = "课程号", required = true)@PathVariable Long num,
                            HttpServletRequest request, Model model) {
        PageInfo<Video> videoPageInfo = videoService.getVideosByCourse(num,0,20);
        Map<String,Object> map = new HashMap<>();
        map.put("课程列表",videoPageInfo);
        User user = (User) request.getSession().getAttribute("user");
        if (user==null) {
            user = new User();
            user.setUsername("游客");
        }
        model.addAttribute("user",user);
        model.addAttribute("list_course",new ArrayList<>(videoPageInfo.getList()));
        return "videoList";
    }
    @GetMapping(value = "/manage/books")
    public String showBook(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user==null) {
            user = new User();
            user.setUsername("游客");
        }
        model.addAttribute("user", user);
        List<Book> list = bookService.getBooksByName("").getList();
        model.addAttribute("list", list);
        return "book-management";
    }
    @GetMapping(value = "/manage/transactions")
    public String showTransaction(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user==null) {
            user = new User();
            user.setUsername("游客");
        }
        model.addAttribute("user", user);
        List<MyTransaction> list = myTransactionService.getTransactionByTargrt("").getList();
        model.addAttribute("list", list);
        return "transactionManagement";
    }
    @GetMapping(value = "/manage/tablets")
    public String showTablet(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user==null) {
            user = new User();
            user.setUsername("游客");
        }
        model.addAttribute("user", user);
        List<Tablet> list = tabletService.findTabletByDonors("",0,20).getList();
        model.addAttribute("list", list);
        return "tabletManagement";
    }
    @GetMapping(value = "/manage/users")
    public String showUser(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user==null) {
            user = new User();
            user.setUsername("游客");
        }
        model.addAttribute("user", user);
        List<User> list = userService.findUsersByPage(0,20).getList();
        model.addAttribute("list", list);
        return "userManagement";
    }
}
