package com.example.springbootdemo.service;

import com.example.springbootdemo.entity.WxUser;

public interface WxUserService {
    int insertSelective(WxUser record);
    int selectByOpenId(String openId);

}
