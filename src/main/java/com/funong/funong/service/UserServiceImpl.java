package com.funong.funong.service;

import com.funong.funong.mapper.UserMapper;
import com.funong.funong.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    private void setUserMapper(UserMapper userMapper){
        this.userMapper = userMapper;
    }
    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    public User getUserByName(String userName) {
        return userMapper.getUserByName(userName);
    }

    @Override
    public int upDateUser(User user) {
        return userMapper.upDateUser(user);
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public User getUserById(int userId) {
        return userMapper.getUserById(userId);
    }
}
