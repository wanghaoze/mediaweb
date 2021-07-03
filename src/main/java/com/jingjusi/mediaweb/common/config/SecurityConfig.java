package com.jingjusi.mediaweb.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jingjusi.mediaweb.common.utils.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Qualifier("userServiceImpl")
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    AuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/manage/users").hasRole("ADMIN")
                .antMatchers("/userManage/**").hasAnyRole("ADMIN")
                .antMatchers("/manage/videos").hasAnyRole("ADMIN","STUDY")
                .antMatchers("/manage/courses").hasAnyRole("ADMIN","STUDY")
                .antMatchers("/courseManage/**").hasAnyRole("ADMIN","STUDY")
                .antMatchers("/manage/video/delete").hasAnyRole("ADMIN","STUDY")
                .antMatchers("/videoUpload").hasAnyRole("ADMIN","STUDY")
                .antMatchers("/manage/books").hasAnyRole("ADMIN","LIBRARY")
                .antMatchers("/manage/borrows").hasAnyRole("ADMIN","LIBRARY")
                .antMatchers("/bookManage/**").hasAnyRole("ADMIN","LIBRARY")
                .antMatchers("/manage/transactions").hasAnyRole("ADMIN","TRANSACTION")
                .antMatchers("/transactionManage/**").hasAnyRole("ADMIN","TRANSACTION")
//                .antMatchers("/user").hasAnyRole("ADMIN", "USER")
                .antMatchers("/manage/tablets").hasAnyRole("ADMIN","TABLET")
                .antMatchers("/tabletManage/**").hasAnyRole("ADMIN","TABLET")
                .antMatchers("/course/**").hasAnyRole("ADMIN","STUDY","USER","LIBRARY","TRANSACTION","TABLET")
                .antMatchers("/").permitAll()
                .and().formLogin()
                .successHandler(myAuthenticationSuccessHandler)
                .and().sessionManagement().invalidSessionUrl("/login");
        http.cors().and().csrf().disable();
    }

    @Bean
    public PasswordEncoder PasswordEncoder() { return NoOpPasswordEncoder.getInstance();
    }
}
