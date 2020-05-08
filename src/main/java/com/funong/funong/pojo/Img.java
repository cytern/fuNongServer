package com.funong.funong.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * img
 * @author 
 */
@Data
public class Img implements Serializable {
    private String imgUrl;

    private Date createTime;

    private String imgStatus;

    private static final long serialVersionUID = 1L;
}