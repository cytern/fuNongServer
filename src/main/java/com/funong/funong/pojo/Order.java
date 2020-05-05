package com.funong.funong.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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

    private Date createtime;

    private Date updatetime;

    private String orderstatus;

    private String orderconf;

    private String ordertype;

    private static final long serialVersionUID = 1L;
}