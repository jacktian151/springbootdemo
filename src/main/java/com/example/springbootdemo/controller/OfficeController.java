package com.example.springbootdemo.controller;

import com.example.springbootdemo.entity.Office;
import com.example.springbootdemo.service.OfficeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/office")
public class OfficeController {
    @Resource
    OfficeService officeService;

    @RequestMapping("/test")
    @ResponseBody
    public Office selectAll(String id){
        return officeService.selectAll(id);
    }

    @RequestMapping("/")
    public ModelAndView office(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("view/officePage");
        return mv;
    }

    @RequestMapping("/getChildren")
    @ResponseBody
    public Map<String,Object> getChildren(String parentId){
        Map<String,Object> map = new HashMap<>();
        List<Office> officeList = officeService.getChildrenByParentId(parentId);
        map.put("data",officeList);
        return map;
    }

    @RequestMapping("/update")
    @ResponseBody
    public int updateByPrimaryKeySelective(Office record) {
        return officeService.updateByPrimaryKeySelective(record);
    }

    @RequestMapping("/del")
    @ResponseBody
    public int deleteByPrimaryKey(String id) {
        return officeService.deleteByPrimaryKey(id);
    }

}
