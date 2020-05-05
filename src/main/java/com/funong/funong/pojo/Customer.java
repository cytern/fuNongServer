package com.funong.funong.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private int customerId;
    private int userId;
    private String customerName;
    private String customerConf;
    private String customerUrl;
    private String customerType;
    private String customerGrade;
    private String createTime;
    private String updateTime;
    private String customerPhone;
}
