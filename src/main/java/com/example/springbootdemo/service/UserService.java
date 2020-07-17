package com.example.springbootdemo.service;

import com.example.springbootdemo.entity.User;

public interface UserService {
    User selectByPrimaryKey(Integer id);
}
