package com.funong.funong.mapper;

import com.funong.funong.pojo.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CustomerMapper {
    List<Customer> getAllCustomer();
    Customer getCustomerByUserId(int userId);
    int updateCustomer(Customer customer);
    int addCustomer(Customer customer);
    Customer getCustomerByCustomerId(int customerId);
}
