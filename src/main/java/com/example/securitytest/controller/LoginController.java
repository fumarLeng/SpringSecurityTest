package com.example.securitytest.controller;

import com.example.securitytest.model.User;
import com.example.securitytest.result.ResponseResult;
import com.example.securitytest.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public ResponseResult<User> login(@RequestBody User user) {

        ResponseResult<User> result = loginService.login(user);

        return result;
    }


}
