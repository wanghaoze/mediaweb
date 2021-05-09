package com.jingjusi.mediaweb.controller;

import com.jingjusi.mediaweb.common.domain.Image;
import com.jingjusi.mediaweb.common.utils.CommonResult;
import com.jingjusi.mediaweb.common.utils.FileUtils;
import com.jingjusi.mediaweb.service.ImageService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

@RestController
@Api(value="图片controller",tags={"图片操作接口"})
public class ImageController {
    @Value("${file.uploadFolder}")
    private String uploadFolder;
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/imageUpload", method = {RequestMethod.POST, RequestMethod.GET})
    public CommonResult<String> videoUpload(@RequestParam(value = "file") MultipartFile file,
                                            @RequestParam(value = "my_chooses") String my_chooses,
                                            HttpServletRequest request) {
        System.out.println(request.getMethod());
        if (file.isEmpty()) {
            return new CommonResult<>(300,"空文件");
        }else {

            try {
                Image image = FileUtils.saveImage(file,uploadFolder+"static\\image\\");
                imageService.addImage(image);
                return new CommonResult<>(200,file.getOriginalFilename()+"上传成功",file.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("保存失败");
                return new CommonResult<>(400,"保存失败");
            }
        }
    }
}
