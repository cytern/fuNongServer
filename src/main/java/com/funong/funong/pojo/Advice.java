package com.funong.funong.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * advice
 * @author 
 */
@Data
public class Advice implements Serializable {
    private Integer adviceId;

    private Integer goodid;

    private String goodType;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    private static final long serialVersionUID = 1L;
}