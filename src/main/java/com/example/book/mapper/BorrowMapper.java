package com.example.book.mapper;

import com.example.book.vo.BorrowVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @program: bookmanager
 * @Author: Simon_Cao
 * @Date: 2021/3/6 19:45
 * @Description: 借书
 */
@Mapper
public interface BorrowMapper {
    /**
     * 插入借阅信息
     * @param entity BorrowVO实体
     */
    void insert(BorrowVO entity);

    /**
     * 更新借阅信息
     * @param entity BorrowVO实体
     */
    void update(BorrowVO entity);

    /**
     * 批量删除借阅信息
     * @param ids 需要被删除的id数组
     */
    void deletes(Integer[] ids);

    /**
     * 还书
     * @param param Map集合
     */
    void returnBook(Map<String, Object> param);

    /**
     * 查询一个BorrowVO
     * @param id id
     * @return BorrowVO
     */
    BorrowVO select(Integer id);

    /**
     * 统计数量
     * @param param Map集合
     * @return 数量
     */
    int count(Map<String, Object> param);

    /**
     * 分页信息
     * @param param Map集合
     * @return List<BorrowVO>
     */
    List<BorrowVO> selectPageResult(Map<String, Object> param);

    /**
     * 统计借阅数量
     * @param entity  BorrowVO实体
     * @return 数量
     */
    int countBorrow(BorrowVO entity);

    /**
     * 统计未还图书
     * @param date 图书借出日期
     * @return 数量
     */
    int selectBorrowCount(String date);

    /**
     * 统计已还的图书数量
     * @param date 图书实际归还日期
     * @return 数量
     */
    int selectReturnCount(String date);
}
