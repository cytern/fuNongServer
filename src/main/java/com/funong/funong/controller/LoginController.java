package com.funong.funong.controller;

import com.funong.funong.backPojo.BackLogin;
import com.funong.funong.mapper.FarmerDao;
import com.funong.funong.mapper.ManagerDao;
import com.funong.funong.mapper.RepresentDao;
import com.funong.funong.mapper.RootDao;
import com.funong.funong.plugin.ChangeDate;
import com.funong.funong.pojo.*;
import com.funong.funong.service.CustomerService;
import com.funong.funong.service.TokenService;
import com.funong.funong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("login/")
public class LoginController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private FarmerDao farmerDao;
    @Autowired
    private ManagerDao managerDao;
    @Autowired
    private RepresentDao representDao;
    @Autowired
    private RootDao rootDao;
    @PostMapping("loginEveryOne")
    public HashMap<Object,Object> login(BackLogin backLogin){
   HashMap<Object,Object> hashMap = new HashMap<>();
    String userName = backLogin.getUserName();
    String password = backLogin.getPassword();
     User user =   userService.getUserByName(userName);
     if (user.getUserPassword().equals(password)){
         String userStatus = user.getUserStatus();
         if (userStatus.equals("dead")){
             hashMap.put("msg","用户已被删除");
             return hashMap;
         }else {
             String token = getToken(user);
             Token token1 = new Token();
             token1 = setToken(token1);
             token1.setToken(token);
             token1.setUserId(user.getUserId());
           switch (user.getUserType()){
               case "customer":{
                   Customer customer = customerService.getCustomerByUserId(user.getUserId());
                   hashMap.put("user",user);
                   hashMap.put("customer",customer);
                   hashMap.put("token",token);
                   token1.setTokenType("customer");
                   tokenService.addToken(token1);
                   return hashMap;
               }
               case "farmer":{
                   Farmer farmer = farmerDao.getFarmerByUserId(user.getUserId());
                   hashMap.put("user",user);
                   hashMap.put("farmer",farmer);
                   hashMap.put("token",token);
                   token1.setTokenType("farmer");
                   tokenService.addToken(token1);
                   return hashMap;
               }
               case "manager":{
                   Manager manager = managerDao.getManagerByUserId(user.getUserId());
                   hashMap.put("token",token);
                   hashMap.put("user",user);
                   hashMap.put("manager",manager);
                   token1.setTokenType("manager");
                   tokenService.addToken(token1);
                   return hashMap;
               }
               case "represent":{
                   Represent represent = representDao.getRepresentById(user.getUserId());
                   hashMap.put("token",token);
                   hashMap.put("user",user);
                   hashMap.put("represent",represent);
                   token1.setTokenType("represent");
                   tokenService.addToken(token1);
                   return hashMap;
               }
               case "root":{
                   Root root = rootDao.getRootByUserId(user.getUserId());
                   hashMap.put("token",token);
                   hashMap.put("user",user);
                   hashMap.put("root",root);
                   token1.setTokenType("root");
                   tokenService.addToken(token1);
                   return hashMap;
               }
               default:{
            hashMap.put("error","无法查询到该用户所属信息");
            return hashMap;
               }
           }
         }
     }else {
         hashMap.put("msg","密码错误");
         return hashMap;
     }


    }
    public static String getToken(User user){
        ChangeDate changeDate =new ChangeDate();
        int userCode = user.hashCode();
        String userCode2 = changeDate.getTime();
        String token = userCode+userCode2;
        token =  token.replaceAll(" ","");
        return token;
    }
    public static Token setToken(Token token){
        ChangeDate changeDate =new ChangeDate();
        String createTime = changeDate.getTime();
        String updateTime = changeDate.getTime();
        token.setCreateTime(createTime);
        token.setUpdateTime(updateTime);
        return token;
    }

}
