package com.example.springbootdemo.util;

import org.apache.http.client.utils.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExcelUtil {
    private final static String excel2003L = ".xls";    //2003- 版本的excel
    private final static String excel2007U = ".xlsx";   //2007+ 版本的excel

    /**
     * Excel导入
     */
    public static List<List<Object>> getBankListByExcel(InputStream in, String fileName) throws Exception {
        List<List<Object>> list = null;
        //创建Excel工作薄
        Workbook work = getWorkbook(in, fileName);
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        list = new ArrayList<List<Object>>();
        //遍历Excel中所有的sheet
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }
            //遍历当前sheet中的所有行
            //包涵头部，所以要小于等于最后一列数,这里也可以在初始值加上头部行数，以便跳过头部
            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                //读取一行
                row = sheet.getRow(j);
                //去掉空行和表头
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }
                //遍历所有的列
                List<Object> li = new ArrayList<Object>();
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    li.add(getCellValue(cell));
                }
                list.add(li);
            }
        }
        return list;
    }

    /**
     * 判断文件是哪种形式
     */
    private static Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (excel2003L.equals(fileType)) {
            wb = new HSSFWorkbook(inStr);  //2003-
        } else if (excel2007U.equals(fileType)) {
            wb = new XSSFWorkbook(inStr);  //2007+
        } else {
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }

    /**
     * 描述：对表格中数值进行格式化
     */
    public static Object getCellValue(Cell cell) {
        Object value = null;
        DecimalFormat df = new DecimalFormat("0");  //格式化字符类型的数字
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化
        DecimalFormat df2 = new DecimalFormat("0.00");  //格式化数字
        switch (cell.getCellType()) {
            case STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case NUMERIC:
                if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                    value = df.format(cell.getNumericCellValue());
                } else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                    value = sdf.format(cell.getDateCellValue());
                } else {
                    value = df2.format(cell.getNumericCellValue());
                }
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case BLANK:
                value = "";
                break;
            default:
                break;
        }
        return value;
    }
    /**
     * 导入完成
     * */
    /**
     * Excel表格导出
     * */


}

//import com.example.springbootdemo.entity.ExcelData;
//import com.example.springbootdemo.entity.UserInfo;
//import com.example.springbootdemo.entity.UserInfo;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.poi.hssf.usermodel.*;
//import org.apache.poi.ss.usermodel.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.BufferedOutputStream;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.apache.poi.ss.usermodel.CellType.*;
//
//
//@Slf4j
//public class ExcelUtil {


