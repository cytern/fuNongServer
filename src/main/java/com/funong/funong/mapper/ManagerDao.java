package com.funong.funong.mapper;

import com.funong.funong.pojo.Manager;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ManagerDao {
    int deleteByPrimaryKey(Integer managerid);

    int insert(Manager record);

    int insertSelective(Manager record);

    Manager selectByPrimaryKey(Integer managerid);

    int updateByPrimaryKeySelective(Manager record);

    int updateByPrimaryKey(Manager record);
    List<Manager> getAllManager();
    Manager getManagerByUserId(Integer userid);
}