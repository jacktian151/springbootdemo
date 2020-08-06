package com.example.springbootdemo.service.Impl;

import com.example.springbootdemo.entity.Studentdb;
import com.example.springbootdemo.entity.UserInfo;
import com.example.springbootdemo.mapper.UserInfoMapper;
import com.example.springbootdemo.service.UserInfoService;
import com.example.springbootdemo.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;

    /**
     * Excel导入
     * */
    @Override
    public void insertdb(InputStream in, MultipartFile file) throws Exception {
        System.out.println(file.getOriginalFilename());
        List<List<Object>> listob = ExcelUtil.getBankListByExcel(in, file.getOriginalFilename());
        List<UserInfo> salaryList = new ArrayList<UserInfo>();
        //遍历listob数据，把数据放到List中
        for (int i = 0; i < listob.size(); i++) {
            List<Object> ob = listob.get(i);
            UserInfo studentdb = new UserInfo();
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
                    float类型
            studentdb.setPaymoney(Float.parseFloat(ob.get(3).toString()));
 */
            studentdb.setOrdernum(String.valueOf(ob.get(0)));
            studentdb.setVipname(String.valueOf(ob.get(1)));
            studentdb.setPaymoney(Float.parseFloat(ob.get(2).toString()));
            studentdb.setName(String.valueOf(ob.get(3)));
            studentdb.setProvince(String.valueOf(ob.get(4)));
            studentdb.setCity(String.valueOf(ob.get(5)));
            studentdb.setArea(String.valueOf(ob.get(6)));
            studentdb.setAddress(String.valueOf(ob.get(7)));
            studentdb.setTel(String.valueOf(ob.get(8)));
            studentdb.setProductname(String.valueOf(ob.get(9)));
            studentdb.setRemarks(String.valueOf(ob.get(10)));

            salaryList.add(studentdb);
        }
        //批量插入
        userInfoMapper.insertdb(salaryList);
        //salarymanageDao.insertInfoBatch(salaryList);
    }

    @Override
    public List<UserInfo> selectAll() {
        return userInfoMapper.selectAll();
    }


    @Override
    public int deleteByIdlist(Integer[] ids) {
        return userInfoMapper.deleteByIdlist(ids);
    }

    @Override
    public List<UserInfo> selectByPage(Integer currentPage, Integer pageSize) {
        int cur = (currentPage-1)*pageSize;
        return userInfoMapper.selectByPage(cur,pageSize);
    }

    @Override
    public int usercount() {
        return userInfoMapper.usercount();
    }
}
