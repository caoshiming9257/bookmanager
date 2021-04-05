package com.example.book.service;

import com.example.book.entity.Reader;
import com.example.book.mapper.ReaderMapper;
import com.example.book.redis.RedisCache;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @program: bookmanager
 * @Author: Simon_Cao
 * @Date: 2021/3/6 19:45
 * @Description: 读者管理
 */
@Service
public class ReaderService {

    @Resource
    ReaderMapper readerMapper;

    @Resource
    RedisCache redisCache;

    public void add(Reader entity) {
        readerMapper.insert(entity);
    }

    public void modify(Reader entity) {
        readerMapper.update(entity);
    }

    public void removes(Integer[] ids) {
        readerMapper.deletes(ids);
    }

    /**
     * 查询读者
     * @param id
     * @return Reader
     */
    public Reader get(Integer id) {
        Reader idReader = redisCache.getCacheObject("idReader");
        if (idReader == null){
            redisCache.setCacheObject("idReader",readerMapper.select(id),10, TimeUnit.MINUTES);
            return readerMapper.select(id);
        }
        return idReader;
    }

    /**
     * 统计数量
     * @param
     * @return 数量
     */
    public int count() {
        Object count = redisCache.getCacheObject("count");
        if (count == null) {
            redisCache.setCacheObject("count",readerMapper.count(),10,TimeUnit.MINUTES);
            return readerMapper.count();
        }
        return (Integer)count;
    }

    /**
     * 查询读者列表
     * @param map
     * @return List<Reader>
     */
    public List<Reader> getList(Map<String, Object> map) {
        List<Reader> mapReaderList = redisCache.getCacheList("mapReaderList");
        if (mapReaderList.isEmpty()){
            redisCache.setCacheList("mapReaderList",readerMapper.selectList(map));
            redisCache.expire("mapReaderList",10,TimeUnit.MINUTES);
            return readerMapper.selectList(map);
        }
        return mapReaderList;
    }

    /**
     * 分页信息
     * @param map
     * @return List<Reader>
     */
    public List<Reader> getPageResult(Map<String, Object> map) {
        List<Reader> mapPageReader = redisCache.getCacheList("mapPageReader");
        if (mapPageReader.isEmpty()){
            redisCache.setCacheList("mapPageReader",readerMapper.selectPageResult(map));
            redisCache.expire("mapPageReader",10,TimeUnit.MINUTES);
            return readerMapper.selectPageResult(map);
        }
        return mapPageReader;
    }

    /**
     * 查询数量通过手机号
     * @param entity
     * @return 数量
     */
    public int checkPhone(Reader entity){
        Object checkPhone = redisCache.getCacheObject("phoneCount");
        if (checkPhone == null){
            redisCache.setCacheObject("phoneCount",readerMapper.countPhone(entity),10,TimeUnit.MINUTES);
            return readerMapper.countPhone(entity);
        }
        return (Integer)checkPhone;
    }

}
