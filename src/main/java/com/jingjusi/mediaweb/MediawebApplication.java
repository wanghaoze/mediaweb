package com.jingjusi.mediaweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;
//@EnableEurekaServer
@SpringBootApplication
@MapperScan(basePackages = "com.jingjusi.mediaweb.mapper")
public class MediawebApplication {
    @Value("${file.uploadFolder}")
    private String uploadFolder;

    public static void main(String[] args) {
        SpringApplication.run(MediawebApplication.class, args);
    }
    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
//        factory.setMaxFileSize(DataSize.ofBytes(102400000));
//        factory.setMaxRequestSize(DataSize.ofBytes(102400000));
        System.out.println(uploadFolder);
        factory.setLocation(uploadFolder);
        return factory.createMultipartConfig();
    }
}
