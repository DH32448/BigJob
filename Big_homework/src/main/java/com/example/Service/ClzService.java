package com.example.Service;

import com.example.entity.ClzEntity;

import java.util.List;

/**
 * @author HJX
 */
public interface ClzService {
    List<ClzEntity> findAll();
    int add(ClzEntity clzEntity);
    int remove(ClzEntity clzEntity);
    int update(ClzEntity clzEntity);
}
