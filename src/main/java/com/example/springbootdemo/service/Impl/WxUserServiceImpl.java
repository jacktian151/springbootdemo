package com.example.springbootdemo.service.Impl;

import com.example.springbootdemo.entity.WxUser;
import com.example.springbootdemo.mapper.WxUserMapper;
import com.example.springbootdemo.service.WxUserService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WxUserServiceImpl implements WxUserService {
    @Autowired
    WxUserMapper wxUserMapper;


    @Override
    public int insertSelective(WxUser wxUser) {
        return  wxUserMapper.insert(wxUser);
    }

    @Override
    public int selectByOpenId(@Param("openId") String openId) {
        int has =  wxUserMapper.selectByOpenId(openId);
        if(has!=0){
            return 1;
        }else {
            return 0;
        }
    }

}
