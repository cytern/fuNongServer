package com.funong.funong.mapper;

import com.funong.funong.backPojo.BackOrderLimit;
import com.funong.funong.backPojo.BackPageIndex;
import com.funong.funong.backPojo.BackTypeIndex;
import com.funong.funong.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderDao {
    int deleteByPrimaryKey(Integer orderid);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer orderid);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> getAllOrder(BackTypeIndex backTypeIndex);

    List<Order> getOrderByCustomerId(BackTypeIndex backTypeIndex);

    List<Order> getOrderByOrderType(BackTypeIndex backTypeIndex);

    List<Order> getOrderByRepresentId(BackTypeIndex backTypeIndex);

    List<Order> getOrderByCustomerIdType(BackTypeIndex backTypeIndex);

    List<Order> getOrderByRepresentIdType(BackTypeIndex backTypeIndex);
    int getNumAll();
    int getNumCustomer(Integer customerId);
    int getNumType(String orderType);
    int getNumRepresent(Integer representId);
    int getNumCustomerIdType(BackTypeIndex backTypeIndex);
    int getNumRepresentIdType(BackTypeIndex backTypeIndex);
}