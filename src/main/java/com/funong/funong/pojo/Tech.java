package com.funong.funong.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * tech
 * @author 
 */
@Data
public class Tech implements Serializable {
    private Integer techId;

    /**
     * 标题
     */
    private String techTitle;

    /**
     * 内容
     */
    private String techBody;

    /**
     * 标签
     */
    private String techTag;

    private Date createTime;

    private Date updateTime;

    /**
     * 用户ID
     */
    private Integer userId;

    private static final long serialVersionUID = 1L;
}