package com.jingjusi.mediaweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

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
        System.out.println(uploadFolder);
        factory.setLocation(uploadFolder);
        return factory.createMultipartConfig();
    }
}
