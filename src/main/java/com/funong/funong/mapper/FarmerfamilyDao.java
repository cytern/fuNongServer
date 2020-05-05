package com.funong.funong.mapper;

import com.funong.funong.pojo.Farmerfamily;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FarmerfamilyDao {
    int deleteByPrimaryKey(Integer farmerfamilyid);

    int insert(Farmerfamily record);

    int insertSelective(Farmerfamily record);

    Farmerfamily selectByPrimaryKey(Integer farmerfamilyid);

    int updateByPrimaryKeySelective(Farmerfamily record);

    int updateByPrimaryKey(Farmerfamily record);
}