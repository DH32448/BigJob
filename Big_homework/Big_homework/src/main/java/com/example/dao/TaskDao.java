package com.example.dao;

import com.example.entity.TaskEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Administrator
 */
@Mapper
public interface TaskDao {
    //@Select("select * from t_task")
    public List<TaskEntity> findAll();   //查找全部
    public List<TaskEntity> findByClzno(String clzno);  //根据班级编号查找  -》假设学生用的
    public List<TaskEntity> findByTid(int tid);  //根据教师ID 查找        -》假设教师用的
    public int  add(TaskEntity task);  //添加
    public int remove(TaskEntity kid);   //删除
}
