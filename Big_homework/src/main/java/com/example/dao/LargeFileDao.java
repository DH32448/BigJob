package com.example.dao;

import com.example.entity.Largefile;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LargeFileDao {

    public Integer add(Largefile largefile);

    public Largefile findOne(String id);


    int delete(String id);
}
