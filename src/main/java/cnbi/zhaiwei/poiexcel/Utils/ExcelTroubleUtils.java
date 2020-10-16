package cnbi.zhaiwei.poiexcel.Utils;


import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName ExcelTroubleUtils
 * @Description
 * @Author cnbi翟伟
 * @Date 2020/10/12 10:05
 **/

public class ExcelTroubleUtils {
    /**
     * 用SAX获取到的单元格，把每一行缺失的单元格补上0
     * @param j Excel中某一行的第j列
     * @param colName 所有列名组成的数组
     * @param currEntry 当前的Entry
     * @param oneRow 存储Excel中一行的LinkedHashMap集合
     * @return 当前列的下标
     */
    public static int solveCellValNull(int j,
                                       String[] colName,
                                       Map.Entry<String, String> currEntry,
                                       LinkedHashMap<String, String> oneRow){
        String[] colIndex = {"A","B","C","D","E","F","G","H","I","J","K","L","M"};
        if(!Objects.equals(currEntry.getKey().substring(0,1),colIndex[j])){
            oneRow.put(colName[j], "0");
            solveCellValNull(++j, colName, currEntry, oneRow);
            return j;
        }else{
            if(Objects.equals(currEntry.getKey().substring(0,1), "H")){
                oneRow.put(colName[j], convertNumType(currEntry));
            }else{
                oneRow.put(colName[j], currEntry.getValue());
            }

            return j;
        }
    }

    /**
     * 去除掉科学计数法中的E
     * @param currEntry
     * @return
     */
    private static String convertNumType(Map.Entry<String, String> currEntry){
        String value = new BigDecimal(currEntry.getValue()).stripTrailingZeros().toPlainString();
        return value;
    }
}