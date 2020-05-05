package com.funong.funong.mapper;

import com.funong.funong.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface UserMapper {
    List<User> getAllUser();
    User getUserByName(String userName);
    int upDateUser(User user);
    int addUser(User user);
    User getUserById(int userId);
}
