package com.example.book.vo;

import lombok.Data;

import java.util.List;

/**
 * @program: bookmanager
 * @Author: Simon_Cao
 * @Date: 2021/3/6 19:45
 * @Description:
 */
@Data
public class BorrowStatVO {
    private String name;
    private String type;
    private List<Integer> data;
}
