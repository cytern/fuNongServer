package com.funong.funong.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * notice
 * @author 
 */
@Data
public class Notice implements Serializable {
    /**
     * id
     */
    private Integer noticeId;

    /**
     * 通知时间长度
     */
    private String noticeTimeAll;

    private Date createTime;

    private Date updateTime;

    /**
     * 用户id
     */
    private Integer userId;

    private static final long serialVersionUID = 1L;
}