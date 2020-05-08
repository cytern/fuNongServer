package com.funong.funong.mapper;

import com.funong.funong.pojo.Img;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface ImgDao {
    int deleteByPrimaryKey(String imgUrl);

    int insert(Img record);

    int insertSelective(Img record);

    Img selectByPrimaryKey(String imgUrl);

    int updateByPrimaryKeySelective(Img record);

    int updateByPrimaryKey(Img record);

    List<Img> getAllImg();
}