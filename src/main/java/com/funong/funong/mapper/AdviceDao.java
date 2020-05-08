package com.funong.funong.mapper;

import com.funong.funong.pojo.Advice;

public interface AdviceDao {
    int deleteByPrimaryKey(Integer adviceId);

    int insert(Advice record);

    int insertSelective(Advice record);

    Advice selectByPrimaryKey(Integer adviceId);

    int updateByPrimaryKeySelective(Advice record);

    int updateByPrimaryKey(Advice record);
}