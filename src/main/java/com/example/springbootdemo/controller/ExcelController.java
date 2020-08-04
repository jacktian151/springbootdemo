package com.example.springbootdemo.controller;

import com.example.springbootdemo.service.StudentdbService;
import com.example.springbootdemo.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;

/**
 * 路径：com.example.demo.controller
 * 类名：
 * 功能：excel导入导出
 */
@Slf4j
@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private StudentdbService studentdbService;

    /**
     * 方法名：
     * 功能：导出
     * 描述：文件格式为xls或xlsx
     */
//    @GetMapping("/export")
//    public String exportExcel(HttpServletResponse response, String fileName, Integer pageNum, Integer pageSize) {
//        if (fileName == null || "".equals(fileName)) {
//            return "文件名不能为空！";
//        } else {
//            if (fileName.endsWith("xls")) {
//                Boolean isOk = excelService.exportExcel(response, fileName, 1, 10);
//                if (isOk) {
//                    return "导出成功！";
//                } else {
//                    return "导出失败！";
//                }
//            }
//            return "文件格式有误！";
//        }
//    }

        @RequestMapping("/import")
        @ResponseBody
        public String impotr(HttpServletRequest request) throws Exception {
            //获取上传的文件
            MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
            MultipartFile file = multipart.getFile("file");
            String filename = request.getParameter("filename");
            InputStream in = file.getInputStream();
            System.out.println(filename);
            //数据导入
            userInfoService.insertdb(in,file);
            in.close();
            return "true";
        }


}
