package cnbi.zhaiwei.poiexcel.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author cnbi 翟伟
 */
@Mapper
public interface ExcelTableMapper {
    void insertData2Table(@Param("list") List<LinkedHashMap> recordList);

    List<LinkedHashMap> queryDatasFromTable(@Param("tabName") String tableName);
}
