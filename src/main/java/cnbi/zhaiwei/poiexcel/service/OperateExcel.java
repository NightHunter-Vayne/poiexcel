package cnbi.zhaiwei.poiexcel.service;

public interface OperateExcel {
    void DataAccess2Table(String fileName) throws Exception;

    void DataAccess2Workbook(String fileName) throws Exception;
}
