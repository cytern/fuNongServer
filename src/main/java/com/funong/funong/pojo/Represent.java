package com.funong.funong.pojo;

import java.io.Serializable;
import java.util.Date;
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

    private Date createtime;

    private Date updatetime;

    private static final long serialVersionUID = 1L;
}