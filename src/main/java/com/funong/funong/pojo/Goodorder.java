package com.funong.funong.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createtime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updatetime;

    private static final long serialVersionUID = 1L;
}