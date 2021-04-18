package com.jingjusi.mediaweb.controller;

import com.jingjusi.mediaweb.common.utils.CommonResult;
import com.jingjusi.mediaweb.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

@RestController
//@Api(value="视频controller",tags={"视频操作接口"})
public class VideoController {
    static int page_size = 20;

    @Autowired
    private VideoService videoService;

    @RequestMapping(value = "/videoUpload", method = {RequestMethod.POST, RequestMethod.GET})
    public CommonResult<String> videoUpload(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "my_chooses") String my_chooses, HttpServletRequest request) {
        System.out.println(request.getMethod());
        if (file.isEmpty()) {
            return new CommonResult<>(300,"空文件");
        }else {
            System.out.println(my_chooses);
            System.out.println(request);
            String fileOriginName = file.getOriginalFilename();  // 文件名
            String fileName = file.getOriginalFilename();  // 文件名
            assert fileName != null;
            String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
            String filePath = "C:\\Users\\WangHaoze\\Desktop\\mediaweb\\src\\main\\resources\\static\\video\\"; // 上传后的路径
//            fileName = UUID.randomUUID().toString().replace("-", "") + suffixName; // 新文件名
            File dest = new File(filePath + fileName);

            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
                System.out.println(dest);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("保存失败");
            }
            String filename = "/video/" + fileName;
            fileName.hashCode();
            System.out.println(filename);
            Hashtable hashtable = new Hashtable();
            return new CommonResult<>(200,fileOriginName+"上传成功",filename);
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
