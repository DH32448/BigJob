package com.example.Service;

import com.example.dao.ClzDao;
import com.example.entity.ClzEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author X
 */
@Service
public class ClzServiceImpl implements ClzService{
    @Autowired
    ClzDao clzDao;
    @Override
    public List<ClzEntity> findAll() {
        return clzDao.findAll();
    }

    @Override
    public int add(ClzEntity clzEntity) {
        return clzDao.add(clzEntity);
    }

    @Override
    public int remove(ClzEntity clzEntity) {
        return clzDao.remove(clzEntity);
    }

    @Override
    public int update(ClzEntity clzEntity) {
        return clzDao.update(clzEntity);
    }
}
