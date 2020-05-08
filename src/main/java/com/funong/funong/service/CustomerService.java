package com.funong.funong.service;

import com.funong.funong.pojo.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomer();
    Customer getCustomerByUserId(int userId);
    int updateCustomer(Customer customer);
    int addCustomer(Customer customer);
    Customer getCustomerByCustomerId(int customerId);
}
