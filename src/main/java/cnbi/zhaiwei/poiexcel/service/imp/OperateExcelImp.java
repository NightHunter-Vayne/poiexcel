package cnbi.zhaiwei.poiexcel.service.imp;


import cnbi.zhaiwei.poiexcel.Utils.ExcelUtils;
import cnbi.zhaiwei.poiexcel.Utils.LargeExcelFileReadUtil;
import cnbi.zhaiwei.poiexcel.dao.ExcelTableMapper;
import cnbi.zhaiwei.poiexcel.service.OperateExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName OperateExcelImp
 * @Description
 * @Author cnbi翟伟
 * @Date 2020/9/27 11:31
 **/

@Service
public class OperateExcelImp implements OperateExcel {
    //一次最多插入5000条记录 time = 2983013ms
    //一次最多插入3000条记录 time = 916413
    //一次最多插入1000条记录 time = 256132
    private final Integer BATCH_COUNT = 1000;
    @Autowired
    private ExcelTableMapper mapper;
    @Autowired
    private LargeExcelFileReadUtil readUtil;

    @Override
    public void DataAccess2Table(String fileName) throws Exception {
        LinkedHashMap<String, String> originalMap = readDataFromExcel(fileName);
        //获取原Excel文件的行数
        int rowCount = ExcelUtils.getrowCount(originalMap);
        int restRowNum = rowCount;

        while(restRowNum > 0){
            List<LinkedHashMap> recordList = ExcelUtils.mapTransform2List
                    (originalMap, restRowNum>BATCH_COUNT ? BATCH_COUNT : restRowNum);
            mapper.insertData2Table(recordList);
            restRowNum = restRowNum - BATCH_COUNT;
        }
    }

    private LinkedHashMap readDataFromExcel(String fileName) throws Exception {
        readUtil.processOneSheet(fileName);
        LinkedHashMap<String, String> originalMap = readUtil.getRowContents();

        return originalMap;
    }

    @Override
    public void DataAccess2Workbook(String fileName) throws Exception {

    }
}