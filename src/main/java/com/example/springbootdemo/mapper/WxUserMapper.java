package com.example.springbootdemo.mapper;

import com.example.springbootdemo.entity.WxUser;
import org.springframework.stereotype.Repository;

@Repository
public interface WxUserMapper {
    int deleteByPrimaryKey(Integer id);


    int insertSelective(WxUser record);

    WxUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxUser record);

    int updateByPrimaryKey(WxUser record);

    int insert(WxUser wxUser);

    int selectByOpenId(String openId);

}