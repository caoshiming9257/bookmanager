package com.example.book.entity;


import lombok.Data;

/**
  @Description: 读者信息
  @Param:
  @return:
  @Author: Simon_Cao
  @Date: 2021/3/8
 */
@Data
public class Reader extends BaseBean{
    private String name;
    private String phone;
    private double fine;
}
