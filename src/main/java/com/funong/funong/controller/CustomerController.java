package com.funong.funong.controller;

import com.funong.funong.service.CustomerService;
import com.funong.funong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private UserService userService;


}
