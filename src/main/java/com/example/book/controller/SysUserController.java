package com.example.book.controller;

import com.example.book.entity.SysUser;
import com.example.book.framework.Result;
import com.example.book.framework.ResultUtil;
import com.example.book.service.SysUserService;
import com.example.book.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @program: bookmanager
 * @Author: Simon_Cao
 * @Date: 2021/3/6 19:45
 * @Description:  管理员密码修改
 */
@RestController
@RequestMapping("/api/user")
public class SysUserController {
    @Autowired
    SysUserService userService;

    @PostMapping("/modifyPassword")
    public Result<String> modifyPassword(HttpServletRequest request, @RequestBody Map<String,String> param){
        // 校验密码
        String oldPassword = param.get("oldPassword");
        String newPassword = param.get("newPassword");
        String newPassword2 = param.get("newPassword2");
        if(oldPassword.isEmpty()){
            return ResultUtil.fail("原密码不能为空！");
        }
        if(newPassword.isEmpty()){
            return ResultUtil.fail("新密码不能为空！");
        }
        if(newPassword2.isEmpty()){
            return ResultUtil.fail("确认密码不能为空！");
        }
        HttpSession session = request.getSession();
        // 从session中获取用户信息
        SysUser loginUser = (SysUser) session.getAttribute("loginUser");
        String realPassword = loginUser.getPassword();
        if(!(Md5Util.encode(oldPassword).equals(realPassword))){
            return ResultUtil.fail("原密码输入错误！");
        }
        if(!(newPassword.equals(newPassword2))){
            return ResultUtil.fail("新密码输入不一致！");
        }
        loginUser.setPassword(Md5Util.encode(newPassword));
        userService.modify(loginUser);
        return ResultUtil.ok();
    }


}
