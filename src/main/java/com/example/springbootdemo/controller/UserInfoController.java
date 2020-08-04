package com.example.springbootdemo.controller;

import com.example.springbootdemo.entity.UserInfo;
import com.example.springbootdemo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;


    @RequestMapping("/getAll")
    @ResponseBody
    public List<UserInfo> selectAll() {
        return userInfoService.selectAll();
    }

}
