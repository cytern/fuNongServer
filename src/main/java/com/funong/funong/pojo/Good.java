package com.funong.funong.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * good
 * @author 
 */
@Data
public class Good implements Serializable {
    private Integer goodid;

    private String goodname;

    private String goodstatus;

    private Integer farmerid;

    private BigDecimal goodprice;

    private String goodlocation;

    private Integer representid;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createtime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updatetime;

    private Integer goodnum;

    private String goodsize;

    private String goodurl;

    private String goodtype;

    private static final long serialVersionUID = 1L;
}