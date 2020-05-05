package com.funong.funong.mapper;

import com.funong.funong.pojo.Root;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RootDao {
    int insert(Root record);

    int insertSelective(Root record);

    List<Root> getAllRoot();

    Root getRootByUserId(Integer userid);


}