package com.funong.funong.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONPObject;
import com.funong.funong.backPojo.BackOrder;
import com.funong.funong.backPojo.BackOrderLimit;
import com.funong.funong.backPojo.BackTypeIndex;
import com.funong.funong.mapper.*;
import com.funong.funong.plugin.ChangeDate;
import com.funong.funong.plugin.GetPageIndex;
import com.funong.funong.pojo.*;
import com.funong.funong.service.CustomerService;
import com.funong.funong.service.TokenService;
import com.funong.funong.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
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
    @Autowired
    private GoodorderDao goodorderDao;
    @Autowired
    private GoodDao goodDao;
    @Autowired
    private TypeDao typeDao;
    GetPageIndex getPageIndex = new GetPageIndex();
    ChangeDate changeDate = new ChangeDate();
    //智能查询header
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
                int pageMax = orderDao.getNumCustomer(customer.getCustomerId()) / 10 + 1;
                List<BackOrder> backOrders = getBackOrder(orders);
                hashMap.put("order", backOrders);
                hashMap.put("pageMax", pageMax);
                hashMap.put("customer", customer);

                return hashMap;

            }
            case "manager": {
                Manager manager = managerDao.getManagerByUserId(userId);
                List<Order> orders = orderDao.getAllOrder(backTypeIndex);
                int pageMax = orderDao.getNumAll() / 10 + 1;
                hashMap.put("pageMax", pageMax);
                hashMap.put("manager", manager);
                List<BackOrder> backOrders = getBackOrder(orders);
                hashMap.put("order", backOrders);
                return hashMap;
            }
            case "represent": {
                Represent represent = representDao.getRepresentById(userId);
                backTypeIndex.setType(String.valueOf(represent.getRepresentid()));
                List<Order> orders = orderDao.getOrderByRepresentId(backTypeIndex);
                int pageMax = orderDao.getNumRepresent(represent.getRepresentid()) / 10 + 1;
                hashMap.put("pageMax", pageMax);
                hashMap.put("represent", represent);
                List<BackOrder> backOrders = getBackOrder(orders);
                hashMap.put("order", backOrders);
                return hashMap;
            }
            case "root": {
                Root root = rootDao.getRootByUserId(userId);
                List<Order> orders = orderDao.getAllOrder(backTypeIndex);
                int pageMax = orderDao.getNumAll() / 10 + 1;
                hashMap.put("pageMax", pageMax);
                hashMap.put("root", root);
                List<BackOrder> backOrders = getBackOrder(orders);
                hashMap.put("order", backOrders);
                return hashMap;
            }
            default: {
                hashMap.put("error", "无效用户");
                return hashMap;
            }
        }


    }
    //顾客查询
    @GetMapping("customer/getCustomerOrder")
    public HashMap<Object, Object> getCustomerOrder(BackTypeIndex backTypeIndex) {
        HashMap<Object, Object> hashMap = new HashMap<>();
        if (backTypeIndex.getPage() == 0) {
            backTypeIndex.setPage(1);
        }
        BackTypeIndex backTypeIndex1 = getPageIndex.getTypeIndex(backTypeIndex.getPage());
        backTypeIndex.setStart(backTypeIndex1.getStart());
        backTypeIndex.setEnd(backTypeIndex1.getEnd());
        if (backTypeIndex.getId() == 0) {
            hashMap.put("error", "id不能为空");
            return hashMap;
        }
        if (backTypeIndex.getType() != null) {
            List<Order> orders = orderDao.getOrderByCustomerIdType(backTypeIndex);
            int pageMax = orderDao.getNumCustomerIdType(backTypeIndex) / 10 + 1;
            hashMap.put("pageMax", pageMax);
            List<BackOrder> backOrders = getBackOrder(orders);
            hashMap.put("order", backOrders);
            hashMap.put("customer", "customer");
            return hashMap;
        } else {
            List<Order> orders = orderDao.getOrderByCustomerId(backTypeIndex);
            int pageMax = orderDao.getNumCustomer(backTypeIndex.getId()) / 10 + 1;
            hashMap.put("pageMax", pageMax);
            List<BackOrder> backOrders = getBackOrder(orders);
            hashMap.put("order", backOrders);
            hashMap.put("customer", "customer");
            return hashMap;
        }
    }
    //代表查询
    @GetMapping("represent/getRepresentOrder")
    public HashMap<Object, Object> getRepresentOrder(BackTypeIndex backTypeIndex) {
        HashMap<Object, Object> hashMap = new HashMap<>();
        if (backTypeIndex.getPage() == 0) {
            backTypeIndex.setPage(1);
        }
        BackTypeIndex backTypeIndex1 = getPageIndex.getTypeIndex(backTypeIndex.getPage());
        backTypeIndex.setStart(backTypeIndex1.getStart());
        backTypeIndex.setEnd(backTypeIndex1.getEnd());
        if (backTypeIndex.getId() == 0) {
            hashMap.put("error", "id不能为空");
            return hashMap;
        }
        if (backTypeIndex.getType() != null) {
            List<Order> orders = orderDao.getOrderByRepresentIdType(backTypeIndex);
            int pageMax = orderDao.getNumRepresentIdType(backTypeIndex) / 10 + 1;
            hashMap.put("pageMax", pageMax);
            List<BackOrder> backOrders = getBackOrder(orders);
            hashMap.put("order", backOrders);
            hashMap.put("customer", "customer");
            return hashMap;
        } else {
            List<Order> orders = orderDao.getOrderByRepresentId(backTypeIndex);
            int pageMax = orderDao.getNumRepresent(backTypeIndex.getId()) / 10 + 1;
            hashMap.put("pageMax", pageMax);
            List<BackOrder> backOrders = getBackOrder(orders);
            hashMap.put("order", backOrders);
            hashMap.put("customer", "customer");
            return hashMap;
        }
    }

    /*
    复合订单查询功能
     */
    @GetMapping("manager/getOrders/noAuto")
    public HashMap<Object, Object> getOrdersNoAuto(BackTypeIndex backTypeIndex1) {
        HashMap<Object, Object> hashMap = new HashMap<>();
        if (backTypeIndex1.getId() == 0) {
            hashMap.put("error", "缺少userid");
            return hashMap;
        }
        int userId = backTypeIndex1.getId();
        User user = userService.getUserById(userId);
        if (user.getUserType() == null) {
            hashMap.put("error", "无效的用户");
            return hashMap;
        }
        String type = user.getUserType();
        int pageIndex = backTypeIndex1.getPage();
        BackTypeIndex backTypeIndex = getPageIndex.getTypeIndex(pageIndex);
        backTypeIndex.setType(backTypeIndex1.getType());
        backTypeIndex.setId(backTypeIndex1.getId());
        if (backTypeIndex.getType() != null) {
            hashMap = hasType(backTypeIndex, type);
            return hashMap;
        }
        switch (type) {
            case "customer": {
                Customer customer = customerService.getCustomerByUserId(userId);
                backTypeIndex.setType(String.valueOf(customer.getCustomerId()));
                List<Order> orders = orderDao.getOrderByCustomerId(backTypeIndex);
                int pageMax = orderDao.getNumCustomer(customer.getCustomerId()) / 10 + 1;
                hashMap.put("pageMax", pageMax);
                hashMap.put("customer", customer);
                List<BackOrder> backOrders = getBackOrder(orders);
                hashMap.put("order", backOrders);
                return hashMap;
            }
            case "manager": {
                Manager manager = managerDao.getManagerByUserId(userId);
                List<Order> orders = orderDao.getAllOrder(backTypeIndex);
                int pageMax = orderDao.getNumAll() / 10 + 1;
                hashMap.put("pageMax", pageMax);
                hashMap.put("manager", manager);
                List<BackOrder> backOrders = getBackOrder(orders);
                hashMap.put("order", backOrders);
                return hashMap;
            }
            case "represent": {
                Represent represent = representDao.getRepresentById(userId);
                backTypeIndex.setType(String.valueOf(represent.getRepresentid()));
                List<Order> orders = orderDao.getOrderByRepresentId(backTypeIndex);
                int pageMax = orderDao.getNumRepresent(represent.getRepresentid()) / 10 + 1;
                hashMap.put("pageMax", pageMax);
                hashMap.put("represent", represent);
                List<BackOrder> backOrders = getBackOrder(orders);
                hashMap.put("order", backOrders);
                return hashMap;
            }
            case "root": {
                Root root = rootDao.getRootByUserId(userId);
                List<Order> orders = orderDao.getAllOrder(backTypeIndex);
                int pageMax = orderDao.getNumAll() / 10 + 1;
                hashMap.put("pageMax", pageMax);
                hashMap.put("root", root);
                List<BackOrder> backOrders = getBackOrder(orders);
                hashMap.put("order", backOrders);
                return hashMap;
            }
            default: {
                hashMap.put("error", "无效用户");
                return hashMap;
            }
        }


    }
   //引用方法
    public HashMap<Object, Object> hasType(BackTypeIndex backTypeIndex, String type) {
        HashMap<Object, Object> hashMap = new HashMap<>();
        int userId = backTypeIndex.getId();
        switch (type) {
            case "customer": {
                Customer customer = customerService.getCustomerByUserId(userId);
                backTypeIndex.setId(customer.getCustomerId());
                List<Order> orders = orderDao.getOrderByCustomerIdType(backTypeIndex);
                int pageMax = orderDao.getNumCustomerIdType(backTypeIndex) / 10 + 1;
                hashMap.put("pageMax", pageMax);
                hashMap.put("customer", customer);
                List<BackOrder> backOrders = getBackOrder(orders);
                hashMap.put("order", backOrders);
                return hashMap;
            }
            case "manager": {
                Manager manager = managerDao.getManagerByUserId(userId);
                List<Order> orders = orderDao.getOrderByOrderType(backTypeIndex);
                int pageMax = orderDao.getNumType(backTypeIndex.getType()) / 10 + 1;
                hashMap.put("pageMax", pageMax);
                hashMap.put("manager", manager);
                List<BackOrder> backOrders = getBackOrder(orders);
                hashMap.put("order", backOrders);
                return hashMap;
            }
            case "represent": {
                Represent represent = representDao.getRepresentById(userId);
                backTypeIndex.setId(represent.getRepresentid());
                List<Order> orders = orderDao.getOrderByRepresentIdType(backTypeIndex);
                int pageMax = orderDao.getNumRepresentIdType(backTypeIndex) / 10 + 1;
                hashMap.put("pageMax", pageMax);
                hashMap.put("represent", represent);
                List<BackOrder> backOrders = getBackOrder(orders);
                hashMap.put("order", backOrders);
                return hashMap;
            }
            case "root": {
                Root root = rootDao.getRootByUserId(userId);
                List<Order> orders = orderDao.getOrderByOrderType(backTypeIndex);
                int pageMax = orderDao.getNumType(backTypeIndex.getType()) / 10 + 1;
                hashMap.put("pageMax", pageMax);
                hashMap.put("root", root);
                List<BackOrder> backOrders = getBackOrder(orders);
                hashMap.put("order", backOrders);
                return hashMap;
            }
            default: {
                hashMap.put("error", "无效用户");
                return hashMap;
            }
        }
    }

   //引用方法
    public List<BackOrder> getBackOrder(List<Order> orders){
        List<BackOrder> backOrders = new ArrayList<>();
        for (Order order:orders){
            BackOrder backOrder = new BackOrder();
            BackTypeIndex backTypeIndex10 = new BackTypeIndex();
            backTypeIndex10.setId(order.getOrderid());
            List<Goodorder> goodorders = goodorderDao.getByOrderId(backTypeIndex10);
            backOrder.setCreatetime(order.getCreatetime());
            backOrder.setCustomerid(order.getCustomerid());
            backOrder.setGoodorders(goodorders);
            backOrder.setOrderconf(order.getOrderconf());
            backOrder.setOrderid(order.getOrderid());
            backOrder.setOrderstatus(order.getOrderstatus());
            backOrder.setOrdertype(order.getOrdertype());
            backOrder.setRepresentid(order.getRepresentid());
            backOrder.setTotalprice(order.getTotalprice());
            backOrder.setUpdatetime(order.getUpdatetime());
            backOrders.add(backOrder);
        }
        return backOrders;
    }
    //获取订单商品
    @GetMapping("everyOne/getOrderGoods")
    public HashMap<Object,Object> getGoodOrder(BackTypeIndex backTypeIndex){
        HashMap<Object,Object> hashMap = new HashMap<>();
        int orderId = backTypeIndex.getId();
        if (orderId == 0){
            hashMap.put("error","缺少订单id");
            return hashMap;
        }
        List<Goodorder> goodorders = goodorderDao.getByOrderId(backTypeIndex);
        hashMap.put("goodOrder",goodorders);
        return hashMap;
    }

    @PostMapping(value = "customer/addOrder",produces = "application/json;charset=UTF-8")
    public HashMap<Object,Object> addOrder(@RequestBody String json){

        BackOrder backOrder = JSON.parseObject(json,BackOrder.class);
        HashMap<Object,Object> hashMap = new HashMap<>();
        Order order =new Order();
        order.setUpdatetime(changeDate.getDate());
        order.setOrderconf(backOrder.getOrderconf());
        order.setCreatetime(changeDate.getDate());
        order.setCustomerid(backOrder.getCustomerid());
        order.setOrderstatus("create");
        order.setOrdertype(backOrder.getOrdertype());
        if(order.getOrdertype() != null){
            Customer customer = customerService.getCustomerByCustomerId(order.getCustomerid());
            if (customer.getCustomerType().equals("vip")){
                hashMap.put("vip","vip");
                orderDao.insertSelective(order);
                hashMap.put("order",order);
                return hashMap;
            }else {
                hashMap.put("error","非vip用户,拒接定制订单");
                return hashMap;
            }
        }
        //需要遍历的good列表
       List<Goodorder> goodorders = backOrder.getGoodorders();
        //最终获得的订单数组
        List<BackOrder> backOrders = new ArrayList<>();

        //筛选，归类相同representid的订单，以便于返回订单列表
        for(Goodorder goodorder:goodorders){
            if (goodorder.getGoodid() ==0 && goodorder.getGoodnum() ==0){
                hashMap.put("error","某商品id或数量缺失");
                return hashMap;
            }
            boolean hasSame = false;
            Good good =goodDao.selectByPrimaryKey(goodorder.getGoodid());
            if (good.getGoodname() == null){
                hashMap.put("error","无效的商品id");
                return hashMap;
            }
            for (BackOrder backOrder1:backOrders){
                if (backOrder1.getRepresentid() == good.getRepresentid()){
                    List<Goodorder> goodorders1 = backOrder1.getGoodorders();
                    BigDecimal totalPrice = backOrder1.getTotalprice();
                    totalPrice = totalPrice.add(good.getGoodprice().multiply(new BigDecimal(goodorder.getGoodnum()))) ;
                   backOrder1.setTotalprice(totalPrice);
                    goodorders1.add(goodorder);
                    backOrder1.setGoodorders(goodorders1);
                    hasSame = true;
                    break;
                }
            }
            if (!hasSame){
                BackOrder backOrder1 = new BackOrder();
               List<Goodorder> goodorders1 = new ArrayList<>();
               goodorders1.add(goodorder);
               BigDecimal totalPrice = good.getGoodprice().multiply(new BigDecimal(goodorder.getGoodnum()));
               backOrder1.setRepresentid(good.getRepresentid());
               backOrder1.setTotalprice(totalPrice);
               backOrder1.setGoodorders(goodorders1);
               backOrders.add(backOrder1);
            }

        }
        for(BackOrder backOrder1:backOrders){
            Order order1 =new Order();
            order1.setUpdatetime(changeDate.getDate());
            order1.setOrderconf(backOrder.getOrderconf());
            order1.setCreatetime(changeDate.getDate());
            order1.setCustomerid(backOrder.getCustomerid());
            order1.setOrderstatus("create");
            order1.setOrdertype(backOrder.getOrdertype());
            order1.setRepresentid(backOrder1.getRepresentid());
            order1.setOrdertype("shop");
            order1.setTotalprice(backOrder1.getTotalprice());
             int a = orderDao.insertSelective(order1);
              if (a ==0){
                  hashMap.put("订单添加失败","无知错误");
                  return hashMap;
              }
            backOrder1.setOrderid(order1.getOrderid());
              backOrder1.setCustomerid(order.getCustomerid());
              backOrder1.setTotalprice(order1.getTotalprice());
              backOrder1.setUpdatetime(order1.getUpdatetime());
              backOrder1.setOrderstatus(order1.getOrderstatus());
              backOrder1.setCreatetime(order1.getCreatetime());
              backOrder1.setOrderconf(order1.getOrderconf());
              backOrder1.setOrdertype(order1.getOrdertype());

            for (Goodorder goodorder:backOrder1.getGoodorders()){
                goodorder.setOrderid(order1.getOrderid());
                goodorder.setCreatetime(changeDate.getDate());
                goodorder.setUpdatetime(changeDate.getDate());
                int b = goodorderDao.insertSelective(goodorder);
                if (b == 0){
                    hashMap.put("订单商品添加失败","未知错误");
                }
            }

        }
        hashMap.put("orders",backOrders);
        hashMap.put("success","订单添加成功");
        return hashMap;

    }
       @PostMapping("customer/updateOrder")
     public HashMap<Object,Object> updateOrder(@RequestBody Order backOrder){
        HashMap<Object,Object> hashMap =new HashMap<>();
         if (backOrder.getOrderid() ==0 && backOrder.getOrderid() == null){
             hashMap.put("error","缺少订单id");
             return hashMap;
         }
         Order order = orderDao.selectByPrimaryKey(backOrder.getOrderid());
         if (order.getOrderid() == 0){
             hashMap.put("error","无该订单");
             return hashMap;
         }
         if (backOrder.getOrderconf() != null){
             order.setOrderconf(backOrder.getOrderconf());
         }
         if (backOrder.getOrderstatus() != null){
             order.setOrderstatus(backOrder.getOrderstatus());
         }
          order.setUpdatetime(changeDate.getDate());
         orderDao.updateByPrimaryKeySelective(order);
         hashMap.put("success","更新成功");
         hashMap.put("order",order);
         return hashMap;
     }
       @PostMapping("customer/updateGoodOrder")
     public HashMap<Object,Object> updateGoodOrder(@RequestBody Goodorder goodorder){
         HashMap<Object,Object> hashMap =new HashMap<>();
         if (goodorder.getGoodorderid() == null){
             hashMap.put("error","没有goodorderid");
             return hashMap;
         }
         Goodorder goodorder1 = new Goodorder();
         if (goodorder.getGoodnum() != null){
             goodorder1.setGoodnum(goodorder.getGoodnum());
         }
         goodorder1.setUpdatetime(changeDate.getDate());
         goodorderDao.updateByPrimaryKeySelective(goodorder1);
         hashMap.put("success","更新成功");
         hashMap.put("goodOrder",goodorder1);
         return hashMap;
     }
     @PostMapping("customer/deleteOrder")
     public HashMap<Object,Object> deleteOrder(@RequestBody Order order){
         HashMap<Object,Object> hashMap =new HashMap<>();
        if (order.getOrderid() == null){
            hashMap.put("error","缺少orderid");
            return hashMap;
        }
        List<Goodorder> goodorders = goodorderDao.getAllShit(order.getOrderid());
        if (goodorders.size()<1){
            hashMap.put("error","无效orderid");
            return hashMap;
        }
        for (Goodorder goodorder:goodorders){
            goodorderDao.deleteByPrimaryKey(goodorder.getGoodorderid());
        }
        orderDao.deleteByPrimaryKey(order.getOrderid());
        hashMap.put("success","删除成功");
        return hashMap;
     }
      @PostMapping("customer/deleteGoodOrder")
    public HashMap<Object,Object> deleteGoodOrder(@RequestBody Goodorder goodorder){
        HashMap<Object,Object> hashMap =new HashMap<>();
        if (goodorder.getGoodorderid() == null){
            hashMap.put("error","缺少goodorderid");
            return hashMap;
        }
    int a =  goodorderDao.deleteByPrimaryKey(goodorder.getGoodorderid());
      if (a != 0){
          hashMap.put("success","删除成功");
          return hashMap;
      }
        hashMap.put("error","无效的goodorderid");
        return hashMap;
    }
}






