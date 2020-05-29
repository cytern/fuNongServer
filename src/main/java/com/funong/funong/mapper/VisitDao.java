package com.funong.funong.mapper;

import com.funong.funong.pojo.Visit;

public interface VisitDao {
    int deleteByPrimaryKey(Integer visitId);

    int insert(Visit record);

    int insertSelective(Visit record);

    Visit selectByPrimaryKey(Integer visitId);

    int updateByPrimaryKeySelective(Visit record);

    int updateByPrimaryKey(Visit record);
}