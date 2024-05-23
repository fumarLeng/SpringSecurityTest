package com.example.securitytest.exception;

import com.alibaba.fastjson.JSON;
import com.example.securitytest.result.ResponseResult;
import com.example.securitytest.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 抓取認證過程中的例外
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {

        ResponseResult result = new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "認證失敗重新登入");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response, json);
    }
}
