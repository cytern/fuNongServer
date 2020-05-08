package com.funong.funong.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private String createTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private String updateTime;
    private String customerPhone;
}
