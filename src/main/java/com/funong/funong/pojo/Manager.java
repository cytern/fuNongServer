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

    private String managername;

    private String managersex;

    private String managerconf;

    private String managerage;

    private String managerurl;

    private Date createtime;

    private Date updatetime;

    private Integer userid;

    private static final long serialVersionUID = 1L;
}