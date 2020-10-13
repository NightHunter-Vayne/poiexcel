package com.example.demo.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

@Data
public class ExcelMode extends BaseRowModel {
    @ExcelProperty(value="凭证编号", index=0)
    private String proofNum;
    @ExcelProperty(value = "年度/月份",index = 1)
    private String period;
    @ExcelProperty(value = "总帐金额", index = 2)
    private String smoney;
    @ExcelProperty(value = "功能范围", index = 3)
    private String funRange;
    @ExcelProperty(value = "科目匹配", index = 4)
    private String subjectMatch;
    @ExcelProperty(value = "费用分类", index = 5)
    private String expenseType;
    @ExcelProperty(value = "成本中心按研发号调整", index = 6)
    private String costCenter;
    @ExcelProperty(value = "总帐金额", index = 7)
    private String sumMoney;
    @ExcelProperty(value = "年份", index = 8)
    private String years;
    @ExcelProperty(value = "是否电站", index = 9)
    private String isPowerStation;
    @ExcelProperty(value = "事业部/中心", index = 10)
    private String businessUnit;
    @ExcelProperty(value = "简化成本中心", index = 11)
    private String simplifyCostCenter;
    @ExcelProperty(value = "费用类型", index = 12)
    private String outlayType;
}
