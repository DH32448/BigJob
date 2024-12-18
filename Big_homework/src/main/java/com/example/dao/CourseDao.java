package com.example.dao;

import com.example.entity.CourseEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author HJX
 */
@Mapper
public interface CourseDao {
    List<CourseEntity> findAll();
    int add(CourseEntity courseEntity);
    int remove(CourseEntity courseEntity);
    int update(CourseEntity courseEntity);
}
