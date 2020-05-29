package com.funong.funong.mapper;

import com.funong.funong.pojo.Tech;

public interface TechDao {
    int deleteByPrimaryKey(Integer techId);

    int insert(Tech record);

    int insertSelective(Tech record);

    Tech selectByPrimaryKey(Integer techId);

    int updateByPrimaryKeySelective(Tech record);

    int updateByPrimaryKey(Tech record);
}