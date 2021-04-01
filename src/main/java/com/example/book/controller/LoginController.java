package com.example.book.controller;

import com.example.book.service.SysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @program: bookmanager
 * @Author: Simon_Cao
 * @Date: 2021/3/6 19:45
 * @Description: 管理员登录
 */
@Controller
public class LoginController {

    @Resource
    public SysUserService userService;


    /** 
      @Description: 校验用户名和密码
      @Param: [request, username, password, model]
      @return: java.lang.String
      @Author: Simon_Cao
      @Date: 2021/3/8
     */ 
    @RequestMapping(value = {"/loginAdmin","/"},method = RequestMethod.GET)
    public String login(HttpServletRequest httpServletRequest){
        return "loginAdmin";
    }

    @RequestMapping(value = "/admin",method = RequestMethod.GET)
    public String indexAdmin(){
        return "admin";
    }
    /*public String login(HttpServletRequest request ,
                        @RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model){
        SysUser user=userService.selectUserByUsername(username);
        if(user!=null && Md5Util.encode(password).equals(user.getPassword())){
            HttpSession session= request.getSession();
            session.setAttribute("loginUser",user);
            return "admin";
        }else {
            model.addAttribute("msg","用户名或密码错误！");
            return "login";
        }
    }*/


}