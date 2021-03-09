package com.example.book.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @program: bookmanager
 * @Author: Simon_Cao
 * @Date: 2021/3/6 19:45
 * @Description: 管理员信息
 */

@Data
@AllArgsConstructor
public class User{

    private Integer id;
    private String username;
    private String password;

}
