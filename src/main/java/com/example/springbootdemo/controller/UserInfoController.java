package com.example.springbootdemo.controller;

import com.example.springbootdemo.entity.UserInfo;
import com.example.springbootdemo.service.UserInfoService;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @RequestMapping(value = "/delete" , produces = { "application/json;charset=UTF-8"} )
    @ResponseBody
    public int deleteByIdlist(@RequestBody Integer[] lists) {
        return userInfoService.deleteByIdlist(lists);

    }
}
