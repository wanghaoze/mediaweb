package com.jingjusi.mediaweb.controller;

import com.github.pagehelper.PageInfo;
import com.jingjusi.mediaweb.common.domain.Course;
import com.jingjusi.mediaweb.common.domain.Video;
import com.jingjusi.mediaweb.common.utils.CommonResult;
import com.jingjusi.mediaweb.common.utils.FileUtils;
import com.jingjusi.mediaweb.common.utils.StringDateUtils;
import com.jingjusi.mediaweb.common.utils.VideoUtil;
import com.jingjusi.mediaweb.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.bytedeco.javacpp.presets.opencv_core;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.*;

@RestController
@Api(value="视频controller",tags={"视频操作接口"})
public class VideoController {
    static int page_size = 20;

    @Autowired
    private VideoService videoService;

    @RequestMapping(value = "/videoUpload", method = {RequestMethod.POST, RequestMethod.GET})
    public CommonResult<String> videoUpload(@RequestParam(value = "file") MultipartFile file,
//                                            @RequestParam(value = "speaker") String speaker,
//                                            @RequestParam(value = "uploadUser") String uploadUser,
                                            HttpServletRequest request) throws Exception {
        System.out.println(file);
        String fileName = file.getOriginalFilename();  // 文件名
        String filePath = "C:\\Users\\WangHaoze\\Desktop\\mediaweb\\src\\main\\resources\\static\\video\\";// 上传后的路径
        String imgPath = "C:\\Users\\WangHaoze\\Desktop\\mediaweb\\src\\main\\resources\\static\\image\\";
        String res = FileUtils.saveVideo(file,filePath);
        long filesize = file.getSize()/1024/1024;
        Video newVideo = new Video();
        long time = VideoUtil.getDuration(filePath+fileName);
        Time videoTime = StringDateUtils.LongToTime(time);
        System.out.println(videoTime);
        Date now = new Date();
        newVideo.setSpeaker("speaker");
        newVideo.setVideoName(fileName);
        String VideoName = fileName.split("\\.")[0];
        String vpurl = "";
        File file1 = new File(filePath+fileName);
        VideoUtil.fetchPic(file1,imgPath+VideoName+".jpg");
//        newVideo.setFramePath((String) map.get("imgPath"));
        newVideo.setVideoUrl("/static/video/"+fileName);
        if (filesize<1024)
            newVideo.setVideoSize(filesize+"MB");
        else
            newVideo.setVideoSize((filesize/1024)+"GB");
        newVideo.setVideoTime(videoTime);
        newVideo.setCntVisit(0L);
        newVideo.setUploadUser("uploadUser");
        newVideo.setUploadTime(now);
        newVideo.setLastRequest(now);
        newVideo.setRemarks("");
        if (res.equals("空文件")) {
            return new CommonResult<>(300,"空文件");
        } else if (res.equals("保存失败")) {
            return new CommonResult<>(400,"保存失败");
        } else  {
            videoService.addVideo(newVideo);
            return new CommonResult<>(200,res,fileName);
        }
    }






//    @RequestMapping(value = "/getVideos", method = {RequestMethod.GET,RequestMethod.POST})
//    public Object getVideos(@ApiParam(name = "keyword", value = "关键字", required = true) @PathVariable String video_class,
//                                     @ApiParam(name = "page_no", value = "页码", required = true)@PathVariable Integer page_no) {
//        Map<String, Object> map = new HashMap<>();
//        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationContextConfiguration.class);
//        videoService = ctx.getBean(VideoService.class);
//        PageInfo<Video> videos = videoService.getVideos(page_no,20, video_class);
//        if (videos!=null) {
//            map.put("msg",1);   //账户存在
//            map.put("page_no",page_no);
//            map.put("page_size",videos.getSize());
//            map.put("videos_info", videos);
//        } else {
//            map.put("msg",0);   //账户不存在
//        }
//        return map;
//    }

//    @RequestMapping(value = "")
}
