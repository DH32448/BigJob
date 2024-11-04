package com.example.Service;

import com.example.dao.MarkDao;
import com.example.entity.MarkEntity;
import com.example.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author X
 */
@Service
public class MarkServiceImpl implements MarkService{
    @Autowired
    MarkDao markDao;
    @Override
    public MarkEntity findBySnoCno(Map<String, Object> map) {
        return markDao.findBySnoCno(map);
    }

    @Override
    public List<MarkEntity> findBySno(String sno) {
        return markDao.findBySno(sno);
    }

    @Override
    public List<UserEntity> findByClzno2(String cno, String clzno) {
        return markDao.findByClzno2(cno, clzno);
    }

    @Override
    public List<MarkEntity> findByClznoCno(String clzno, String cno) {
        return markDao.findByClznoCno(clzno, cno);
    }

    @Override
    public int update(MarkEntity mark) {
        return markDao.update(mark);
    }

    @Override
    public int add(MarkEntity mark) {
        return markDao.add(mark);
    }
}
