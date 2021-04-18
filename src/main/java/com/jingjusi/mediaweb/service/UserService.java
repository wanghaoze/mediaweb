package com.jingjusi.mediaweb.service;

import com.github.pagehelper.PageInfo;
import com.jingjusi.mediaweb.common.domain.Course;
import com.jingjusi.mediaweb.common.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
public interface UserService extends UserDetailsService {
    public String addUser(User user);
    public String updateUser(Long id, String username, String password, String email,String phone);
    public String removeUser(Long id);
    public User findUser(String userName);
    public PageInfo<User> findUsersByName(String username, Integer pageNo, Integer pageSize);
    public PageInfo<User> findUsersByCourse(Long courseId, Integer pageNo, Integer pageSize);
    public PageInfo<User> findUsersByPage(Integer pageNo, Integer pageSize);
    public PageInfo<Course> findCourseByUserRole(Long userId, String role, Integer pageNo, Integer pageSize);
}
