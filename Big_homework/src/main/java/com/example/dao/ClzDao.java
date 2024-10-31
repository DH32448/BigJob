package com.example.dao;

import com.example.entity.ClzEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author X
 */
@Mapper
public interface ClzDao {
    List<ClzEntity> findAll();
    int add(ClzEntity clzEntity);
    int remove(ClzEntity clzEntity);
    int update(ClzEntity clzEntity);
}