//    /**
//     * 方法名：exportExcel
//     * 功能：导出Excel
//     */
//    public static void exportExcel(HttpServletResponse response, ExcelData data) {
//        log.info("导出解析开始，fileName:{}", data.getFileName());
//        try {
//            //实例化HSSFWorkbook
//            HSSFWorkbook workbook = new HSSFWorkbook();
//            //创建一个Excel表单，参数为sheet的名字
//            HSSFSheet sheet = workbook.createSheet("sheet");
//            //设置表头
//            setTitle(workbook, sheet, data.getHead());
//            //设置单元格并赋值
//            setData(sheet, data.getData());
//            //设置浏览器下载
//            setBrowser(response, workbook, data.getFileName());
//            log.info("导出解析成功!");
//        } catch (Exception e) {
//            log.info("导出解析失败!");
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 方法名：setTitle
//     * 功能：设置表头
//     */
//    private static void setTitle(HSSFWorkbook workbook, HSSFSheet sheet, String[] str) {
//        try {
//            HSSFRow row = sheet.createRow(0);
//            //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
//            for (int i = 0; i <= str.length; i++) {
//                sheet.setColumnWidth(i, 15 * 256);
//            }
//            //设置为居中加粗,格式化时间格式
//            HSSFCellStyle style = workbook.createCellStyle();
//            HSSFFont font = workbook.createFont();
//            font.setBold(true);
//            style.setFont(font);
//            style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
//            //创建表头名称
//            HSSFCell cell;
//            for (int j = 0; j < str.length; j++) {
//                cell = row.createCell(j);
//                cell.setCellValue(str[j]);
//                cell.setCellStyle(style);
//            }
//        } catch (Exception e) {
//            log.info("导出时设置表头失败！");
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 方法名：setData
//     * 功能：表格赋值
//     */
//    private static void setData(HSSFSheet sheet, List<String[]> data) {
//        try {
//            int rowNum = 1;
//            for (int i = 0; i < data.size(); i++) {
//                HSSFRow row = sheet.createRow(rowNum);
//                for (int j = 0; j < data.get(i).length; j++) {
//                    row.createCell(j).setCellValue(data.get(i)[j]);
//                }
//                rowNum++;
//            }
//            log.info("表格赋值成功！");
//        } catch (Exception e) {
//            log.info("表格赋值失败！");
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 方法名：setBrowser
//     * 功能：使用浏览器下载
//     */
//    private static void setBrowser(HttpServletResponse response, HSSFWorkbook workbook, String fileName) {
//        try {
//            //清空response
//            response.reset();
//            //设置response的Header
//            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
//            OutputStream os = new BufferedOutputStream(response.getOutputStream());
//            response.setContentType("application/vnd.ms-excel;charset=gb2312");
//            //将excel写入到输出流中
//            workbook.write(os);
//            os.flush();
//            os.close();
//            log.info("设置浏览器下载成功！");
//        } catch (Exception e) {
//            log.info("设置浏览器下载失败！");
//            e.printStackTrace();
//        }
//
//    }
//
//
//    /**
//     * 方法名：importExcel
//     * 功能：导入
//     */
//    public static List<UserInfo> importExcel(InputStream in, MultipartFile file) throws Exception {
//        UserInfo userInfo = null;
//        log.info("导入解析开始");
//        try {
//            List<UserInfo> list = new ArrayList<UserInfo>();
////          1.创建工作薄
//            HSSFWorkbook workbook =new HSSFWorkbook(in);
////          2.获取并设置sheet的值
//            HSSFSheet sheet = workbook.getSheetAt(0);
////          2.1获取sheet页的行数
//            int rows = sheet.getPhysicalNumberOfRows();
//            for (int i = 0; i < rows; i++) {
////          3.跳过标题行
//                if (i == 0) {
//                    continue;
//                }
////          4.获取当前行的数据
//                HSSFRow row = sheet.getRow(i);
////          5.获取列数
//                int physicalNumberOfCells = row.getPhysicalNumberOfCells();
//                userInfo =new UserInfo();
//                for (int j=1 ; j<physicalNumberOfCells ;j++){
//                    HSSFCell cell = row.getCell(j);
//                    switch (cell.getCellType()){
//                        case STRING:
//                            String cellValue = cell.getStringCellValue();
//                            switch (j){
//                                case 1:
//                                    userInfo.setOrdernum(cellValue);
//                                    break;
//                                case 2:
//                                    userInfo.setVipname(cellValue);
//                                    break;
//                                case 4:
//                                    userInfo.setName(cellValue);
//                                    break;
//                                case 5:
//                                    userInfo.setProvince(cellValue);
//                                    break;
//                                case 6:
//                                    userInfo.setCity(cellValue);
//                                    break;
//                                case 7:
//                                    userInfo.setArea(cellValue);
//                                    break;
//                                case 8:
//                                    userInfo.setAddress(cellValue);
//                                    break;
//                                case 9:
//                                    userInfo.setTel(cellValue);
//                                    break;
//                                case 10:
//                                    userInfo.setProductname(cellValue);
//                                    break;
//                                case 11:
//                                    userInfo.setRemarks(cellValue);
//                                    break;
//                            }
//                            break;
//                        case NUMERIC:
//                            Double cellvalue = cell.getNumericCellValue();
//
////                            int b = cellvalue.intValue();
//                            if (j == 3) {
////                                userInfo.setPaymoney(Integer.valueOf(b));
//                                DecimalFormat df = new DecimalFormat("#.##");
//                                String a  = df.format(cellvalue);
//                                userInfo.setPaymoney(Float.parseFloat(a));
//
//                            }
//
//
//                            break;
//                        case BOOLEAN:
//                            break;
//                        case ERROR:
//                            break;
//                    }
//                }
//
//                list.add(userInfo);
//            }
//            log.info("导入文件解析成功！");
//            System.out.println(list);
//            return list;
//        } catch (Exception e) {
//            log.info("导入文件解析失败！");
//            e.printStackTrace();
//        }
//        return null;
//    }


