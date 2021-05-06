package com.jingjusi.mediaweb.common.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtils {
    public static String saveVideo(MultipartFile file, String path) {
        if (file.isEmpty()) {
            return "空文件";
        } else {
            String fileOriginName = file.getOriginalFilename();  // 文件名
            String fileName = file.getOriginalFilename();  // 文件名
            assert fileName != null;
//            String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
            //fileName = UUID.randomUUID().toString().replace("-", "") + suffixName; // 新文件名
            File dest = new File(path + fileName);

            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
                System.out.println(dest);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("保存失败");
                return "保存失败";
            }
            String filename = "/video/" + fileName;
            System.out.println(filename);
            return fileOriginName + "上传成功";
        }
    }
}
