package com.example.book.vo;

import lombok.Data;

/**
 * @program: bookmanager
 * @Author: Simon_Cao
 * @Date: 2021/3/9 21:37
 * @Description:
 */
@Data
public class Result {
    /*o表示成功   1表示失败*/
    private int code;

    /*表示错误码*/
    private int error;

    /*消息文本*/
    private String msg;
}
