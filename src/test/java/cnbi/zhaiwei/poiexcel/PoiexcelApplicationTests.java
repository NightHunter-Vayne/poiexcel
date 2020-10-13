package cnbi.zhaiwei.poiexcel;

import cnbi.zhaiwei.poiexcel.service.OperateExcel;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(value={"cnbi.zhaiwei.poiexcel.dao"})
class PoiexcelApplicationTests {
    @Autowired
    private OperateExcel operate;

    @Test
    void contextLoads() throws Exception {
        Long currTime1 = System.currentTimeMillis();
        operate.DataAccess2Table("D:\\Documents\\work\\费用明细测试.xlsx");
        Long currTime2 = System.currentTimeMillis();
        System.out.println(currTime2-currTime1);
    }
}
