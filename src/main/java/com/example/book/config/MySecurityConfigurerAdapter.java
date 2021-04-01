package com.example.book.config;

import com.example.book.service.SysUserService;
import org.springframework.boot.autoconfigure.security.servlet.WebSecurityEnablerConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @program: bookmanager
 * @Author: Simon_Cao
 * @Date: 2021/3/9 16:04
 * @Description: security配置类
 */
@Configuration
public class MySecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Resource
    SysUserService sysUserService;

    @Resource
    DataSource dataSource;

    @Resource
    MyFailureHandler myFailureHandler;

    @Resource
    MySuccessHandler mySuccessHandler;

    /**
     * 自定义用户
     * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(sysUserService);
    }

    /**
     * 加密方式
     * */
    @Bean
    public PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
    }

    /**
     *token持久化对象
     * */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        jdbcTokenRepository.setCreateTableOnStartup(false);
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/loginAdmin","/static/**","/error.htnl").permitAll()
                .antMatchers("/api/book/**").hasRole("ADMIN")
                .antMatchers("/api/borrow/**").hasRole("ADMIN")
                .antMatchers("/api/reader/**").hasRole("ADMIN")
                .antMatchers("/api/user/**").hasRole("ADMIN")
                .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/loginAdmin")  //自定义登录页面
                .loginProcessingUrl("/loginAdmin")   //form中登录访问的url
                .defaultSuccessUrl("/admin",true)
//                .failureHandler(myFailureHandler)
//                .successHandler(mySuccessHandler)
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository()) // 配置 token 持久化仓库
                .tokenValiditySeconds(3600) // remember 过期时间，单为秒
                .userDetailsService(sysUserService) // 处理自动登录逻辑
                .and()
                .headers().frameOptions().disable()
                .and()
                .csrf().disable();
    }
}
