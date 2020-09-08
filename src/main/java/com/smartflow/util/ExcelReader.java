package com.smartflow.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.synth.SynthSpinnerUI;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.stereotype.Component;

@Component
public class ExcelReader {

	
	private POIFSFileSystem fs;
    private HSSFWorkbook wb;
    private HSSFSheet sheet;
    private HSSFRow row;
   

    private String fileFullPath;
    
    private int sheetNo;
    
    
    public ExcelReader(){
    	
    }


    /**
     * 读取Excel数据内容
     * @param InputStream 
     * @param sheetNo sheet 页号
     * @return Map 包含单元格数据内容的Map对象
     */
    public List<Map<String,Object>> readExcel(String fileFullPath,int sheetNo) {
      
        sheetNo--;//从1开始及从0开始
        InputStream is = null;
        try {
            is = new FileInputStream(fileFullPath);
        } catch (FileNotFoundException e1) {
            
        }
        Map<String,Object> dataMap = null;
        List<Map<String,Object>> dataList= new ArrayList<>();
        String value = "";
         try {
             fs = new POIFSFileSystem(is);
             wb = new HSSFWorkbook(fs);
         } catch (IOException e) {
            
         }
         sheet = wb.getSheetAt(sheetNo);
         row = sheet.getRow(0);
         // 标题总列数
         int colNum = row.getPhysicalNumberOfCells();
         String[] keyArray = new String[colNum];
         for (int i = 0; i < colNum; i++) {
             keyArray[i] = getCellFormatValue(row.getCell(i));
         }
        int rowNum = sheet.getLastRowNum();
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            dataMap= new HashMap<>();
            row = sheet.getRow(i);
            if(row!=null){
                int j = 0;
                while (j < colNum) {
                    //这里把列循环到Map
                    if(row.getCell(j)!=null){
                    value = getCellFormatValue(row.getCell(j)).trim();
                    dataMap.put(keyArray[j],value);
                    }
                    j++;
                }
                value = "";
                dataList.add(dataMap);
            }
        }
     
        try {
            if(is!=null)
            is.close();
        } catch (IOException e) {
           
        }
        return dataList;
    }

    /**
     * 根据HSSFCell类型设置数据
     * @param cell
     * @return
     */
    private String getCellFormatValue(HSSFCell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
            // 如果当前Cell的Type为NUMERIC
            case HSSFCell.CELL_TYPE_NUMERIC:
            case HSSFCell.CELL_TYPE_FORMULA: {
                // 判断当前的cell是否为Date
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellvalue = sdf.format(date);
                }
                // 如果是纯数字
                else {
                    // 取得当前Cell的数值
                    DecimalFormat df = new DecimalFormat("0");  
                    String dfStr = df.format(cell.getNumericCellValue()); 
                    cellvalue = dfStr;
                }
                break;
            }
            // 如果当前Cell的Type为STRIN
            case HSSFCell.CELL_TYPE_STRING:
                // 取得当前的Cell字符串
                cellvalue = cell.getRichStringCellValue().getString();
                break;
            // 默认的Cell值
            default:
                cellvalue = " ";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;

    }

    public static void main(String[] args) {
            List<Map<String, Object>> dataList;
            // 对读取Excel表格标题测试
            ExcelReader excelReader = new ExcelReader();
            dataList = excelReader.readExcel("F:\\excel\\选择题_批量导入模板.xls",1);
            for(Map<String,Object> theMap:dataList){
                System.out.println(theMap);
            }
    	
    }


}
