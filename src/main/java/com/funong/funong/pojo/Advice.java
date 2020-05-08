package com.funong.funong.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * advice
 * @author 
 */
@Data
public class Advice implements Serializable {
    private Integer adviceId;

    private Integer goodid;

    private String goodType;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}