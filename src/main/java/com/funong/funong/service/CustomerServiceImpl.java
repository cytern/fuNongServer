package com.funong.funong.service;

import com.funong.funong.mapper.CustomerMapper;
import com.funong.funong.pojo.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerMapper customerMapper;

    private void setCustomerMapper(CustomerMapper customerMapper){
        this.customerMapper = customerMapper;
    }
    @Override
    public List<Customer> getAllCustomer() {
        return customerMapper.getAllCustomer();
    }

    @Override
    public Customer getCustomerByUserId(int userId) {
        return customerMapper.getCustomerByUserId(userId);
    }

    @Override
    public int updateCustomer(Customer customer) {
        return customerMapper.updateCustomer(customer);
    }

    @Override
    public int addCustomer(Customer customer) {
        return customerMapper.addCustomer(customer);
    }

    @Override
    public Customer getCustomerByCustomerId(int customerId) {
        return customerMapper.getCustomerByCustomerId(customerId);
    }
}
