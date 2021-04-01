package com.example.book.mapper;

import com.example.book.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: bookmanager
 * @Author: Simon_Cao
 * @Date: 2021/3/6 19:45
 * @Description: 管理员信息
 */
@Mapper
public interface SysUserMapper{
    /**
     * 通过姓名查找用户
     * @param username
     * @return User
     */
    SysUser selectUserByUsername(String username);

    /**
     * 更新用户信息
     * @param user
     */
    void update(SysUser user);
    
    /** 
      @Description: 
      @Param: [sysUser]
      @return: void
      @Author: Simon_Cao
      @Date: 2021/3/9
     */ 
    void insert(SysUser sysUser);
}
