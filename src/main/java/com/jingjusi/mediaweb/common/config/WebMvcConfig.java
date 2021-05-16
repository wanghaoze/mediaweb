package com.jingjusi.mediaweb.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //上传的图片在E盘Projects目录下，访问路径如：http://localhost:8080/pic/d3cf0281-bb7f-40e0-ab77-406db95ccf2c.jpg
        //其中pic表示访问的前缀。"file:E:\\Projects\\img_example\\"是文件真实的存储路径
        registry.addResourceHandler("/image/**").
                addResourceLocations("file:C:\\Users\\WangHaoze\\Desktop\\mediaweb\\src\\main\\resources\\static\\image\\");
        //20190929访问本地文件

        registry.addResourceHandler("/video/**").
                addResourceLocations("file:C:\\Users\\WangHaoze\\Desktop\\mediaweb\\src\\main\\resources\\static\\video\\");

        registry.addResourceHandler("/static/**").
                addResourceLocations("file:C:\\Users\\WangHaoze\\Desktop\\mediaweb\\src\\main\\resources\\static\\");
    }


}