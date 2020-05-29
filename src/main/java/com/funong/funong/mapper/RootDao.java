package com.funong.funong.mapper;

import com.funong.funong.pojo.Root;

public interface RootDao {
    int deleteByPrimaryKey(Integer rootid);

    int insert(Root record);

    int insertSelective(Root record);

    Root selectByPrimaryKey(Integer rootid);

    int updateByPrimaryKeySelective(Root record);

    int updateByPrimaryKey(Root record);
}