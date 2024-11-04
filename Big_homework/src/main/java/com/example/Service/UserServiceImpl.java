package com.example.Service;

import com.example.dao.UserDao;
import com.example.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author X
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDao userDao;
    @Override
    public int add(UserEntity u) {
        return userDao.add(u);
    }

    @Override
    public int update(UserEntity u) {
        return userDao.update(u);
    }

    @Override
    public int updatePwd(UserEntity u) {
        return userDao.updatePwd(u);
    }

    @Override
    public UserEntity findById(int uid) {
        return userDao.findById(uid);
    }

    @Override
    public List<UserEntity> findByRole(int role) {
        return userDao.findByRole(role);
    }

    @Override
    public int del(int uid) {
        return userDao.del(uid);
    }

    @Override
    public UserEntity findByUserByPhone(String phone) {
        return userDao.findByUserByPhone(phone);
    }
}
