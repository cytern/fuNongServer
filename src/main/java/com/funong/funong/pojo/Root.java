package com.funong.funong.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * root
 * @author 
 */
@Data
public class Root implements Serializable {
    private String rootid;

    private String rootname;

    private String rooturl;

    private String rootconf;

    private Integer userid;

    private String rootphone;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createtime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updatetime;

    private static final long serialVersionUID = 1L;
}