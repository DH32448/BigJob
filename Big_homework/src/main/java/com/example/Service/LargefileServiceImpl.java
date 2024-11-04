package com.example.Service;

import com.example.dao.LargeFileDao;
import com.example.entity.Largefile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author X
 */
@Service
public class LargefileServiceImpl implements LargefileService{
    @Autowired
    LargeFileDao largeFileDao;
    @Override
    public Integer add(Largefile largefile) {
        return largeFileDao.add(largefile);
    }

    @Override
    public Largefile findOne(String id) {
        return largeFileDao.findOne(id);
    }

    @Override
    public int delete(String id) {
        return largeFileDao.delete(id);
    }
}
