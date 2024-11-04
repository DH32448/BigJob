package com.example.Service;

import com.example.dao.TaskDao;
import com.example.entity.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    TaskDao taskDao;
    @Override
    public List<TaskEntity> findAll() {
        return taskDao.findAll();
    }

    @Override
    public List<TaskEntity> findByClzno(String clzno) {
        return taskDao.findByClzno(clzno);
    }

    @Override
    public List<TaskEntity> findByTid(int tid) {
        return taskDao.findByTid(tid);
    }

    @Override
    public int add(TaskEntity task) {
        return taskDao.add(task);
    }

    @Override
    public int remove(TaskEntity kid) {
        return taskDao.remove(kid);
    }
}
