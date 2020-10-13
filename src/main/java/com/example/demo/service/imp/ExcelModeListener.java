package com.example.demo.service.imp;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.demo.dao.OperateExcelMapper;
import com.example.demo.pojo.ExcelMode;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ExcelModeListener extends AnalysisEventListener<ExcelMode> {
    @Autowired
    private OperateExcelMapper mapper;
    private static final int BATCH_COUNT = 3000;
    List<ExcelMode> list = new ArrayList<>();

    public ExcelModeListener(OperateExcelMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public void invoke(ExcelMode mode, AnalysisContext context) {
        list.add(mode);//将一个对象添加进list
        // 达到BATCH_COUNT了，需要去存储一次数据库
        if(list.size() >= BATCH_COUNT){
            saveData();
            //存储完成清理list
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
    }

    private void saveData() {
        mapper.excelToTable(list);
    }
}
