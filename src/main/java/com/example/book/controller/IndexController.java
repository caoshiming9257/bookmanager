package com.example.book.controller;


import com.example.book.vo.BorrowVO;
import com.example.book.entity.Book;
import com.example.book.service.BookService;
import com.example.book.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @program: bookmanager
 * @Author: Simon_Cao
 * @Date: 2021/3/6 19:45
 * @Description:
 */
@Controller
public class IndexController {
    @Autowired
    BookService bookService;

    @Autowired
    BorrowService borrowService;


    /** 
      @Description: 通过id获取指定图书信息
      @Param: [id, model]
      @return: java.lang.String
      @Author: Simon_Cao
      @Date: 2021/3/8
     */ 
    @GetMapping("/bookEdit/{id}")
    public String bookEdit(@PathVariable Integer id, Model model){
        Book book = bookService.get(id);
        model.addAttribute("book",book);
        return "bookEdit";
    }


    /** 
      @Description: 根据id获取图书详细信息
      @Param: [id, model]
      @return: java.lang.String
      @Author: Simon_Cao
      @Date: 2021/3/8
     */ 
    @GetMapping("/bookDetail/{id}")
    public String book(@PathVariable Integer id, Model model){
        Book book = bookService.get(id);
        model.addAttribute("book",book);

        return "bookDetail";
    }


    /** 
      @Description: 通过图书id获取租借的全部信息
      @Param: [id, model]
      @return: java.lang.String
      @Author: Simon_Cao
      @Date: 2021/3/8
     */ 
    @GetMapping("/bookBorrowEdit/{id}")
    public String bookBorrowEdit(@PathVariable Integer id, Model model){
        BorrowVO bookBorrow = borrowService.get(id);
        model.addAttribute("bookBorrow",bookBorrow);

        return "bookBorrowEdit";
    }


    @GetMapping("/bookReBorrowEdit/{id}")
    public String bookReBorrowEdit(@PathVariable Integer id, Model model){
        BorrowVO bookBorrow = borrowService.get(id);
        model.addAttribute("bookBorrow",bookBorrow);

        return "bookReBorrowEdit";
    }
}
