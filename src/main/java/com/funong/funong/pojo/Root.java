package com.funong.funong.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * root
 * @author 
 */
@Data
public class Root implements Serializable {
    private Integer rootid;

    /**
     * 姓名
     */
    private String rootname;

    /**
     * 头像
     */
    private String rooturl;

    /**
     * 简介
     */
    private String rootconf;

    private Integer userid;

    /**
     * 电话
     */
    private String rootphone;

    private Date createtime;

    private Date updatetime;

    private static final long serialVersionUID = 1L;
}