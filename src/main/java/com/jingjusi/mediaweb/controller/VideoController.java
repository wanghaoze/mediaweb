package com.jingjusi.mediaweb.controller;

import com.jingjusi.mediaweb.common.domain.User;
import com.jingjusi.mediaweb.common.domain.Video;
import com.jingjusi.mediaweb.common.domain.VideoExample;
import com.jingjusi.mediaweb.common.utils.CommonResult;
import com.jingjusi.mediaweb.common.utils.FileUtils;
import com.jingjusi.mediaweb.common.utils.StringDateUtils;
import com.jingjusi.mediaweb.common.utils.VideoUtil;
import com.jingjusi.mediaweb.mapper.VideoMapper;
import com.jingjusi.mediaweb.service.VideoService;
import io.swagger.annotations.Api;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.Time;
import java.util.*;

@RestController
@Api(value="视频controller",tags={"视频操作接口"})
public class VideoController {
    static int page_size = 20;

    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Autowired
    private VideoService videoService;
    @Autowired
    private VideoMapper videoMapper;
    @RequestMapping(value = "/videoUpload", method = {RequestMethod.POST, RequestMethod.GET})
    public CommonResult<String> videoUpload(@RequestParam(value = "file") MultipartFile file,
                                            String my_chooses,
                                            String new_name,
                                            HttpServletRequest request) throws Exception {
//        User user = (User) request.getSession().getAttribute("user");
//        if (user==null) {
//            return new CommonResult<>(200,"登录过期，请重新登录");
//        }
//        if (!user.getRoles().contains("ROLE_ADMIN")&&!user.getRoles().contains("ROLE_STUDY")) {
//            return new CommonResult<>(200,"只有系统管理和网上学习管理员才能添加视频");
//        }
        System.out.println(file);

        String fileName = file.getOriginalFilename();  // 文件名
        String filePath = uploadFolder+"static\\video\\";// 上传后的路径
        String imgPath = uploadFolder+"static\\image\\";
        String res;

        if (new_name!=null&& !new_name.equals("")) {
            if (new_name.contains(".")) {
                new_name = new_name.substring(0,new_name.lastIndexOf("."));
            }
            assert fileName != null;
            String[] sp = fileName.split("\\.");
            String postfix = sp[sp.length-1];
            new_name = new_name + "." + postfix;
            fileName = new_name;
        }
        if (new_name==null||new_name.equals(""))
             res = FileUtils.saveFile(file,filePath,null);
        else
            res = FileUtils.saveFile(file,filePath,new_name);
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
        newVideo.setVideoUrl("static/video/"+fileName);
        if (filesize<1024)
            newVideo.setVideoSize(filesize+"MB");
        else
            newVideo.setVideoSize((filesize/1024)+"GB");
        newVideo.setVideoTime(videoTime);
        newVideo.setCntVisit(0L);
        newVideo.setUploadUser("s");
        newVideo.setUploadTime(now);
        newVideo.setFramepath("static/image/"+VideoName+".jpg");
        newVideo.setLastRequest(now);
        newVideo.setRemarks("static/video/"+fileName);
        if (res.equals("空文件")) {
            return new CommonResult<>(300,"空文件");
        } else if (res.equals("保存失败")) {
            return new CommonResult<>(400,"保存失败");
        } else  {
            videoService.addVideo(newVideo);
            VideoExample videoExample = new VideoExample();
            videoExample.createCriteria().andVideoNameEqualTo(fileName);
            System.out.println(videoMapper.selectByExample(videoExample));
            Video vid = videoMapper.selectByExample(videoExample).get(0);
            Long videoId = vid.getId();
            System.out.println(my_chooses);
            JSONObject jsonObject= new JSONObject(my_chooses);
            if (jsonObject.has("All")) {
                jsonObject.remove("All");
            }
            Iterator it = jsonObject.keys();
            while (it.hasNext()) {
                String key = (String) it.next();
                String value = jsonObject.getString(key);
                System.out.println(key);
                System.out.println(value);
                videoService.addVideoToCourse(videoId, new Long(key));
            }
            return new CommonResult<>(200,res,fileName);
        }
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
                String res = FileUtils.saveFile(file,uploadFolder+"video//",null);
                if (!file_name.equals(""))file_name = file.getOriginalFilename();
            }
            videoService.updateVideoById(vid,video);
            return new CommonResult<>(200,"上传成功",file_name);
        }
    }
    @RequestMapping(value = "/manage/video/delete", method = RequestMethod.POST)
    public CommonResult<String> deletevideo(Long id) {
        Video video = videoService.getVideoInfoById(id);
        String videoPath = video.getVideoUrl();
        String imgPath = video.getFramepath();
        FileUtils.deleteFile(uploadFolder+videoPath);
        FileUtils.deleteFile(uploadFolder+imgPath);
        String message = videoService.deleteVideo(id);
        System.out.println(message);
        return new CommonResult<>(200,message);
    }

}
