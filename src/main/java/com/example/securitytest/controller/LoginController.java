package com.example.securitytest.controller;

import com.example.securitytest.config.SecurityConfig;
import com.example.securitytest.model.User;
import com.example.securitytest.result.ResponseResult;
import com.example.securitytest.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private SecurityConfig securityConfig;

    @PostMapping("/user/login")
    public ResponseResult<User> login(@RequestBody User user) {

        ResponseResult<User> result = loginService.login(user);

        return result;
    }

    @PostMapping("/user/logout")
    public ResponseResult logout() {

        ResponseResult result = loginService.logout();

        return result;
    }

    @GetMapping("/user/test/{password}")
    public String test(@PathVariable String password) {
        String pwd = securityConfig.passwordEncoder().encode(password);
        return pwd;
    }


}
