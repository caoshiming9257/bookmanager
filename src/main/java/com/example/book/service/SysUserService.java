package com.example.book.service;


import com.example.book.entity.SysUser;
import com.example.book.mapper.SysUserMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: bookmanager
 * @Author: Simon_Cao
 * @Date: 2021/3/6 19:45
 * @Description:
 */
@Service
public class SysUserService implements UserDetailsService {

    @Resource
    public SysUserMapper sysUserMapper;

//    public SysUser selectUserByUsername(String username) {
//        return sysUserMapper.selectUserByUsername(username);
//    }

    public void modify(SysUser user) {
        sysUserMapper.update(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = null;
        if (username != null){
            sysUser = sysUserMapper.selectUserByUsername(username);
            if (sysUser != null){
                List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_"+sysUser.getRole());
                grantedAuthorities.add(grantedAuthority);
                sysUser.setGetAuthorities(grantedAuthorities);
                return sysUser;
            }

        }
        return sysUser;
    }
}
