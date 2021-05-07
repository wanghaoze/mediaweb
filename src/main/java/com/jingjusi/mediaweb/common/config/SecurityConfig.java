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
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("ADMIN", "USER")
                .antMatchers("/videos/add").hasRole("ADMIN")
                .antMatchers("/videoUpload").hasRole("ADMIN")
                .antMatchers("/").permitAll()
                .and().formLogin()
                .successHandler(myAuthenticationSuccessHandler);
        http.cors().and().csrf().disable();
    }

    @Bean
    public PasswordEncoder PasswordEncoder() { return NoOpPasswordEncoder.getInstance();
    }
}
