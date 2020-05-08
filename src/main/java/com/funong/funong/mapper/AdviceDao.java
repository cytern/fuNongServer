package com.funong.funong.mapper;

import com.funong.funong.pojo.Advice;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface AdviceDao {
    int deleteByPrimaryKey(Integer adviceId);

    int insert(Advice record);

    int insertSelective(Advice record);

    Advice selectByPrimaryKey(Integer adviceId);

    int updateByPrimaryKeySelective(Advice record);

    int updateByPrimaryKey(Advice record);

    List<Advice> getAdviceByType(String goodType);

    List<Advice> getAllAdvice();
}