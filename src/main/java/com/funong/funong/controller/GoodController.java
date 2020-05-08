package com.funong.funong.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.funong.funong.backPojo.BackPageIndex;
import com.funong.funong.backPojo.BackTypeIndex;
import com.funong.funong.mapper.*;
import com.funong.funong.plugin.ChangeDate;
import com.funong.funong.plugin.GetPageIndex;
import com.funong.funong.plugin.JundgeCan;
import com.funong.funong.pojo.*;
import com.funong.funong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    private TypeDao typeDao;
    @Autowired
    private AdviceDao adviceDao;
    @Autowired
    private ImgDao imgDao;
    JundgeCan jundgeCan = new JundgeCan();
    ChangeDate changeDate = new ChangeDate();

    @GetMapping("everyOne/getGood/{pageIndex}")
    public List<Good> getAllGood(@PathVariable int pageIndex) {
        GetPageIndex getPageIndex = new GetPageIndex();
        BackPageIndex backPageIndex = getPageIndex.getPageIndex(pageIndex);
        List<Good> goods = goodDao.getAllGood(backPageIndex);
        return goods;
    }

    @GetMapping("everyOne/getTypeGood/{pageIndex}/{type}")
    public List<Good> getTypeGood(@PathVariable int pageIndex, @PathVariable String type) {
        GetPageIndex getPageIndex = new GetPageIndex();
        BackPageIndex backPageIndex = getPageIndex.getPageIndex(pageIndex);
        BackTypeIndex backTypeIndex = new BackTypeIndex();
        backTypeIndex.setStart(backPageIndex.getStart());
        backTypeIndex.setEnd(backPageIndex.getEnd());
        backTypeIndex.setType(type);
        List<Good> goods = goodDao.getGood(backTypeIndex);
        return goods;
    }

    @GetMapping("everyOne/getTypes")
    public List<Type> getAllType() {
        List<Type> types = typeDao.getAllType();
        return types;
    }

    @GetMapping("everyOne/getGoodPage")
    public int getPage() {
        return goodDao.getNum() / 10 + 1;
    }

    @GetMapping("everyOne/getGoodDetail/{goodId}")
    public HashMap<Object, Object> getGoodDetail(@PathVariable int goodId) {
        Good good = goodDao.selectByPrimaryKey(goodId);
        int farmerId = good.getFarmerid();
        Farmer farmer = farmerDao.selectByPrimaryKey(farmerId);
        int representId = good.getRepresentid();
        Represent represent = representDao.selectByPrimaryKey(representId);
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("good", good);
        hashMap.put("farmer", farmer);
        hashMap.put("represent", represent);
        return hashMap;

    }

    @PostMapping("test")
    public void test() {
        Good good = new Good();
        good.setGoodname("kekekeke");
        good.setGoodurl("aaaaa");
        int a = goodDao.insert(good);
        System.out.println("打印++++++++++++++++++++++aaaa" + a);
        int b = goodDao.insertSelective(good);
        System.out.println("打印++++++++++bbbbb" + b);
    }

    @GetMapping("everyOne/getAdviceGood/{type}")
    public List<Good> getAdviceGood(@PathVariable String type) {
        List<Advice> advice = adviceDao.getAdviceByType(type);
        List<Good> goods = new ArrayList<>();
        for (Advice advice1 : advice) {
            Good good = goodDao.selectByPrimaryKey(advice1.getGoodid());
            goods.add(good);
        }
        return goods;
    }

    @GetMapping("everyOne/getALLAdvice")
    private List<Advice> getAllAdvice() {
        return adviceDao.getAllAdvice();
    }

    @PostMapping("represent/addGood")
    private HashMap<Object, Object> addGood(@RequestBody Good good) {
        HashMap<Object, Object> hashMap = new HashMap<>();
        Date createTime = changeDate.getDate();
        good.setCreatetime(createTime);
        good.setUpdatetime(createTime);
        good.setGoodid(1);
        String string = good.getGoodurl();
        if (string == null){
            hashMap.put("msg", "没有图片");
            return hashMap;
        }else {
            Img img = new Img();
            img.setImgUrl(good.getGoodurl());
            img.setImgStatus("good"+ ":" + good.getGoodname());
            imgDao.updateByPrimaryKeySelective(img);
        }
        if (good.getGoodstatus()== null){
            good.setGoodstatus("live");
        }
        System.out.println(good);
        if (jundgeCan.canUse(good)) {
            int a = goodDao.insertSelective(good);
            if (a != 0) {
                hashMap.put("good", good);
                hashMap.put("msg", "添加成功");
                return hashMap;
            } else {
                hashMap.put("msg", "数据添加失败，检查是否合法！");
                return hashMap;
            }
        } else {
            hashMap.put("msg", "内容不完整");
            hashMap.put("error",good);
            return hashMap;
        }

    }

     @PostMapping("represent/updateGood")
    private HashMap<Object,Object> updateGood(Good good){
        HashMap<Object,Object> hashMap = new HashMap<>();
        good.setUpdatetime(changeDate.getDate());
         int a = goodDao.updateByPrimaryKeySelective(good);
         if (a != 0){
             Good good1 = goodDao.selectByPrimaryKey(good.getGoodid());
             hashMap.put("Good",good1);
             hashMap.put("msg","添加成功");

             return  hashMap;

         }else {
             hashMap.put("msg","数据异常");
             hashMap.put("error",good);
             return hashMap;
         }
    }

    @GetMapping("represent/canUserGoodName/{goodName}")
    private boolean canUse(@PathVariable String goodName){
     Good good = goodDao.getGoodByGoodName(goodName);
     if (good.getGoodname().isEmpty() ){
         return false;
     }else {
         return true;
     }
    }


}
