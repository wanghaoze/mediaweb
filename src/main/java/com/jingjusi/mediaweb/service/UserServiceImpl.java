package com.jingjusi.mediaweb.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jingjusi.mediaweb.common.domain.*;
import com.jingjusi.mediaweb.common.utils.MyUserDetails;
import com.jingjusi.mediaweb.mapper.CourseMapper;
import com.jingjusi.mediaweb.mapper.CourseUserMapper;
import com.jingjusi.mediaweb.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;
    @Autowired
    CourseUserMapper courseUserMapper;
    @Autowired
    CourseMapper courseMapper;

    @Override
    public String addUser(User user) {
        int a =  userMapper.insert(user);
        if (a!=0) return "添加成功";
        else return "添加失败";
    }

    @Override
    public String updateUser(Long id, String username, String password, String email,String phone) {
        UserExample userExample = new UserExample();
        assert id!=null;
        userExample.createCriteria().andIdEqualTo(id);
        List<User> users = userMapper.selectByExample(userExample);
        if (users==null||users.isEmpty()) {
            return "用户不存在或密码错误";
        }
        User user = users.get(0);
        User newUser = new User();

        if (username!=null && !username.equals(""))newUser.setUsername(username);
        if (password!=null && !password.equals(""))newUser.setPassword(password);
        if (email!=null && !email.equals("")) newUser.setEmail(email);
        if (phone!=null && !phone.equals("")) newUser.setNumber(phone);
        userMapper.updateByExampleSelective(newUser,userExample);
        return "添加成功";
    }

    @Override
    public String removeUser(Long id) {
        try {
            userMapper.deleteByPrimaryKey(id);
            return "删除用户"+id+"成功！";
        } catch (Exception e) {
            System.out.println(e);
            return "用户不存在";
        }
    }

    @Override
    public User findUser(String userName) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(userName);
        List<User> users = userMapper.selectByExample(userExample);
        if (users==null||users.isEmpty()) {
            return null;
        }
    return users.get(0);
    }

    @Override
    public PageInfo<User> findUsersByName(String username, Integer pageNo, Integer pageSize) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameLike(username);
        List<User> users = new ArrayList<>(userMapper.selectByExample(userExample));
        if (users.isEmpty())
            return null;
        return new PageInfo<>(users);
    }

    @Override
    public PageInfo<User> findUsersByCourse(Long courseId, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<User> users = new ArrayList<User>();
        CourseUserExample courseUserExample = new CourseUserExample();
        courseUserExample.createCriteria().andCourseIdEqualTo(courseId);
        List<CourseUser> courseUsers = new ArrayList<>(courseUserMapper.selectByExample(courseUserExample));
        if (courseUsers.isEmpty())
            return null;
        for (CourseUser courseUser:courseUsers) {
            UserExample userExample = new UserExample();
            userExample.createCriteria().andIdEqualTo(courseUser.getUserId());
            users.addAll(userMapper.selectByExample(userExample));
        }
        if (users.isEmpty())
            return null;
        return new PageInfo<>(users);
    }

    @Override
    public PageInfo<User> findUsersByPage(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        UserExample userExample = new UserExample();
        userExample.createCriteria();
        List<User> users = new ArrayList<>(userMapper.selectByExample(userExample));
        if (users.isEmpty())
            return null;
        return new PageInfo<>(users);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(s);
        List<User> myUsers = userMapper.selectByExample(userExample);
        Optional<User> myUser;
        if (!myUsers.isEmpty())
            myUser = Optional.of(myUsers.get(0));
        else
            myUser = Optional.empty();
        myUser.orElseThrow(() -> new UsernameNotFoundException("Not Found:" + s));
        return myUser.map(MyUserDetails::new).get();
    }

    @Override
    public PageInfo<Course> findCourseByUserRole(Long userId, String role, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        CourseUserExample courseUserExample = new CourseUserExample();
        courseUserExample.createCriteria()
                .andUserIdEqualTo(userId)
                .andRolesLike("%"+role+"%");
        List<Course> courses = new ArrayList<>();
        List<CourseUser> courseUsers = new ArrayList<>();
        courseUsers = courseUserMapper.selectByExample(courseUserExample);
        for (CourseUser courseUser:courseUsers) {
            CourseExample courseExample = new CourseExample();
            courseExample.createCriteria().andIdEqualTo(courseUser.getCourseId());
            courses.addAll(courseMapper.selectByExample(courseExample));
        }
        if (courses.isEmpty())
            return null;
        return new PageInfo<>(courses);
    }
}
