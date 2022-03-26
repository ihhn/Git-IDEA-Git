package com.angrypig.service.impl;

import com.angrypig.dao.UserMapper;
import com.angrypig.entity.User;
import com.angrypig.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService,ApplicationContextAware {

    private ApplicationContext ctx;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }


    //查询所有用户信息
    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = userMapper.getAllUsers();

        return allUsers;
    }


    //插入一条用户记录
    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }
}
