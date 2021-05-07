package com.jingjusi.mediaweb.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class MyAuthenticationSucessHandler implements AuthenticationSuccessHandler {
    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        //response.setContentType("application/json;charset=utf-8");
        //response.getWriter().write(objectMapper.writeValueAsString(authentication));
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        String user = ((UserDetails)authentication.getPrincipal()).getUsername();
        request.getSession().setAttribute("user", user);
        if (savedRequest == null){
            redirectStrategy.sendRedirect(request, response, "/index");
        }else {
            System.out.println(savedRequest.getRedirectUrl());
            redirectStrategy.sendRedirect(request, response, savedRequest.getRedirectUrl());
        }

    }
}
