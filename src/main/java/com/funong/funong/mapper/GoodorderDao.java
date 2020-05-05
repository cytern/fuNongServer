package com.funong.funong.mapper;

import com.funong.funong.pojo.Goodorder;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface GoodorderDao {
    int deleteByPrimaryKey(Integer goodorderid);

    int insert(Goodorder record);

    int insertSelective(Goodorder record);

    Goodorder selectByPrimaryKey(Integer goodorderid);

    int updateByPrimaryKeySelective(Goodorder record);

    int updateByPrimaryKey(Goodorder record);
}