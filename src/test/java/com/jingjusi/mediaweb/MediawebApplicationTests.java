package com.jingjusi.mediaweb;

import com.jingjusi.mediaweb.common.domain.User;
import com.jingjusi.mediaweb.mapper.UserMapper;
import com.jingjusi.mediaweb.service.UserService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan(basePackages = "com.jingjusi.mediaweb.mapper")
class MediawebApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Test
    void contextLoads() {
    }

    @Test
    void addusers() {
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setUsername("a"+i);
            user.setPassword("test");
            user.setEmail(i+"@123.com");
            user.setNumber(i+"4");
            user.setFirstName("asd");
            user.setLastName("asd");
            user.setIsStaff(false);
            user.setIsActive(false);
            user.setRemarks(null);
            userMapper.insert(user);
        }
    }

}
