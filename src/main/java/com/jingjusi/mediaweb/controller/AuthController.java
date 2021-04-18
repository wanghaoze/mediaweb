package com.jingjusi.mediaweb.controller;

import com.jingjusi.mediaweb.common.domain.User;
import com.jingjusi.mediaweb.common.utils.SignUtils;
import com.jingjusi.mediaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

@RestController
public class AuthController {
    @Autowired
    UserService userService;

    
//    @RequestMapping(value = "/login", method = {RequestMethod.GET,RequestMethod.POST})
//    @ResponseBody
//    public Response login(String nickname, String password, String code, HttpServletRequest request, HttpServletResponse response) {
//        HttpSession session = request.getSession();
//        Response loginResponse = userService.login(nickname,password,code);
//        System.out.println(loginResponse.getContext());
//        return loginResponse;
//    }

    @GetMapping("/user")
    public String user() {
        return "<h1>hello user</h1>";
    }


    @GetMapping("/admin")
    public String admin() {
        return "<h1>hello admin</h1>";
    }

//    @RequestMapping("/toMain")
//    public String toMainPage(Model model, String flag, HttpServletRequest request, HttpServletResponse response) {
//        HttpSession session = request.getSession();
//        Cookie[] cookies = request.getCookies();
//        String username = "";
//        String ssid = "";
//        boolean equals = false;
//        if (cookies!=null && cookies.length>0) {
//            for (Cookie cookie:cookies) {
//                if (cookie.getName().equals("username"))
//                    username = cookie.getValue();
//                if (cookie.getName().equals("ssid"))
//                    ssid = cookie.getValue();
//            }
//        }
//        if (username!=null && ssid!=null){
//            equals = ssid.equals(SignUtils.crypt(username));
//        }
//        if (equals) {
//            User user = userService.findUser(username);
//            if (user!=null) {
//                session.setAttribute("userSessionKey",user);
//            }
//        }
//        return "main";
//    }
}
