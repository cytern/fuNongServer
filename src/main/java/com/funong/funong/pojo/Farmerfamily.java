package com.funong.funong.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * farmerfamily
 * @author 
 */
@Data
public class Farmerfamily implements Serializable {
    private Integer farmerfamilyid;

    private Integer farmerid;

    private String name;

    private String sex;

    private String relationship;

    private String work;

    private Date createtime;

    private Date updatetime;

    private static final long serialVersionUID = 1L;
}