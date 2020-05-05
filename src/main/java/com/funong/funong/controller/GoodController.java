package com.funong.funong.controller;

import com.funong.funong.backPojo.BackPageIndex;
import com.funong.funong.mapper.FarmerDao;
import com.funong.funong.mapper.GoodDao;
import com.funong.funong.mapper.RepresentDao;
import com.funong.funong.plugin.GetPageIndex;
import com.funong.funong.pojo.Farmer;
import com.funong.funong.pojo.Good;
import com.funong.funong.pojo.Represent;
import com.funong.funong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class GoodController {
    @Autowired
    private GoodDao goodDao;
    @Autowired
    private UserService userService;
    @Autowired
    private RepresentDao representDao;
    @Autowired
    private FarmerDao farmerDao;
    @GetMapping("everyOne/getGood/{pageIndex}")
    public List<Good> getAllGood(@PathVariable int pageIndex){
        GetPageIndex getPageIndex =new GetPageIndex();
        BackPageIndex backPageIndex = getPageIndex.getPageIndex(pageIndex);
        List<Good>  goods= goodDao.getAllGood(backPageIndex);
        return goods;
    }
    @GetMapping("everyOne/getGoodPage")
    public int getPage(){
        return goodDao.getNum()/10+1;
    }
    @GetMapping("everyOne/getGoodDetail/{goodId}")
    public HashMap<Object,Object> getGoodDetail(@PathVariable int goodId){
        Good good = goodDao.selectByPrimaryKey(goodId);
        int farmerId = good.getFarmerid();
        Farmer farmer = farmerDao.selectByPrimaryKey(farmerId);
        int representId = good.getRepresentid();
        Represent represent = representDao.selectByPrimaryKey(representId);
        HashMap<Object,Object> hashMap = new HashMap<>();
        hashMap.put("good",good);
        hashMap.put("farmer",farmer);
        hashMap.put("represent",represent);
        return hashMap;

    }

    @PostMapping("test")
    public void test(){
        Good good =new Good();
        good.setGoodname("kekekeke");
        good.setGoodurl("aaaaa");
        int a = goodDao.insert(good);
        System.out.println("打印++++++++++++++++++++++aaaa"+a);
        int b = goodDao.insertSelective(good);
        System.out.println("打印++++++++++bbbbb"+b);
    }
}
