package com.example.book.mapper;

import com.example.book.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author Liutianci
 */
@Mapper
public interface BookMapper {
    /**
     *更新书籍信息
     * @param book 书籍信息
     * @return 没有返回值
     */
    void update(Book book);
    /**
     *增加书籍
     * @param book 书籍信息
     * @return 没有返回值
     */
    void insert(Book book);
    /**
     *批量删除书籍
     * @param ids 书籍的id
     * @return 没有返回值
     */
    void deletes(Integer[] ids);
    /**
     *通过id查书籍信息
     * @param id 书籍信息
     * @return Book实体
     */
    Book select(Integer id);
    /**
     *统计书籍数量
     * @param param
     * @return 返回书籍数量
     */
    int count(Map<String, Object> param);
    /**
     *分页信息
     * @param param
     * @return List<Book>
     */
    List<Book> selectPageResult(Map<String, Object> param);
    /**
     *通过ISBN查书籍信息
     * @param bookIsbn 书籍ISBN
     * @return Book实体
     */
    Book selectByIsbn(String bookIsbn);
    /**
     *统计书籍数量
     * @param book
     * @return 返回书籍数量
     */
    Integer countIsbn(Book book);
    /**
     *通过ID查书籍信息
     * @param id
     * @return 返回书籍数量
     */
    Book selectById(Integer id);
}
