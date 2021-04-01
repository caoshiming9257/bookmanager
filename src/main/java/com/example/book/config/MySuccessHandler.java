package com.example.book.config;

import com.example.book.vo.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: bookmanager
 * @Author: Simon_Cao
 * @Date: 2021/3/09
 * @Description: spring security 通过ajax验证成功后的接口实现类
 */
@Component
public class MySuccessHandler implements AuthenticationSuccessHandler {

    /**
      @Description:
      @Param: [httpServletRequest, httpServletResponse, authentication: spring security验证用户信息成功后的封装类]
      @return: void
      @Author: Simon_Cao
      @Date: 2021/2/23
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest
            , HttpServletResponse httpServletResponse
            , Authentication authentication) throws IOException, ServletException {

        httpServletResponse.setContentType("text/json;charset=utf-8");
//        PrintWriter writer = httpServletResponse.getWriter();
        //手动输出json文件需要对符号进行转义，否则前台无法获取json数据
//        writer.println("{\"mesg\":\"登录成功\"}");
//        writer.flush();
//        writer.close();

        ServletOutputStream out = httpServletResponse.getOutputStream();
        Result result = new Result();
        result.setCode(0);
        result.setError(1000);
        result.setMsg("登录成功");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(out,result);
        out.flush();
        out.close();

    }
}
