package cnbi.zhaiwei.poiexcel.Utils;

import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @ClassName ExcelUtils
 * @Description
 * @Author cnbi翟伟
 * @Date 2020/9/27 12:35
 **/

public final class ExcelUtils {
    /**
     * 将map集合转换成List嵌套List的集合
     * @param originalMap 原来的map集合
     * @return 转换后的List<List>集合
     */
    public static List<LinkedHashMap> mapTransform2List(LinkedHashMap<String, String> originalMap, Integer batchCount, Integer colNum){
        if(CollectionUtils.isEmpty(originalMap)){
            return null;
        }

        String[] colName = {"proofNum","period","sMoney","funRange","subjectMatch","expenseType","costCenter",
                "s_Money","years","isPowerStation","businessUnit","simplifyCostCenter","expense_Type"};
        Iterator<Map.Entry<String, String>> iterator = originalMap.entrySet().iterator();

        List<LinkedHashMap> outerList = new ArrayList(batchCount);

        String preRowIndex = "";

        for(int i = 0; i < batchCount; ++i){
            LinkedHashMap<String, String> oneRow = new LinkedHashMap(colNum);

            for (int j = 0; j < colNum; j++) {
                if(iterator.hasNext()){
                    Map.Entry<String, String> currEntry = (Map.Entry<String, String>)iterator.next();
                    String rowIndex = currEntry.getKey().substring(1);

                    if(!Objects.equals(preRowIndex, rowIndex)){
                        preRowIndex = rowIndex;
                        oneRow.put(colName[0], currEntry.getValue());
                    }else{
                        j = ExcelTroubleUtils.solveCellValNull(j, colName, currEntry, oneRow);
                    }
                    //成功转换了一个map集合中的元素，就将其删除
                    iterator.remove();
                }
            }
            outerList.add(oneRow);
        }

        return outerList;
    }

    //获取原Excel的列数
//    private static int getColCount(LinkedHashMap<String, String> originalMap){
//        Iterator<Map.Entry<String, String>> iterator = originalMap.entrySet().iterator();
//        List<Character> colList = new ArrayList();
//
//        while(iterator.hasNext()){
//            char col = iterator.next().getKey().charAt(0);
//            if(!colList.contains(col)){
//                colList.add(col);
//            }else{
//                break;
//            }
//        }
//
//        return colList.size();
//    }

    //获取原Excel的行数
    public static int getrowCount(int colNum, LinkedHashMap<String, String> originalMap){
        int rowCount = originalMap.size()/colNum;
        return rowCount;
    }

    @Test
    public void test() throws Exception {
        LargeExcelFileReadUtil example = new LargeExcelFileReadUtil();
        example.processOneSheet("D:\\Documents\\work\\费用明细测试.xlsx");
        LinkedHashMap<String, String> map = example.getRowContents();
        List<LinkedHashMap> list = mapTransform2List(map, 3000, 13);
        int count = 0;
        for (LinkedHashMap linkedHashMap : list) {
            System.out.println(linkedHashMap.get("s_Money"));
        }
    }
}