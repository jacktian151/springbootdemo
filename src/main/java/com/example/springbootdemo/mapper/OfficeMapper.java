package com.example.springbootdemo.mapper;

import com.example.springbootdemo.entity.Office;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface OfficeMapper {
    int deleteByPrimaryKey(String id);

    int insert(Office record);

    int insertSelective(Office record);

    Office selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Office record);

    int updateByPrimaryKey(Office record);

    //根据父id查询所有子信息
    List<Office> getChildrenByParentId(@Param("parentId") String parentId);
}