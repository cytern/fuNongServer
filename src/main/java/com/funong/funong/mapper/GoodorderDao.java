package com.funong.funong.mapper;

import com.funong.funong.backPojo.BackTypeIndex;
import com.funong.funong.pojo.Goodorder;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GoodorderDao {
    int deleteByPrimaryKey(Integer goodorderid);

    int insert(Goodorder record);

    int insertSelective(Goodorder record);

    Goodorder selectByPrimaryKey(Integer goodorderid);

    int updateByPrimaryKeySelective(Goodorder record);

    int updateByPrimaryKey(Goodorder record);

    List<Goodorder> getByGoodId(BackTypeIndex backTypeIndex);

    List<Goodorder> getByOrderId(BackTypeIndex backTypeIndex);

    List<Goodorder> getAll(BackTypeIndex backTypeIndex);
    List<Goodorder> getAllShit(int orderId);

    int getNumAll();
    int getNumOrderId();
    int getNumGoodId();
}