package com.funong.funong.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * goodorder
 * @author 
 */
@Data
public class Goodorder implements Serializable {
    private Integer goodorderid;

    private Integer goodid;

    private Integer goodnum;

    private Integer orderid;

    private Date createtime;

    private Date updatetime;

    private static final long serialVersionUID = 1L;
}