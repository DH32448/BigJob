package com.example.Service;

import com.example.entity.CourseEntity;

import java.util.List;

public interface CourseService {
    List<CourseEntity> findAll();
    int add(CourseEntity courseEntity);
    int remove(CourseEntity courseEntity);
    int update(CourseEntity courseEntity);
}
