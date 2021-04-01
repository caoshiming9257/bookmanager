//package com.example.book.interceptor;
//
//
//import com.example.book.entity.SysUser;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
///**
// * @program: bookmanager
// * @Author: Simon_Cao
// * @Date: 2021/3/6 19:45
// * @Description:  session超时拦截器
// */
//@Component
//public class SessionTimeoutInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        HttpSession session = request.getSession();
//        // 从session中获取用户信息
//        SysUser loginUser = (SysUser) session.getAttribute("loginUser");
//
//        // session过期
//        if(loginUser == null){
//            // 通过接口跳转登录页面
////            response.sendRedirect("/login");
//            return false;
//        }else{
//            return true;
//        }
//    }
//}
