package com.jingjusi.mediaweb.controller;

import com.jingjusi.mediaweb.common.domain.User;
import com.jingjusi.mediaweb.common.utils.CommonResult;
import com.jingjusi.mediaweb.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(value="用户controller",tags={"用户操作接口"})
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/addUser", method = {RequestMethod.GET, RequestMethod.POST})
    public CommonResult<String> addUser(@RequestParam(value = "username")String username,
                                        @RequestParam(value = "password") String pswd,
                                        @RequestParam(value = "email") String email,
                                        @RequestParam(value = "number") String number,
                                        @RequestParam(value = "firstname")String firstname,
                                        @RequestParam(value = "lastname")String lastname,
                                        HttpServletRequest request
    ) {
        User new_user = new User();
        new_user.setUsername(username);
        new_user.setPassword(pswd);
        new_user.setEmail(email);
        new_user.setNumber(number);
        new_user.setFirstName(firstname);
        new_user.setLastName(lastname);
        new_user.setIsStaff(false);
        new_user.setIsActive(false);
        new_user.setRemarks(null);
        String message = userService.addUser(new_user);
        return new CommonResult<>(200,message, username);
    }

//    @RequestMapping(value = "/gateway", method = {RequestMethod.GET, RequestMethod.POST})
//    public CommonResult<String> checkUser(@RequestParam(value = "username")String username,
//                                          @RequestParam(value = "password")String password,
//                                          HttpServletRequest request) {
//        User user = userService.findUser(username);
//        if (user.getPassword().equals(password)) {
//            return new CommonResult<>(200,username+"登录成功", username);
//        } else {
//            return new CommonResult<>(404, "用户名或密码错误", username);
//        }
//    }

}
