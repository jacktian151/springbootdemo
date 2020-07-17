package com.example.springbootdemo.service;

import com.example.springbootdemo.entity.Office;

import java.util.List;

public interface OfficeService  {

    Office selectAll(String id);

    List<Office> getChildrenByParentId(String parentId);
    int updateByPrimaryKeySelective(Office record);
    int deleteByPrimaryKey(String id);


}
