package com.example.springbootdemo.service.Impl;

import com.example.springbootdemo.entity.User;
import com.example.springbootdemo.mapper.UserMapper;
import com.example.springbootdemo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
