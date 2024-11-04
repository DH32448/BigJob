package com.example.Service;

import com.example.dao.CourseDao;
import com.example.entity.CourseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author X
 */
@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    CourseDao courseDao;
    @Override
    public List<CourseEntity> findAll() {
        return courseDao.findAll();
    }

    @Override
    public int add(CourseEntity courseEntity) {
        return courseDao.add(courseEntity);
    }

    @Override
    public int remove(CourseEntity courseEntity) {
        return courseDao.remove(courseEntity);
    }

    @Override
    public int update(CourseEntity courseEntity) {
        return courseDao.update(courseEntity);
    }
}
