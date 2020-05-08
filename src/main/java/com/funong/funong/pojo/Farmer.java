package com.funong.funong.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * farmer
 * @author 
 */
@Data
public class Farmer implements Serializable {
    private Integer farmerid;

    private String farmername;

    private Integer userid;

    private String farmeraddress;

    private String farmerio;

    private String farmerconf;

    private String farmerphone;

    private Integer representid;

    private Integer farmergrade;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createtime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updatetime;

    private static final long serialVersionUID = 1L;
}