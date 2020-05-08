package com.funong.funong.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * order
 * @author 
 */
@Data
public class Order implements Serializable {
    private Integer orderid;

    private BigDecimal totalprice;

    private Integer customerid;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createtime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updatetime;

    private String orderstatus;

    private String orderconf;

    private String ordertype;

    private int representid;

    private static final long serialVersionUID = 1L;
}