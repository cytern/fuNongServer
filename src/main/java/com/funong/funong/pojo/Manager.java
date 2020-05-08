package com.funong.funong.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createtime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updatetime;

    private Integer userid;

    private static final long serialVersionUID = 1L;
}