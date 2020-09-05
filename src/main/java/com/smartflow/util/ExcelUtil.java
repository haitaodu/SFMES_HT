package com.smartflow.util;

import com.smartflow.view.Process.ProcessItemEditeView;
import org.apache.log4j.Logger;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDataValidationConstraint;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil {

    private static Logger log = Logger.getLogger(ExcelUtil.class);
    /**
     * 生成Excel导入模板
     * @Title: createExcelTemplate
     * @Description: 生成Excel导入模板
     * @param @param filePath  Excel文件路径
     * @param @param handers   Excel列标题(数组)
     * @param @param downData  下拉框数据(数组)
     * @param @param downRows  下拉列的序号(数组,序号从0开始)
     * @return void
     * @throws
     */
    public static void createExcelTemplate(String filePath, String[] headers,
                                           List<String[]> downData, String[] downRows, List<Map<String, String>> dataList){

        HSSFWorkbook wb = new HSSFWorkbook();//创建工作薄

        //表头样式
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        //字体样式
        HSSFFont fontStyle = wb.createFont();
        fontStyle.setFontName("微软雅黑");
        fontStyle.setFontHeightInPoints((short)12);
        fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(fontStyle);

        //新建sheet
        HSSFSheet sheet1 = wb.createSheet("Sheet1");
        HSSFSheet sheet2 = wb.createSheet("Sheet2");
        HSSFSheet sheet3 = wb.createSheet("Sheet3");

        //生成sheet1内容
        HSSFRow rowFirst = sheet1.createRow(0);//第一个sheet的第一行为标题
        //写标题
        for(int i=0;i<headers.length;i++){
            HSSFCell cell = rowFirst.createCell(i); //获取第一行的每个单元格
            sheet1.setColumnWidth(i, 4000); //设置每列的列宽
            cell.setCellStyle(style); //加样式
            cell.setCellValue(headers[i]); //往单元格里写数据
//            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        }

        //设置下拉框数据
        String[] arr = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        int index = 0;
        HSSFRow row = null;
        for(int r=0;r<downRows.length;r++){
            String[] dlData = downData.get(r);//获取下拉对象
            int rownum = Integer.parseInt(downRows[r]);

            if(dlData.length < 5){ //255以内的下拉
//                wb.setSheetHidden(1, true);//1隐藏、0显示
                //255以内的下拉,参数分别是：作用的sheet、下拉内容数组、起始行、终止行、起始列、终止列
                sheet1.addValidationData(setDataValidation(sheet1, dlData, 1, 50000, rownum ,rownum)); //超过255个报错
            } else { //255以上的下拉，即下拉列表元素很多的情况

                //1、设置有效性
                //String strFormula = "Sheet2!$A$1:$A$5000" ; //Sheet2第A1到A5000作为下拉列表来源数据
                String strFormula = "Sheet2!$"+arr[index]+"$1:$"+arr[index]+"$5000"; //Sheet2第A1到A5000作为下拉列表来源数据
                sheet2.setColumnWidth(r, 4000); //设置每列的列宽
                //设置数据有效性加载在哪个单元格上,参数分别是：从sheet2获取A1到A5000作为一个下拉的数据、起始行、终止行、起始列、终止列
                sheet1.addValidationData(SetDataValidation(strFormula, 1, 50000, rownum, rownum)); //下拉列表元素很多的情况

                //2、生成sheet2内容
                for(int j=0;j<dlData.length;j++){
                    if(index==0){ //第1个下拉选项，直接创建行、列
                        row = sheet2.createRow(j); //创建数据行
                        sheet2.setColumnWidth(j, 4000); //设置每列的列宽
                        row.createCell(0).setCellValue(dlData[j]); //设置对应单元格的值

                    } else { //非第1个下拉选项

                        int rowCount = sheet2.getLastRowNum();
                        //System.out.println("========== LastRowNum =========" + rowCount);
                        if(j<=rowCount){ //前面创建过的行，直接获取行，创建列
                            //获取行，创建列
                            sheet2.getRow(j).createCell(index).setCellValue(dlData[j]); //设置对应单元格的值

                        } else { //未创建过的行，直接创建行、创建列
                            sheet2.setColumnWidth(j, 4000); //设置每列的列宽
                            //创建行、创建列
                            sheet2.createRow(j).createCell(index).setCellValue(dlData[j]); //设置对应单元格的值
                        }
                    }
                }
                index++;
            }
        }

        //数据导入单元格
        if(!CollectionUtils.isEmpty(dataList)) {
            HSSFRow dataRow;
            HSSFCell cell;
            int i = 1;
            for (Map<String, String> map : dataList) {
                dataRow = sheet1.createRow(i);
                cell = dataRow.createCell(0);
//                cell.setCellStyle(style);
                cell.setCellValue(map.get("StationGroupId"));
                cell = dataRow.createCell(1);
//                cell.setCellStyle(style);
                cell.setCellValue(map.get("Description"));
                cell = dataRow.createCell(2);
//                cell.setCellStyle(style);
                cell.setCellValue(map.get("IsNeedSetupCheck"));
                i++;
            }
        }

        try {

            File f = new File(filePath); //写文件

            //不存在则新增
            if(!f.getParentFile().exists()){
                f.getParentFile().mkdirs();
            }
            if(!f.exists()){
                f.createNewFile();
            }

            FileOutputStream out = new FileOutputStream(f);
            out.flush();
            wb.write(out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * @Title: SetDataValidation
     * @Description: 下拉列表元素很多的情况 (255以上的下拉)
     * @param @param strFormula
     * @param @param firstRow   起始行
     * @param @param endRow     终止行
     * @param @param firstCol   起始列
     * @param @param endCol     终止列
     * @param @return
     * @return HSSFDataValidation
     * 注意：如果是一个单元格，需要firstRow = endRow, firstCol = endCol
     * @throws
     */
    private static HSSFDataValidation SetDataValidation(String strFormula,
                                                        int firstRow, int endRow, int firstCol, int endCol) {

        // 设置数据有效性加载在哪个单元格上。四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        DVConstraint constraint = DVConstraint.createFormulaListConstraint(strFormula);
        HSSFDataValidation dataValidation = new HSSFDataValidation(regions,constraint);

        dataValidation.createErrorBox("Error", "Error");
        dataValidation.createPromptBox("", null);

        return dataValidation;
    }

    /**
     * 添加数据有效性检查.
     * @param sheet 要添加此检查的Sheet
     * @param firstRow 开始行
     * @param lastRow 结束行
     * @param firstCol 开始列
     * @param lastCol 结束列
     * @param explicitListValues 有效性检查的下拉列表
     * @throws IllegalArgumentException 如果传入的行或者列小于0(< 0)或者结束行/列比开始行/列小
     * 注意： 如果是一个单元格，需要 firstRow = lastRow , firstCol= lastCol
     */
    private static void setValidationData(Sheet sheet, int firstRow, int lastRow,
                                          int firstCol, int lastCol, String[] explicitListValues) throws IllegalArgumentException{
        if (firstRow < 0 || lastRow < 0 || firstCol < 0 || lastCol < 0 || lastRow < firstRow || lastCol < firstCol) {
            throw new IllegalArgumentException("Wrong Row or Column index : " + firstRow+":"+lastRow+":"+firstCol+":" +lastCol);
        }
        if (sheet instanceof XSSFSheet) {
            XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper((XSSFSheet)sheet);
            XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper
                    .createExplicitListConstraint(explicitListValues);
            CellRangeAddressList addressList = new CellRangeAddressList(firstRow, lastRow, 1, 2);
            XSSFDataValidation validation = (XSSFDataValidation) dvHelper.createValidation(dvConstraint, addressList);
            validation.setSuppressDropDownArrow(true);
            validation.setShowErrorBox(true);
            sheet.addValidationData(validation);
        } else if(sheet instanceof HSSFSheet){
            CellRangeAddressList addressList = new CellRangeAddressList(firstRow, lastRow, firstCol, lastCol);
            DVConstraint dvConstraint = DVConstraint.createExplicitListConstraint(explicitListValues);
            DataValidation validation = new HSSFDataValidation(addressList, dvConstraint);
            validation.setSuppressDropDownArrow(true);
            validation.setShowErrorBox(true);
            sheet.addValidationData(validation);
        }
    }


    /**
     *  设置有效性
     * @Title: setDataValidation
     * @Description: 下拉列表元素不多的情况(255以内的下拉)
     * @param @param sheet
     * @param @param textList
     * @param @param firstRow
     * @param @param endRow
     * @param @param firstCol
     * @param @param endCol
     * @param @return
     * @return DataValidation
     * @throws
     */
    private static DataValidation setDataValidation(Sheet sheet, String[] textList, int firstRow, int endRow, int firstCol, int endCol) {

        DataValidationHelper helper = sheet.getDataValidationHelper();
        //加载下拉列表内容
        DataValidationConstraint constraint = helper.createExplicitListConstraint(textList);
        //DVConstraint constraint = new DVConstraint();
        constraint.setExplicitListValues(textList);
        //设置数据有效性加载在哪个单元格上。四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);

        //数据有效性对象
        DataValidation data_validation = helper.createValidation(constraint, regions);
        //DataValidation data_validation = new DataValidation(regions, constraint);

        return data_validation;
    }


    /**
     * @Title: getExcel
     * @Description: 下载指定路径的Excel文件
     * @param @param url 文件路径
     * @param @param fileName  文件名
     * @param @param response
     * @return void
     * @throws
     */
    public static void getExcel(String url, String fileName, HttpServletResponse response, HttpServletRequest request){

        try {

            //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
            response.setContentType("multipart/form-data");

            //2.设置文件头：最后一个参数是设置下载文件名
            response.setHeader("Content-disposition", "attachment; filename=\""
                    + encodeChineseDownloadFileName(request, fileName+".xls") +"\"");
//            response.setHeader("Content-Disposition", "attachment;filename="
//                    + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + ".xls"); //中文文件名

            //通过文件路径获得File对象
            File file = new File(url);

            FileInputStream in = new FileInputStream(file);
            //3.通过response获取OutputStream对象(out)
            OutputStream out = new BufferedOutputStream(response.getOutputStream());

            int b = 0;
            byte[] buffer = new byte[2048];
            while ((b=in.read(buffer)) != -1){
                out.write(buffer,0,b); //4.写到输出流(out)中
            }

            in.close();
            out.flush();
            out.close();

        } catch (IOException e) {
            log.error("下载Excel模板异常", e);
        }
    }

    /**
     * 下载指定路径的Excel文件
     * @Title: encodeChineseDownloadFileName
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param request
     * @param @param pFileName
     * @param @return
     * @param @throws UnsupportedEncodingException
     * @return String
     * @throws
     */
    private static String encodeChineseDownloadFileName(HttpServletRequest request, String pFileName)
            throws UnsupportedEncodingException {

        String filename = null;
        String agent = request.getHeader("USER-AGENT");
        //System.out.println("agent==========》"+agent);

        if (null != agent) {
            if (-1 != agent.indexOf("Firefox")) {//Firefox
                filename = "=?UTF-8?B?" + (new String(org.apache.commons.codec.binary.Base64.encodeBase64(pFileName.getBytes("UTF-8")))) + "?=";
            } else if (-1 != agent.indexOf("Chrome")) {//Chrome
                filename = new String(pFileName.getBytes(), "ISO8859-1");
            } else {//IE7+
                filename = java.net.URLEncoder.encode(pFileName, "UTF-8");
                filename = StringUtils.replace(filename, "+", "%20");//替换空格
            }
        } else {
            filename = pFileName;
        }

        return filename;
    }

    /**
     * 删除生成的临时文件
     * @Title: delFile
     * @Description: 删除文件
     * @param @param filePath  文件路径
     * @return void
     * @throws
     */
    public static void delFile(String filePath) {
        java.io.File delFile = new java.io.File(filePath);
        delFile.delete();
    }

    public static void main(String[] args) {
        String fileName = "员工信息表"; //模板名称
        String[] handers = {"姓名","性别","证件类型","证件号码","服务结束时间","参保地","民族"}; //列标题

        //下拉框数据
        List<String[]> downData = new ArrayList();
        String[] str1 = {"男","女","未知"};
        String[] str2 = {"北京","上海","广州","深圳","武汉","长沙","湘潭"};
        String[] str3 = {"01-汉族","02-蒙古族","03-回族","04-藏族","05-维吾尔族","06-苗族","07-彝族","08-壮族","09-布依族","10-朝鲜族","11-满族","12-侗族","13-瑶族","14-白族","15-土家族","16-哈尼族","17-哈萨克族","18-傣族","19-黎族","20-傈僳族","21-佤族","22-畲族","23-高山族","24-拉祜族","25-水族","26-东乡族","27-纳西族","28-景颇族","29-柯尔克孜族","30-土族","31-达斡尔族","32-仫佬族","33-羌族","34-布朗族","35-撒拉族","36-毛难族","37-仡佬族","38-锡伯族","39-阿昌族","40-普米族","41-塔吉克族","42-怒族","43-乌孜别克族","44-俄罗斯族","45-鄂温克族","46-德昂族","47-保安族","48-裕固族","49-京族","50-塔塔尔族","51-独龙族","52-鄂伦春族","53-赫哲族","54-门巴族","55-珞巴族","56-基诺族","98-外国血统","99-其他"};
        downData.add(str1);
        downData.add(str2);
        downData.add(str3);
        String[] downRows = {"1","5","6"}; //下拉的列序号数组(序号从0开始)

        try {

            ExcelUtil.createExcelTemplate("C:\\Users\\smartflow\\Desktop\\"+fileName+".xls", handers, downData, downRows, null);//, request, response

        } catch (Exception e) {
            log.error("批量导入信息异常：" + e.getMessage());
        }
    }

    public static List<Map> toSingle(List<Map<String, Object>> mlist){
        List<Map> singleList = new ArrayList<Map>();
        Map map = null;
        for(Map m : mlist){
            map = new HashMap();
            map.put("StationGroupId", m.get("工站组"));
            map.put("Description", m.get("描述"));
            ;
            if(m.get("是否需要上料检验").equals("是")){
                map.put("IsNeedSetupCheck", true);
            }
            if(m.get("是否需要上料检验").equals("否")){
                map.put("IsNeedSetupCheck", false);
            }
            singleList.add(map);
        }
        return singleList;
    }
}
