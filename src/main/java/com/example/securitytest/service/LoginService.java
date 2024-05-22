package com.example.securitytest.service;

import com.example.securitytest.model.User;
import com.example.securitytest.result.ResponseResult;

public interface LoginService {

    ResponseResult<User> login(User user);

    ResponseResult<Void> logout();
}
