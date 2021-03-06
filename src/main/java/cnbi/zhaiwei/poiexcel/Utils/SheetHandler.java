package cnbi.zhaiwei.poiexcel.Utils;

/**
 SheetHandler  类中处理从excle获取的数据，官方文档中 SheetHandler以内部类形式，为保证更新代码减少内部类class文件忘记打包，改为一般java类
 */
import java.util.LinkedHashMap;

import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SheetHandler extends DefaultHandler{
    /**
     * SharedStrings:在Excel文件底层专门存储每个单元格中值的XML文件
     */
    private SharedStringsTable sst;
    /**
     * lastContents:上一个单元格的值
     */
    private String lastContents;
    /**
     * 下个单元格的值是否为String类型
     */
    private boolean nextIsString;
    /**
     * 单元格的坐标，比如 A1
     */
    private String  cellPosition;
    private LinkedHashMap<String, String> rowContents = new LinkedHashMap();

    public LinkedHashMap<String, String> getRowContents() {
        return rowContents;
    }

    public void setRowContents(LinkedHashMap<String, String> rowContents) {
        this.rowContents = rowContents;
    }

    public SheetHandler(SharedStringsTable sst) {
        this.sst = sst;
    }

    @Override
    public void startElement(String uri, String localName, String name,
                             Attributes attributes) throws SAXException {
        if(name.equals("c")) {
            cellPosition = attributes.getValue("r");
            String cellType = attributes.getValue("t");
            if(cellType != null && cellType.equals("s")) {
                nextIsString = true;
            } else {
                nextIsString = false;
            }
        }
        // 清除缓存内容
        lastContents = "";
    }

    @Override
    public void endElement(String uri, String localName, String name)
            throws SAXException {
        if(nextIsString) {
            int idx = Integer.parseInt(lastContents);
            lastContents = new XSSFRichTextString(sst.getEntryAt(idx)).toString();
            nextIsString = false;
        }

        if(name.equals("v")) {
            //数据读取结束后，将单元格坐标,内容存入map中
            //不保存第一行数据
            if(!(cellPosition.length()==2)||(cellPosition.length()==2&&!"1".equals(cellPosition.substring(1)))){
                rowContents.put(cellPosition, lastContents);
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        lastContents += new String(ch, start, length);
    }
}