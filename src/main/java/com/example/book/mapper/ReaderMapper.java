package com.example.book.mapper;

import com.example.book.entity.Reader;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @program: bookmanager
 * @Author: Simon_Cao
 * @Date: 2021/3/6 19:45
 * @Description: 读者信息
 */
@Mapper
public interface ReaderMapper {
    /**
     * 插入读者信息
     * @param entity
     */
    void insert(Reader entity);

    /**
     * 更新读者信息
     * @param entity
     */
    void update(Reader entity);

    /**
     * 批量删除读者信息
     * @param ids
     */
    void deletes(Integer[] ids);

    /**
     * 查询读者
     * @param id
     * @return Reader
     */
    Reader select(Integer id);

    /**
     * 统计数量
     * @param
     * @return 数量
     */
    int count();

    /**
     * 查询读者列表
     * @param param
     * @return List<Reader>
     */
    List<Reader> selectList(Map<String, Object> param);

    /**
     * 分页信息
     * @param param
     * @return List<Reader>
     */
    List<Reader> selectPageResult(Map<String, Object> param);

    /**
     * 查询数量通过手机号
     * @param entity
     * @return 数量
     */
    int countPhone(Reader entity);

    /**
     * 查询罚款
     * @param id
     * @return 罚款金额
     */
    double checkFine(int id);
}
