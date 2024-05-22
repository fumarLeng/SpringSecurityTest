package com.example.securitytest.mapper;

import com.example.securitytest.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectMapper() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void insertMapper() {
        User user = new User();
        user.setId(1L);
        user.setUserName("user1");
        user.setPassword("user1");
        user.setNickName("使用者1");
        user.setStatus("0");
        user.setSex("0");
        user.setUserType("1");
        user.setCreateBy(1L);
        user.setCreateTime(new Date());
        user.setUpdateBy(1L);
        user.setUpdateTime(new Date());
        int count = userMapper.insert(user);

        System.out.println("新增成功 : " + count + "筆");
    }

    @Test
    public void tteesstt() {
        int i = 0;
        for (; i < 10; i++) {
            System.out.println("Hello" + (i = i + 1));
        }
    }
}
