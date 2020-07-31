package com.example.springbootdemo.mapper;

import com.example.springbootdemo.entity.Studentdb;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentdbMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Studentdb record);

    int insertSelective(Studentdb record);

    Studentdb selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Studentdb record);

    int updateByPrimaryKey(Studentdb record);
    boolean insertdb(List<Studentdb> list);
}