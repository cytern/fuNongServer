package com.funong.funong.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    private int tokenId;
    private String token;
    private String tokenType;
    private String createTime;
    private String updateTime;
    private int userId;
}
