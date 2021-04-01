package com.example.book.init;

import com.example.book.entity.SysUser;
import com.example.book.mapper.SysUserMapper;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @program: bookmanager
 * @Author: Simon_Cao
 * @Date: 2021/3/9 15:29
 * @Description:
 */
//@Component
public class SysUserInitial {

    @Resource
    SysUserMapper sysUserMapper;

//    @PostConstruct
    public void initUser(){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        SysUser sysUser = new SysUser();
        sysUser.setUsername("admin");
        sysUser.setPassword(passwordEncoder.encode("123"));
        sysUser.setRole("ADMIN");

        sysUserMapper.insert(sysUser);
    }
}
