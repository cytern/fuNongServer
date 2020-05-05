package com.funong.funong.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int userId;
    private String userName;
    private String userPassword;
    private String userStatus;
    private String userType;
    private String createTime;
    private String updateTime;
}
