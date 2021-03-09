package com.example.book.service;

import com.example.book.entity.Reader;
import com.example.book.mapper.ReaderMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    public void add(Reader entity) {
        readerMapper.insert(entity);
    }

    public void modify(Reader entity) {
        readerMapper.update(entity);
    }

    public void removes(Integer[] ids) {
        readerMapper.deletes(ids);
    }

    public Reader get(Integer id) {
        return readerMapper.select(id);
    }

    public int count() {
        return readerMapper.count();
    }

    public List<Reader> getList(Map<String, Object> param) {
        return readerMapper.selectList(param);
    }

    public List<Reader> getPageResult(Map<String, Object> param) {
        return readerMapper.selectPageResult(param);
    }

    public int checkPhone(Reader entity){
        return readerMapper.countPhone(entity);
    }

}
