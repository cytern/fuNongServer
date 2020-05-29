package com.funong.funong.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * visit
 * @author 
 */
@Data
public class Visit implements Serializable {
    private Integer visitId;

    /**
     * 代表id
     */
    private Integer representId;

    private Date createTime;

    private Date updateTime;

    /**
     * 农户id
     */
    private Integer farmerId;

    /**
     * 图片
     */
    private String visitUrl;

    /**
     * 简介
     */
    private String visitConf;

    private static final long serialVersionUID = 1L;
}