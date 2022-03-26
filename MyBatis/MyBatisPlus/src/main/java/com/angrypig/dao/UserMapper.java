package com.angrypig.dao;

import com.angrypig.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    //查找所有用户信息
    List<User> getAllUsers();

    int insertUser(User user);
}
