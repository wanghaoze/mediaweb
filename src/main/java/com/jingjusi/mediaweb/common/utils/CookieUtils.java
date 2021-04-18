package com.jingjusi.mediaweb.common.utils;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
    // 默认cookie有效时间
    private static final Integer DEFAULT_TIME = 7*24*60*68;
    public static void createCookie(String username, HttpServletResponse response) {
        Cookie userCookie = new Cookie("username",username);
        Cookie ssidCookie = new Cookie("ssid", SignUtils.crypt(username));
        userCookie.setMaxAge(DEFAULT_TIME);
        ssidCookie.setMaxAge(DEFAULT_TIME);
        userCookie.setPath("/");
        ssidCookie.setPath("/");
        response.addCookie(userCookie);
        response.addCookie(ssidCookie);
    }
}
