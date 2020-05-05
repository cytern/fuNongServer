package com.funong.funong.service;

import com.funong.funong.pojo.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    User getUserByName(String userName);
    int upDateUser(User user);
    int addUser(User user);
    User getUserById(int userId);
}
