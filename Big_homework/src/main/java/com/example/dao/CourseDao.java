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
    int add(CourseEntity CourseEntity);
    int remove(CourseEntity CourseEntity);
    int update(CourseEntity CourseEntity);
}
