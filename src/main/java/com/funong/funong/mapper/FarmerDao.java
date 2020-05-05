package com.funong.funong.mapper;

import com.funong.funong.pojo.Farmer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FarmerDao {
    int deleteByPrimaryKey(Integer farmerid);

    int insert(Farmer record);

    int insertSelective(Farmer record);

    Farmer selectByPrimaryKey(Integer farmerid);

    int updateByPrimaryKeySelective(Farmer record);

    int updateByPrimaryKey(Farmer record);
    List<Farmer> getAllFarmer();
    Farmer getFarmerByUserId(Integer userid);
}