package com.funong.funong.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * represent
 * @author 
 */
@Data
public class Represent implements Serializable {
    private Integer representid;

    private Integer userid;

    private String representname;

    private String representphone;

    private String representurl;

    private String representconf;

    private String representage;

    private String representsex;

    private Integer representgrade;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createtime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updatetime;

    private static final long serialVersionUID = 1L;
}