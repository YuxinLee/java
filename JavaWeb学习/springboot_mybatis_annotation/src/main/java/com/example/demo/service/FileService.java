package com.example.demo.service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    /**
     * 判断文件格式
     * 1.对于excel的xls格式，使用HSSFWorkbook，HSSFSheet，HSSFRow来进行相关操作
     * 2.对于excel的xlsx格式，使用XSSFWorkbook，XSSFSheet，XSSFRow来进行相关操作
     * 3.其他格式直接报错
     */
    public Workbook getWorkbook(InputStream inputStream, String fileName) throws Exception {
        Workbook workbook = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (".xls".equals(fileType)) {
            workbook = new HSSFWorkbook(inputStream);
        } else if (".xlsx".equals(fileType)) {
            workbook = new XSSFWorkbook(inputStream);
        } else {
            throw new Exception("请上传excel文件！");
        }
        return workbook;
    }

    /**
     * 处理上传的文件
     */
    public List getBankListByExcel(InputStream inputStream, String fileName) throws Exception {
        List list = new ArrayList<>();

        //创建Excel工作薄
        Workbook work = this.getWorkbook(inputStream, fileName);

        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }

        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }

            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }

                List<Object> li = new ArrayList<>();
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    li.add(cell);
                }
                list.add(li);
            }
        }

        work.close();
        return list;
    }

}
