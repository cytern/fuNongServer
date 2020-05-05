package com.funong.funong.controller;

import com.funong.funong.pojo.User;
import com.funong.funong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class testcontroller {
    @Autowired
    private UserService userService;
    @GetMapping("/getAllUser")
    public List<User> get(){
      return userService.getAllUser();
    }
}
