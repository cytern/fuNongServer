package com.funong.funong.pojo;

import java.io.Serializable;
import java.util.Date;
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

    private Date createtime;

    private Date updatetime;

    private static final long serialVersionUID = 1L;
}