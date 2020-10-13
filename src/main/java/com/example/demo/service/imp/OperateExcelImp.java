package com.example.demo.service.imp;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.example.demo.dao.OperateExcelMapper;
import com.example.demo.pojo.ExcelMode;
import com.example.demo.service.OperateExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OperateExcelImp implements OperateExcel {
    @Autowired
    private OperateExcelMapper mapper;

    @Override
    public void excelToTable() {
        String fileName = "D:\\Documents\\work\\zhaiwei.xlsx";
        ExcelModeListener listener =  new ExcelModeListener(mapper);
        EasyExcel.read(fileName, ExcelMode.class, listener).doReadAll();
    }

    @Override
    public void tableToExcel() {
        String fileName = "D:\\Documents\\work\\zhaiwei.xlsx";
        String templateFile = "D:\\Documents\\work\\yzz.xlsx";
        List<Map> costList = mapper.tableToExcel();
        List<Map> list1 = new ArrayList<>();
        List<Map> list2 = new ArrayList<>();

        for (int i = 0; i < 1000000; i++) {
            list1.add(costList.get(i));
        }

        for (int i = 1000000; i < costList.size(); i++) {
            list2.add(costList.get(i));
        }

        ExcelWriter writer = EasyExcel.write(fileName).withTemplate(templateFile).build();
        WriteSheet sheet1 = EasyExcel.writerSheet("总表1(含电站)").build();
        WriteSheet sheet2 = EasyExcel.writerSheet("总表2(含电站)").build();

        writer.fill(list1, sheet1);
        writer.fill(list2, sheet2);

        writer.finish();
    }
}
