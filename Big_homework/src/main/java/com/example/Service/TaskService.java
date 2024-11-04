package com.example.Service;

import com.example.entity.TaskEntity;

import java.util.List;

public interface TaskService {
    public List<TaskEntity> findAll();   //查找全部
    public List<TaskEntity> findByClzno(String clzno);  //根据班级编号查找  -》假设学生用的
    public List<TaskEntity> findByTid(int tid);  //根据教师ID 查找        -》假设教师用的
    public int  add(TaskEntity task);  //添加
    public int remove(TaskEntity kid);   //删除
}
