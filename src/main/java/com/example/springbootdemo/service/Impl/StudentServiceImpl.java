package com.example.springbootdemo.service.Impl;

import com.example.springbootdemo.entity.Studentdb;
import com.example.springbootdemo.mapper.StudentdbMapper;
import com.example.springbootdemo.service.StudentdbService;
import com.example.springbootdemo.util.ExcelUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentdbService {
    @Resource
    private StudentdbMapper studentdbMapper;

    @Override
    public void insertdb(InputStream in, MultipartFile file) throws Exception {
        System.out.println(file.getOriginalFilename());
        List<List<Object>> listob = ExcelUtil.getBankListByExcel(in, file.getOriginalFilename());
        List<Studentdb> salaryList = new ArrayList<Studentdb>();
        //遍历listob数据，把数据放到List中
        for (int i = 0; i < listob.size(); i++) {
            List<Object> ob = listob.get(i);
            Studentdb studentdb = new Studentdb();
            //设置编号
            // studentdb.setSerial(SerialUtil.salarySerial());
            //通过遍历实现把每一列封装成一个model中，再把所有的model用List集合装载,从0开始
/*                  int类型
            int studentid=Integer.parseInt(String.valueOf(ob.get(0)));
            studentdb.setStudentid(studentid);
                  string类型
            studentdb.setName(String.valueOf(ob.get(2)));
                double类型
            salarymanage.setMoney(Double.parseDouble(ob.get(8).toString()));
                可能是date类型
            salarymanage.setSalaryDate(salaryDate);
 */

            studentdb.setUsername(String.valueOf(ob.get(1)));
            studentdb.setPassword(String.valueOf(ob.get(2)));
            int studentid=Integer.parseInt(String.valueOf(ob.get(3)));
            studentdb.setEnable(studentid);

            salaryList.add(studentdb);
        }
        //批量插入
        studentdbMapper.insertdb(salaryList);
    }
}