package com.example.book.entity;

import lombok.Data;
import java.util.Date;

/**
 * @program: bookmanager
 * @Author: Simon_Cao
 * @Date: 2021/3/6 19:45
 * @Description: 借书
 */
@Data
public class Borrow extends BaseBean{
    private Integer readerId;
    private String bookId;
    private Date borrowDate;
    private Date returnDate;
    private Integer borrowDays;
    /**
     * 续借天数
     */
    private Integer reBorrowDays;
    /**
     * 续借天数
     */
    private Date realReturnDate;
    /**
     * 借阅状态 0未还；1已还；2逾期
     */
    private Integer borrowStatus;

}
