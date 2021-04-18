package com.jingjusi.mediaweb.common.utils;

import com.jingjusi.mediaweb.common.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {

    private String userName;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;

    public MyUserDetails(User user) {
        this.userName = user.getUsername();
        this.password = user.getPassword();
        this.active = user.getIsActive();
        this.authorities = Arrays.stream(user.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
    public MyUserDetails() {
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))
        && this.authorities.contains(new SimpleGrantedAuthority("ROLE_USER")) ) {
            return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"),new SimpleGrantedAuthority("ROLE_USER"));
        }
        if (this.authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))){
            return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));

        }
        if (this.authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
