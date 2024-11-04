package com.example.Service;

import com.example.entity.UserEntity;

import java.util.List;

public interface UserService {
    public int add(UserEntity u);         //添加
    public int update(UserEntity u);    //更新, 最好使用条件更新方式书写
    public int updatePwd(UserEntity u);   //更新密码
    public UserEntity  findById(int uid);  //根据主键查找
    public List<UserEntity> findByRole(int role);
    public int del(int uid);            //删
    UserEntity findByUserByPhone(String phone);
}
