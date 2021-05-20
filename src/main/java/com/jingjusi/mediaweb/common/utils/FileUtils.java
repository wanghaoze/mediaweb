package com.jingjusi.mediaweb.common.utils;

import com.jingjusi.mediaweb.common.domain.Image;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

public class FileUtils {

    public static String deleteFile(String path) {
        File file = new File(path);
        if(file.exists()) {
            file.delete();
            return "删除文件成功";
        }
        return "文件不存在";
    }
    public static Image saveImage(MultipartFile file, String path) throws IOException {
        saveFile(file,path,null);
        Image image = new Image();
        image.setImageName(file.getOriginalFilename());
        image.setImageUrl("/static/image/"+file.getOriginalFilename());
        long filesize = file.getSize()/1024;
        if (filesize<1024)
            image.setImageSize(filesize+"KB");
        else
            image.setImageSize((filesize/1024)+"MB");

        File picture = new File(path + file.getOriginalFilename());
        BufferedImage sourceImg=ImageIO.read(new FileInputStream(picture));
        image.setImageWidth(sourceImg.getWidth());
        image.setImageHeight(sourceImg.getHeight());

        Date now = new Date();
        image.setUploadTime(now);
        image.setUploadUser("uploadUser");
        image.setLastRequest(now);
        image.setCntVisit(0L);
        image.setRemarks("");
        return image;
    }
    public static String saveFile(MultipartFile file, String path,String filen) {
        if (file.isEmpty()) {
            return "空文件";
        } else {
            String fileOriginName = file.getOriginalFilename();  // 文件名
            String fileName = file.getOriginalFilename();  // 文件名
            if (filen!=null) {
                if (filen.contains("."))
                    fileName = filen;
                else {
                    assert fileName != null;
                    String[] sp = fileName.split("\\.");
                    String postfix = sp[sp.length-1];
                    fileName = filen + postfix;
                }
            }
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
    public static void main(String[] args) {
        String[] a = "aaaajpg".split("\\.");
        System.out.println(a.length);
    }
}
