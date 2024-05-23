package com.example.securitytest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.securitytest.mapper.MenuMapper;
import com.example.securitytest.mapper.UserMapper;
import com.example.securitytest.model.User;
import com.example.securitytest.vo.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("進入 UserDetailsServiceImpl 了");
        System.out.println(username);
        //用會員名稱拿到訊息
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        System.out.println("queryWrapper" );
        queryWrapper.eq("user_name", username);
        User user = userMapper.selectOne(queryWrapper);
        if (Objects.isNull(user)) {
            System.out.println("是NULL");
        } else {
            System.out.println("loadUserByUsername : " + user);

        }

        //如果找不到要丟異常
        if (Objects.isNull(user)) {
            throw new RuntimeException("用戶名或密碼錯誤");
        }
        //這邊要根據用戶查詢權限訊息 加到LoginUser裡面 ,這邊先寫死
        List<String> permissionKeyList = menuMapper.selectPermsByUserId(user.getId());
        return new LoginUser(user, permissionKeyList);
    }
}
