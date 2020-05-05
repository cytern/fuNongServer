package com.funong.funong.mapper;

import com.funong.funong.pojo.Represent;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RepresentDao {
    int deleteByPrimaryKey(Integer representid);

    int insert(Represent record);

    int insertSelective(Represent record);

    Represent selectByPrimaryKey(Integer representid);

    int updateByPrimaryKeySelective(Represent record);

    int updateByPrimaryKey(Represent record);

    List<Represent> getAllRepresent();

    Represent getRepresentById(Integer userid);
}