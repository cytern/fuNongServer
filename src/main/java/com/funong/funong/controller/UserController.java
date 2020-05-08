package com.funong.funong.controller;

import com.funong.funong.plugin.ChangeDate;
import com.funong.funong.pojo.Customer;
import com.funong.funong.pojo.User;
import com.funong.funong.service.CustomerService;
import com.funong.funong.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerService customerService;
    ChangeDate changeDate =new ChangeDate();
    @PostMapping("everyOne/addUser")
    public HashMap<Object,Object> addUser(User user){
        HashMap<Object,Object> hashMap =new HashMap<>();
         User user1 =new User();
         if (user.getUserName() ==null && user.getUserPassword() == null){
             hashMap.put("error","缺少用户名或密码");
             return hashMap;
         }
         User user2 = userService.getUserByName(user.getUserName());
         if (user2.getUserName() != null){
             hashMap.put("error","已经存在的用户名");
             return hashMap;
         }
         user1.setUserName(user.getUserName());
         user1.setUserPassword(user.getUserPassword());
         user1.setUserStatus("live");
         user1.setUserType("customer");
         user1.setCreateTime(changeDate.getTime());
         user1.setUpdateTime(changeDate.getTime());
          int a=   userService.addUser(user1);
         if (a != 1 ){
             hashMap.put("error","添加失败");
             return hashMap;
         }
        Customer customer =new Customer();
        customer.setUserId(user1.getUserId());
        customer.setCreateTime(changeDate.getTime());
        customer.setUpdateTime(changeDate.getTime());
        customer.setCustomerGrade("0");
        customer.setCustomerName(user1.getUserName());
        customer.setCustomerType("shopper");
        int b = customerService.addCustomer(customer);
        if (b != 1 ){
            hashMap.put("error","添加失败");
            return hashMap;
        }
        hashMap.put("user",user1);
        hashMap.put("customer",customer);
        hashMap.put("success","添加成功");
        return hashMap;
    }
    @GetMapping("everyOne/hasSameName/{userName}")
    public boolean hasSameName(@PathVariable String userName){
        User user = userService.getUserByName(userName);
        if (user.getUserName() != null){
            return true;
        }
        return false;
    }

    @GetMapping("customer/deleteUser/{userId}")
    public HashMap<Object,Object> deleteUser(@PathVariable Integer userId){
        HashMap<Object,Object> hashMap =new HashMap<>();
        User user = userService.getUserById(userId);
        if (user.getUserName() == null){
            hashMap.put("error","无效的userid");
            return hashMap;
        }
        user.setUserStatus("dead");
        userService.upDateUser(user);
        hashMap.put("user",user);
        return hashMap;
    }
}
