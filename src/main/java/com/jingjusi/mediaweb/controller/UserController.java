package com.jingjusi.mediaweb.controller;

import com.jingjusi.mediaweb.common.domain.Book;
import com.jingjusi.mediaweb.common.domain.User;
import com.jingjusi.mediaweb.common.utils.CommonResult;
import com.jingjusi.mediaweb.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@Api(value="用户controller",tags={"用户操作接口"})
public class UserController {

    @Autowired
    UserService userService;
    @RequestMapping(value = "/userManage/addUser",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public CommonResult<String> addUser(HttpServletRequest request,
                                        @RequestBody User newUser) {
        try{
            User user = (User) request.getSession().getAttribute("user");
            if (user==null) {
                return new CommonResult<>(200,"登录过期，请重新登录");
            }
            if (!user.getRoles().contains("ROLE_ADMIN")) {
                return new CommonResult<>(200,"只有系统管理才能添加用户");
            }
            CommonResult<String> ar;
            newUser.setDateJoined(new Date());
            newUser.setIsActive(true);
            newUser.setIsStaff(true);
            newUser.setLevel(0);
            newUser.setRemarks("用户");
            newUser.setLastLogin(new Date());
            if (newUser.getRoles().equals("") ||newUser.getRoles()==null) {
                newUser.setRoles("ROLE_USER");
                newUser.setIsStaff(false);
            }
            String message = userService.addUser(newUser);
            ar = new CommonResult<>(200, message);
            return ar;
        }catch (Exception e){
            System.out.println(e);
            return new CommonResult<>(200,"用户添加失败");
        }

    }

    @RequestMapping(value = "/userManage/deleteUser",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public CommonResult<String> deleteUser(HttpServletRequest request,
                                        @RequestBody User newUser) {
        try{
            User user = (User) request.getSession().getAttribute("user");
            if (user==null) {
                return new CommonResult<>(200,"登录过期，请重新登录");
            }
            if (!user.getRoles().contains("ROLE_ADMIN")) {
                return new CommonResult<>(200,"只有系统管理才能删除用户");
            }
            CommonResult<String> ar;
            User u = userService.findUser(newUser.getUsername());
            if (u==null){
                return new CommonResult<>(200,"用户不存在");
            }
            String message = userService.removeUser(u.getId());
            ar = new CommonResult<>(200, message);
            return ar;
        }catch (Exception e){
            return new CommonResult<>(200,"添加失败");
        }

    }


}
