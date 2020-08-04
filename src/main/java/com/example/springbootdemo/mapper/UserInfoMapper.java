package com.example.springbootdemo.mapper;

import com.example.springbootdemo.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    int insertdb(List<UserInfo> list);

    List<UserInfo> selectAll();

    int deleteByIdlist(@Param("ids") Integer[] ids);

}