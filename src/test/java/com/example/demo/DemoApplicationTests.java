package com.example.demo;

import com.example.demo.service.OperateExcel;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(value={"com.example.demo.dao"})
class DemoApplicationTests {
    @Autowired
    private OperateExcel operate;
    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    void contextLoads() {
        long time1 = System.currentTimeMillis();
        operate.excelToTable();
        operate.tableToExcel();
        long time2 = System.currentTimeMillis();
        System.out.println(time2-time1);
    }


}
