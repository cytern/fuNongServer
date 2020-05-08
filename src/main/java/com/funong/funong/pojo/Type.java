package com.funong.funong.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * type
 * @author 
 */
@Data
public class Type implements Serializable {
    private Integer typeId;

    private String typeName;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}