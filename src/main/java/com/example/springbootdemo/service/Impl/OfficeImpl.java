package com.example.springbootdemo.service.Impl;

import com.example.springbootdemo.entity.Office;
import com.example.springbootdemo.mapper.OfficeMapper;
import com.example.springbootdemo.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OfficeImpl implements OfficeService {

    @Resource
    OfficeMapper officeMapper;

    @Override
    public Office selectAll(String id) {
        return officeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Office> getChildrenByParentId(String parentId) {
        if (parentId==null||parentId.equals("-1")){
            parentId="0";
        }
        return officeMapper.getChildrenByParentId(parentId);
    }

    @Override
    public int updateByPrimaryKeySelective(Office record) {
        return officeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return officeMapper.deleteByPrimaryKey(id);
    }
}
