package com.example.dao;

import com.example.entity.MarkEntity;
import com.example.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MarkDao {
    //查询某个学生某一门课程的分数
    public MarkEntity findBySnoCno(Map<String, Object> map);

    public List<MarkEntity> findBySno(String sno);
    //查询

    public List<UserEntity> findByClzno2(@Param("cno") String cno,@Param("clzno") String clzno);

    //查找班级指定课程分数
    List<MarkEntity> findByClznoCno(@Param("clzno")String clzno, @Param("cno")String cno);

    //更新分数， 根据主键 t_mark.id >0
    public int update(MarkEntity mark);

    //添加分数， 根据主键 t_mark.id ==0
    public int add(MarkEntity mark);
}
