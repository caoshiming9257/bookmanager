package com.example.book.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @program: bookmanager
 * @Author: Simon_Cao
 * @Date: 2021/3/6 19:45
 * @Description: 管理员信息
 */

@Data
@NoArgsConstructor
public class SysUser implements UserDetails {


    private Integer id;
    private String username;
    private String password;

    /*是否启用*/
    private boolean isenable;
    /*是否锁定*/
    private boolean islock;
    /*凭证是否过期*/
    private boolean iscredentials;
    /*是否过期*/
    private boolean isexpired;

    /*获取授权*/
    private List<GrantedAuthority> getAuthorities;


    public SysUser(String username, String password, boolean isenable, boolean islock, boolean iscredentials, boolean isexpired, List<GrantedAuthority> getAuthorities) {
        this.username = username;
        this.password = password;
        this.isenable = isenable;
        this.islock = islock;
        this.iscredentials = iscredentials;
        this.isexpired = isexpired;
        this.getAuthorities = getAuthorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isexpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return islock;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return iscredentials;
    }

    @Override
    public boolean isEnabled() {
        return isenable;
    }
}
