package com.angrypig.service;

import com.angrypig.entity.User;

import java.util.List;


public interface UserService {
    List getAllUsers();

    int insertUser(User user);
}
