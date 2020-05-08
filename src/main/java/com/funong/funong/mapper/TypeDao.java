package com.funong.funong.mapper;

import com.funong.funong.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface TypeDao {
    int deleteByPrimaryKey(Integer typeId);

    int insert(Type record);

    int insertSelective(Type record);

    Type selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);

    Type selectByTypeName(String typeName);

    List<Type> getAllType();
}