package com.jingjusi.mediaweb.controller;

import com.jingjusi.mediaweb.common.utils.CommonResult;
import io.swagger.annotations.Api;
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
    @RequestMapping(value = "/imageUpload", method = {RequestMethod.POST, RequestMethod.GET})
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
}
