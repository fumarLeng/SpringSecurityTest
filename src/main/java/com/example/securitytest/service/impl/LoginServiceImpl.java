package com.example.securitytest.service.impl;

import com.example.securitytest.model.User;
import com.example.securitytest.result.ResponseResult;
import com.example.securitytest.service.LoginService;
import com.example.securitytest.util.JwtUtil;
import com.example.securitytest.util.RedisCache;
import com.example.securitytest.vo.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        //先用username跟password封裝成認證用的訊息令牌
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        System.out.println(user);
        //再用這個認證訊息令牌去認證
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authentication)) {
            throw new RuntimeException("用戶名或密碼錯誤");
        }

        //用userid生成token
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        System.out.println(loginUser);
        Long userId = loginUser.getUser().getId();
        System.out.println(userId);
        String jwt = JwtUtil.createToken(userId);

        //authenticate存到redis
        redisCache.setCacheObject("login:" + userId, loginUser);
        //再把token給前端
        HashMap<String, String> map = new HashMap<>();
        map.put("token", "ABC");
        return new ResponseResult(200, "登入成功", map);
    }

    @Override
    public ResponseResult<Void> logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        redisCache.deleteObject("login:" + userid);
        return new ResponseResult<>(200, "登出成功");
    }
}
