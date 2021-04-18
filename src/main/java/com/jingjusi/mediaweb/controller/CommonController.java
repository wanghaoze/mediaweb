package com.jingjusi.mediaweb.controller;

import com.jingjusi.mediaweb.common.utils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

@Controller
@Api(value="公共controller",tags={"公共操作接口"})
public class CommonController {


    @RequestMapping(value = "/errors", method = {RequestMethod.POST, RequestMethod.GET})
    public String error() {
        return "errors";
    }

    @RequestMapping(value = "manage/{id}", method = {RequestMethod.POST, RequestMethod.GET})
    public String video_up(@ApiParam(name = "id",value = "用户id",required = true)@PathVariable String id) {

        return "upload";
    }

    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.GET})
    public String index(ModelMap model)
    {
        model.addAttribute("kcgl", "课程管理");
        return "upload";
    }

    @RequestMapping(value = "/ceshi")
    public String tteesstt(HttpServletRequest request) {

        System.out.println(request.getParameterMap().get("username").length);
        return "error";

    }
}
