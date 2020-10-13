package com.example.demo.dao;

import com.example.demo.pojo.ExcelMode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OperateExcelMapper {
    void excelToTable(List<ExcelMode> list);

    List<Map> tableToExcel();
}
