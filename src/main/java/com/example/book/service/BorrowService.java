package com.example.book.service;


import com.example.book.mapper.ReaderMapper;
import com.example.book.redis.RedisCache;
import com.example.book.vo.BorrowVO;
import com.example.book.entity.Borrow;
import com.example.book.mapper.BorrowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @program: bookmanager
 * @Author: Simon_Cao
 * @Date: 2021/3/6 19:45
 * @Description: 借书管理
 */
@Service
public class BorrowService {

    @Resource
    public BorrowMapper borrowMapper;
    @Resource
    public ReaderMapper readerMapper;

    @Resource
    RedisCache redisCache;

    public void add(BorrowVO entity) {
        borrowMapper.insert(entity);
    }

    public void modify(BorrowVO entity) {
        borrowMapper.update(entity);
    }

    public void removes(Integer[] ids) {
        borrowMapper.deletes(ids);
    }

    public void returnBook(Map<String, Object> param) {
        borrowMapper.returnBook(param);
    }

    /**
     * 查询一个BorrowVO
     * @param id id
     * @return BorrowVO
     */
    public BorrowVO get(Integer id) {
        BorrowVO idBorrowVO = redisCache.getCacheObject("IdBorrowVO");
        if (idBorrowVO == null){
            redisCache.setCacheObject("IdBorrowVO",borrowMapper.select(id),10, TimeUnit.MINUTES);
            return borrowMapper.select(id);
        }
        return idBorrowVO;
    }

    /**
     * 统计数量
     * @param map Map集合
     * @return 数量
     */
    public int count(Map<String, Object> map) {
        Object mapCount = redisCache.getCacheObject("mapCount");
        if (mapCount == null){
            redisCache.setCacheObject("mapCount",borrowMapper.count(map),10,TimeUnit.MINUTES);
            return borrowMapper.count(map);
        }
        return (Integer)mapCount;
    }

    /**
     * 分页信息
     * @param map Map集合
     * @return List<BorrowVO>
     */
    public List<BorrowVO> getPageResult(Map<String, Object> map) {
        List<BorrowVO> mapBorrowVOList = redisCache.getCacheList("mapBorrowVOList");
        if (mapBorrowVOList.isEmpty()){
            redisCache.setCacheList("mapBorrowVOList",borrowMapper.selectPageResult(map));
            redisCache.expire("mapBorrowVOList",10,TimeUnit.MINUTES);
            return borrowMapper.selectPageResult(map);
        }
        return mapBorrowVOList;
    }

    /**
     * 统计借阅数量
     * @param entity  BorrowVO实体
     * @return 数量
     */
    public int checkBorrow(BorrowVO entity){
        Object borrowVOCount = redisCache.getCacheObject("borrowVOCount");
        if (borrowVOCount == null){
            redisCache.setCacheObject("borrowVOCount",borrowMapper.countBorrow(entity),10,TimeUnit.MINUTES);
            return borrowMapper.countBorrow(entity);
        }
        return (Integer)borrowVOCount;
    }

    /**
     * 统计未还图书
     * @param date 图书借出日期
     * @return 数量
     */
    public int getBorrowCount(String date){
        Object dateCount = redisCache.getCacheObject("dateCount");
        if (dateCount == null){
            redisCache.setCacheObject("dateCount",borrowMapper.selectBorrowCount(date),10,TimeUnit.MINUTES);
            return borrowMapper.selectBorrowCount(date);
        }
        return (Integer)dateCount;
    }

    /**
     * 统计已还的图书数量
     * @param date 图书实际归还日期
     * @return 数量
     */
    public int getReturnCount(String date){
        Object dateCountY = redisCache.getCacheObject("dateCountY");
        if (dateCountY == null){
            redisCache.setCacheObject("dateCountY",borrowMapper.selectReturnCount(date),10,TimeUnit.MINUTES);
            return borrowMapper.selectReturnCount(date);
        }
        return (Integer)dateCountY;
    }

    /**
     * 查询罚款
     * @param id
     * @return 罚款金额
     */
    public double checkFine(Integer id){
        Object penalty = redisCache.getCacheObject("penalty");
        if (penalty == null){
            redisCache.setCacheObject("penalty",readerMapper.checkFine(id),10,TimeUnit.MINUTES);
            return readerMapper.checkFine(id);
        }
        return (Double)penalty;
    }
}
