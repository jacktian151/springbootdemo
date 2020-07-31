package com.example.springbootdemo.service;

import com.example.springbootdemo.entity.UserInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface UserInfoService {
    void insertdb(InputStream in, MultipartFile file) throws Exception;
    List<UserInfo> selectAll();

}
