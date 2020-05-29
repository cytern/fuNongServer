package com.funong.funong.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * manager
 * @author 
 */
@Data
public class Manager implements Serializable {
    private Integer managerid;

    /**
     * 姓名
     */
    private String managername;

    /**
     * 性别
     */
    private String managersex;

    /**
     * 简介
     */
    private String managerconf;

    /**
     * 年龄
     */
    private String managerage;

    /**
     * 头像
     */
    private String managerurl;

    private Date createtime;

    private Date updatetime;

    /**
     * 用户ID
     */
    private Integer userid;

    /**
     * 电话
     */
    private String managerphone;

    private static final long serialVersionUID = 1L;
}