package com.funong.funong.controller;

import com.funong.funong.backPojo.BackOrderLimit;
import com.funong.funong.backPojo.BackTypeIndex;
import com.funong.funong.mapper.ManagerDao;
import com.funong.funong.mapper.OrderDao;
import com.funong.funong.mapper.RepresentDao;
import com.funong.funong.mapper.RootDao;
import com.funong.funong.plugin.GetPageIndex;
import com.funong.funong.pojo.*;
import com.funong.funong.service.CustomerService;
import com.funong.funong.service.TokenService;
import com.funong.funong.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ManagerDao managerDao;
    @Autowired
    private RepresentDao representDao;
    @Autowired
    private RootDao rootDao;
    @Autowired
    private UserService userService;
    GetPageIndex getPageIndex = new GetPageIndex();

    @GetMapping("everyOne/getMyOrder/{pageIndex}")
    public HashMap<Object, Object> getMyOrder(HttpServletRequest request, @PathVariable int pageIndex) {
        String token = request.getHeader("User-Token");
        HashMap<Object, Object> hashMap = new HashMap<>();
        Token token1 = tokenService.getTokenByToken(token);
        String type = token1.getTokenType();
        int userId = token1.getUserId();
        BackTypeIndex backTypeIndex = getPageIndex.getTypeIndex(pageIndex);
        switch (type) {
            case "customer": {
                Customer customer = customerService.getCustomerByUserId(userId);
                backTypeIndex.setType(String.valueOf(customer.getCustomerId()));
                List<Order> orders = orderDao.getOrderByCustomerId(backTypeIndex);
                int pageMax = orderDao.getNumCustomer(customer.getCustomerId());
                hashMap.put("pageMax", pageMax);
                hashMap.put("customer", customer);
                hashMap.put("order", orders);
                return hashMap;
            }
            case "manager": {
                Manager manager = managerDao.getManagerByUserId(userId);
                List<Order> orders = orderDao.getAllOrder(backTypeIndex);
                int pageMax = orderDao.getNumAll();
                hashMap.put("pageMax", pageMax);
                hashMap.put("manager", manager);
                hashMap.put("order", orders);
                return hashMap;
            }
            case "represent": {
                Represent represent = representDao.getRepresentById(userId);
                backTypeIndex.setType(String.valueOf(represent.getRepresentid()));
                List<Order> orders = orderDao.getOrderByRepresentId(backTypeIndex);
                int pageMax = orderDao.getNumRepresent(represent.getRepresentid());
                hashMap.put("pageMax", pageMax);
                hashMap.put("represent", represent);
                hashMap.put("order", orders);
                return hashMap;
            }
            case "root": {
                Root root = rootDao.getRootByUserId(userId);
                List<Order> orders = orderDao.getAllOrder(backTypeIndex);
                int pageMax = orderDao.getNumAll();
                hashMap.put("pageMax", pageMax);
                hashMap.put("root", root);
                hashMap.put("order", orders);
                return hashMap;
            }
            default: {
                hashMap.put("error", "无效用户");
                return hashMap;
            }
        }


    }

    @GetMapping("customer/getCustomerOrder")
    public HashMap<Object, Object> getCustomerOrder(BackTypeIndex backTypeIndex) {
        HashMap<Object, Object> hashMap = new HashMap<>();
       if (backTypeIndex.getPage() == 0){
           backTypeIndex.setPage(1);
       }
       BackTypeIndex backTypeIndex1 = getPageIndex.getTypeIndex(backTypeIndex.getPage());
       backTypeIndex.setStart(backTypeIndex1.getStart());
       backTypeIndex.setEnd(backTypeIndex1.getEnd());
       if (backTypeIndex.getId() == 0){
           hashMap.put("error","id不能为空");
           return hashMap;
       }
        if (backTypeIndex.getType() != null){
            List<Order> orders = orderDao.getOrderByCustomerIdType(backTypeIndex);
            int pageMax = orderDao.getNumCustomerIdType(backTypeIndex);
            hashMap.put("pageMax", pageMax);
            hashMap.put("order",orders);
            hashMap.put("customer","customer");
            return hashMap;
        }else {
            List<Order> orders = orderDao.getOrderByCustomerId(backTypeIndex);
            int pageMax = orderDao.getNumCustomer(backTypeIndex.getId());
            hashMap.put("pageMax", pageMax);
            hashMap.put("order",orders);
            hashMap.put("customer","customer");
            return hashMap;
        }
    }

    @GetMapping("represent/getRepresentOrder")
    public HashMap<Object, Object> getRepresentOrder(BackTypeIndex backTypeIndex) {
        HashMap<Object, Object> hashMap = new HashMap<>();
        if (backTypeIndex.getPage() == 0){
            backTypeIndex.setPage(1);
        }
        BackTypeIndex backTypeIndex1 = getPageIndex.getTypeIndex(backTypeIndex.getPage());
        backTypeIndex.setStart(backTypeIndex1.getStart());
        backTypeIndex.setEnd(backTypeIndex1.getEnd());
        if (backTypeIndex.getId() == 0){
            hashMap.put("error","id不能为空");
            return hashMap;
        }
        if (backTypeIndex.getType() != null){
            List<Order> orders = orderDao.getOrderByRepresentIdType(backTypeIndex);
            int pageMax = orderDao.getNumRepresentIdType(backTypeIndex);
            hashMap.put("pageMax", pageMax);
            hashMap.put("order",orders);
            hashMap.put("customer","customer");
            return hashMap;
        }else {
            List<Order> orders = orderDao.getOrderByRepresentId(backTypeIndex);
            int pageMax = orderDao.getNumRepresent(backTypeIndex.getId());
            hashMap.put("pageMax", pageMax);
            hashMap.put("order",orders);
            hashMap.put("customer","customer");
            return hashMap;
        }
    }
       /*
       复合订单查询功能
        */
    @GetMapping("manager/getOrders/noAuto")
    public HashMap<Object, Object> getOrdersNoAuto(BackTypeIndex backTypeIndex1) {
        HashMap<Object, Object> hashMap = new HashMap<>();
            if (backTypeIndex1.getId() == 0){
                hashMap.put("error","缺少userid");
                return hashMap;
            }
            int userId = backTypeIndex1.getId();
            User user = userService.getUserById(userId);
            if (user.getUserType() == null){
                hashMap.put("error","无效的用户");
                return hashMap;
            }
            String type = user.getUserType();
            int pageIndex = backTypeIndex1.getPage();
            BackTypeIndex backTypeIndex = getPageIndex.getTypeIndex(pageIndex);
            backTypeIndex.setType(backTypeIndex1.getType());
            backTypeIndex.setId(backTypeIndex1.getId());
            if (backTypeIndex.getType() != null){
                hashMap = hasType(backTypeIndex,type);
                return hashMap;
            }
            switch (type) {
                case "customer": {
                    Customer customer = customerService.getCustomerByUserId(userId);
                    backTypeIndex.setType(String.valueOf(customer.getCustomerId()));
                    List<Order> orders = orderDao.getOrderByCustomerId(backTypeIndex);
                    int pageMax = orderDao.getNumCustomer(customer.getCustomerId());
                    hashMap.put("pageMax", pageMax);
                    hashMap.put("customer", customer);
                    hashMap.put("order", orders);
                    return hashMap;
                }
                case "manager": {
                    Manager manager = managerDao.getManagerByUserId(userId);
                    List<Order> orders = orderDao.getAllOrder(backTypeIndex);
                    int pageMax = orderDao.getNumAll();
                    hashMap.put("pageMax", pageMax);
                    hashMap.put("manager", manager);
                    hashMap.put("order", orders);
                    return hashMap;
                }
                case "represent": {
                    Represent represent = representDao.getRepresentById(userId);
                    backTypeIndex.setType(String.valueOf(represent.getRepresentid()));
                    List<Order> orders = orderDao.getOrderByRepresentId(backTypeIndex);
                    int pageMax = orderDao.getNumRepresent(represent.getRepresentid());
                    hashMap.put("pageMax", pageMax);
                    hashMap.put("represent", represent);
                    hashMap.put("order", orders);
                    return hashMap;
                }
                case "root": {
                    Root root = rootDao.getRootByUserId(userId);
                    List<Order> orders = orderDao.getAllOrder(backTypeIndex);
                    int pageMax = orderDao.getNumAll();
                    hashMap.put("pageMax", pageMax);
                    hashMap.put("root", root);
                    hashMap.put("order", orders);
                    return hashMap;
                }
                default: {
                    hashMap.put("error", "无效用户");
                    return hashMap;
                }
            }



    }

    public HashMap<Object,Object> hasType(BackTypeIndex backTypeIndex,String type){
        HashMap<Object, Object> hashMap = new HashMap<>();
                 int userId = backTypeIndex.getId();
        switch (type) {
            case "customer": {
                Customer customer = customerService.getCustomerByUserId(userId);
                backTypeIndex.setId(customer.getCustomerId());
                List<Order> orders = orderDao.getOrderByCustomerIdType(backTypeIndex);
                int pageMax = orderDao.getNumCustomerIdType(backTypeIndex);
                hashMap.put("pageMax", pageMax);
                hashMap.put("customer", customer);
                hashMap.put("order", orders);
                return hashMap;
            }
            case "manager": {
                Manager manager = managerDao.getManagerByUserId(userId);
                List<Order> orders = orderDao.getOrderByOrderType(backTypeIndex);
                int pageMax = orderDao.getNumType(backTypeIndex.getType());
                hashMap.put("pageMax", pageMax);
                hashMap.put("manager", manager);
                hashMap.put("order", orders);
                return hashMap;
            }
            case "represent": {
                Represent represent = representDao.getRepresentById(userId);
                backTypeIndex.setId(represent.getRepresentid());
                List<Order> orders = orderDao.getOrderByRepresentIdType(backTypeIndex);
                int pageMax = orderDao.getNumRepresentIdType(backTypeIndex);
                hashMap.put("pageMax", pageMax);
                hashMap.put("represent", represent);
                hashMap.put("order", orders);
                return hashMap;
            }
            case "root": {
                Root root = rootDao.getRootByUserId(userId);
                List<Order> orders = orderDao.getOrderByOrderType(backTypeIndex);
                int pageMax = orderDao.getNumType(backTypeIndex.getType());
                hashMap.put("pageMax", pageMax);
                hashMap.put("root", root);
                hashMap.put("order", orders);
                return hashMap;
            }
            default: {
                hashMap.put("error", "无效用户");
                return hashMap;
            }
        }
    }



}



