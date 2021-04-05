package com.example.book.service;

import com.example.book.entity.Book;
import com.example.book.mapper.BookMapper;
import com.example.book.redis.RedisCache;
import com.example.book.vo.BorrowVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/** 
  @Description: 图书管理
  @Param: 
  @return: 
  @Author: Simon_Cao
  @Date: 2021/3/8
 */ 
@Service
public class BookService {

    @Resource
    public BookMapper bookMapper;

    @Resource
    RedisCache redisCache;


    public void modify(Book book) {
        bookMapper.update(book);
    }

    public void add(Book book) {
        bookMapper.insert(book);
    }

    /**
     *统计书籍数量
     * @param book
     * @return 返回书籍数量
     */
    public Integer checkIsbn(Book book) {

        Object bookCount = redisCache.getCacheObject("bookCount");
        if (bookCount == null){
            redisCache.setCacheObject("bookCount",bookMapper.countIsbn(book),10, TimeUnit.MINUTES);
            return bookMapper.countIsbn(book);
        }
        return (Integer) bookCount;
    }

    public void removes(Integer[] ids) {
        bookMapper.deletes(ids);
    }

    /**
     *通过id查书籍信息
     * @param id 书籍信息
     * @return Book实体
     */
    public Book get(Integer id) {
        Book book = redisCache.getCacheObject("bookId");
        if (book == null){
            redisCache.setCacheObject("bookId",bookMapper.select(id),30, TimeUnit.MINUTES);
           return bookMapper.select(id);
        }
        return book;
    }

    /**
     *统计书籍数量
     * @param map
     */
    public int count(Map<String, Object> map) {
        Object mapCount = redisCache.getCacheObject("mapCount");
        if (mapCount == null){
            redisCache.setCacheObject("mapCount",bookMapper.count(map),10, TimeUnit.MINUTES);
            return bookMapper.count(map);
        }
        return (Integer) mapCount;
    }

    /**
     * 所有图书分页信息
     * */
    public List<Book> getPageResult(Map<String, Object> param) {
        List<Book> bookList = redisCache.getCacheList("bookList");
        if (bookList.isEmpty()){
            redisCache.setCacheList("bookList",bookMapper.selectPageResult(param));
            redisCache.expire("bookList",10,TimeUnit.MINUTES);
            return bookMapper.selectPageResult(param);
        }
        return bookList;
    }

    public Book getByIsbn(String bookIsbn) {
        return bookMapper.selectByIsbn(bookIsbn);
    }

    /**
     *通过ID查书籍信息
     * @param id
     * @return 返回书籍数量
     */
    public Book getById(Integer id) {
        Book idBook = redisCache.getCacheObject("idBook");
        if (idBook == null){
            redisCache.setCacheObject("idBook",bookMapper.selectById(id),10, TimeUnit.MINUTES);
            return bookMapper.selectById(id);
        }

        return idBook;
    }
}
