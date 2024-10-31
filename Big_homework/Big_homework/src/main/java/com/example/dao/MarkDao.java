package com.example.dao;

import com.example.entity.MarkEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MarkDao {
    //查询某个学生某一门课程的分数
    public MarkEntity findBySnoCno(Map<String, Object> map);

    //查询某个学生所有课程的分数
    public List<MarkEntity> findBySno(String sno);

    //更新分数， 根据主键 t_mark.id >0
    public int update(MarkEntity mark);

    //添加分数， 根据主键 t_mark.id ==0
    public int add(MarkEntity mark);
}
