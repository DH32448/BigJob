package com.example.dao;

import com.example.entity.Largefile;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LargeFileDao {

    public Integer add(Largefile largefile);

    public Largefile findOne(String id);


    void delete(String id);
}
