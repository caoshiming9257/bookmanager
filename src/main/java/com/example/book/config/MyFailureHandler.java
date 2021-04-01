package com.example.book.config;

import com.example.book.vo.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: bookmanager
 * @Author: Simon_Cao
 * @Date: 2021/3/9 21:35
 * @Description:
 */
@Component
public class MyFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("text/json;charset=utf-8");
        ServletOutputStream out = httpServletResponse.getOutputStream();
        Result result = new Result();
        result.setCode(1);
        result.setError(1001);
        result.setMsg("登录失败（用户名或密码有误）");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(out,result);
        out.flush();
        out.close();
    }
}
