package com.example.securitytest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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

        //用會員名稱拿到訊息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>();
        queryWrapper.eq(User::getUserName, username);
        User user = userMapper.selectOne(queryWrapper);

        //如果找不到要丟異常
        if (Objects.isNull(user)) {
            throw new RuntimeException("用戶名或密碼錯誤");
        }
        //這邊要根據用戶查詢權限訊息 加到LoginUser裡面 ,這邊先寫死
        List<String> permissionKeyList = menuMapper.selectPermsByUserId(user.getId());
        return new LoginUser(user, permissionKeyList);
    }
}
