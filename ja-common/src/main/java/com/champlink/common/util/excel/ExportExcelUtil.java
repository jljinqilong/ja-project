package com.champlink.common.util.excel;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.util.CellRangeAddress;
import com.champlink.common.util.RequestUtil;
import com.champlink.common.util.exception.AppException;

/**
 * Excel导出
 * 
 * @author natyu
 * @date 2018年7月9日 下午6:54:43
 *
 */
public final class ExportExcelUtil {

    /**
     * Excel导出
     * 
     * @author natyu
     * @date 2018年7月9日 下午6:54:51
     * @param response
     * @param dataList
     * @param title
     * @param headers
     * @param fields
     * @param renders
     */
    public static void exportExcel(HttpServletResponse response, List<?> dataList, String title, String[] headers, String[] fields, String[] renders) {

        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);
        // 生成一个标题样式
        HSSFCellStyle style = createTitleStyle(workbook);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成一个单元格样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

        //产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //遍历集合数据，产生数据行
        int index = 0; //行号

        for (Object obj : dataList) {
            index++;

            //创建一行
            row = sheet.createRow(index);

            for (int i = 0; i < fields.length; i++) {
                //生成单元格
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(style2);

                //获取值
                Object textValue = getValInPojo(fields[i], obj, renders);
                //把值放入单元格
                cell.setCellValue(new HSSFRichTextString(String.valueOf(textValue)));
            }
        }

        //导出
        export2Http(response, workbook);
    }

    /**
     * 导出excel时，修改自定义渲染器
     * 
     * @author natyu
     * @date 2018年7月9日 下午6:55:02
     * @param response
     * @param dataList
     * @param title
     * @param headers
     * @param fields
     * @param renders
     */
    public static void exportExcelDefine(HttpServletResponse response, List<?> dataList, String title, String[] headers, String[] fields, String[] renders) {

        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);
        // 生成一个标题样式
        HSSFCellStyle style = createTitleStyle(workbook);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成一个单元格样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

        //产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //遍历集合数据，产生数据行
        int index = 0; //行号

        for (Object obj : dataList) {
            index++;

            //创建一行
            row = sheet.createRow(index);

            for (int i = 0; i < fields.length; i++) {
                //生成单元格
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(style2);

                //获取值
                Object textValue = getValInPojoDefine(fields[i], obj, renders);
                //把值放入单元格
                cell.setCellValue(new HSSFRichTextString(String.valueOf(textValue)));
            }
        }

        //导出
        export2Http(response, workbook);
    }

    /**
     * 主细Excel导出
     * 
     * @author natyu
     * @date 2018年7月9日 下午6:55:15
     * @param response
     * @param dataList
     * @param title
     * @param headers
     * @param fields
     * @param fields2
     * @param renders
     */
    public static void exportExcel(HttpServletResponse response, List<?> dataList, String title, String[] headers, String[] fields, String[] fields2, String[] renders) {

        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);
        // 生成一个标题样式
        HSSFCellStyle style = createTitleStyle(workbook);
        // 生成一个单元格样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

        //产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        int index = 0; //行号
        int mainIndex = 0; //主行号  ，无实际意义，用于记录主行的起始行号
        //遍历集合数据，产生数据行
        for (Object obj : dataList) {
            index++;
            mainIndex = index;

            //创建一行
            row = sheet.createRow(index);

            for (int i = 0; i < fields.length; i++) {
                //生成单元格
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(style2);

                //获取值
                Object textValue = getValInPojo(fields[i], obj, renders);

                if (textValue instanceof String) {
                    //把值放入单元格
                    cell.setCellValue(new HSSFRichTextString(String.valueOf(textValue)));
                } else if (textValue instanceof java.util.List) { //处理明细
                    List<Object> dtls = (List<Object>) textValue;
                    for (int k = 0; k < dtls.size(); k++) { //开始遍历明细
                        if (k > 0) { //第2条明细开始新建行
                            index++;
                            row = sheet.createRow(index);
                        }
                        if (k == dtls.size() - 1) { //最后一条明细合并主列单元格
                            //合并单元格
                            for (int n = 0; n < fields.length - 1; n++) {
                                sheet.addMergedRegion(new Region(mainIndex, (short) n, index, (short) n));
                            }
                        }
                        //填充单元格
                        Object dtl = dtls.get(k);
                        for (int j = 0; j < fields2.length; j++) {
                            cell = row.createCell(i + j);
                            cell.setCellStyle(style2);
                            //获取值
                            Object dtlVal = getValInPojo(fields2[j], dtl, renders);
                            //把值放入单元格
                            cell.setCellValue(new HSSFRichTextString(String.valueOf(dtlVal)));
                        }
                    }
                }
            }
        }

        //导出
        export2Http(response, workbook);
    }

    /**
     * 主细Excel导出
     * 
     * @author natyu
     * @date 2018年7月9日 下午6:56:04
     * @param response
     * @param dataList
     * @param title
     * @param headers
     * @param mainFields
     * @param detailFieldsMap
     * @param renders
     */
    public static void exportExcel(HttpServletResponse response, List<?> dataList, String title, String[] headers, String[] mainFields, Map<String, String[]> detailFieldsMap, String[] renders) {

        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);
        // 生成一个标题样式
        HSSFCellStyle style = createTitleStyle(workbook);
        // 生成一个单元格样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

        //产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        int index = 0; //行号
        int mainIndex = 0; //主行号，无实际意义，用于记录主行的起始行号
        Integer[] mainFieldColumnIndex = new Integer[mainFields.length]; //主表列索引
        int currColumnIndex = 0; // 记录当前列索引
        //遍历集合数据，产生数据行
        for (Object obj : dataList) {
            index++;
            mainIndex = index;

            //创建一行
            row = sheet.createRow(index);

            for (int i = 0; i < mainFields.length; i++) {

                //生成单元格
                HSSFCell cell = row.createCell(currColumnIndex);
                cell.setCellStyle(style2);

                //获取值
                Object textValue = getValInPojo(mainFields[i], obj, renders);

                if (textValue instanceof String) {
                    //把值放入单元格
                    cell.setCellValue(new HSSFRichTextString(String.valueOf(textValue)));
                    //记录主表列索引值
                    mainFieldColumnIndex[i] = currColumnIndex;
                } else if (textValue instanceof java.util.List) { //处理明细
                    List<Object> dtls = (List<Object>) textValue;

                    int detailRowIndex = index;
                    HSSFRow detailRow = row;

                    String[] detailFields = detailFieldsMap.get(mainFields[i]);
                    if (detailFields == null)
                        AppException.create(10004, "excel导出异常, " + mainFields[i] + " 不存在对应的明细列");

                    for (int k = 0; k < dtls.size(); k++) { //开始遍历明细
                        if (k > 0) { //第2条明细开始新建行
                            detailRowIndex++;
                            detailRow = sheet.createRow(detailRowIndex);
                        }
                        //                        if (k == dtls.size() - 1) { //最后一条明细合并主列单元格
                        //                            //合并单元格
                        //                            for (int n = 0; n < fields.length - 1; n++) {
                        //                                sheet.addMergedRegion(new Region(mainIndex, (short) n, index, (short) n));
                        //                            }
                        //                        }
                        //填充单元格
                        Object dtl = dtls.get(k);
                        for (int j = 0; j < detailFields.length; j++) {
                            cell = detailRow.createCell(currColumnIndex + j);
                            cell.setCellStyle(style2);
                            //获取值
                            Object dtlVal = getValInPojo(detailFields[j], dtl, renders);
                            //把值放入单元格
                            cell.setCellValue(new HSSFRichTextString(String.valueOf(dtlVal)));

                        }
                    }

                    // 明细处理完之后更新当前列索引
                    currColumnIndex += detailFields.length - 1;

                    //更新当前主表行索引
                    index = detailRowIndex;
                }

                //更新当前列索引
                currColumnIndex++;
            }

            // 在一行数据填充完毕之后合并单元格，这样明细列可以灵活指定位置，也可以有多个明细列
            for (int n = 0; n < mainFields.length; n++) {
                if (mainFieldColumnIndex[n] != null) {
                    sheet.addMergedRegion(new Region(mainIndex, mainFieldColumnIndex[n].shortValue(), index, mainFieldColumnIndex[n].shortValue()));
                }
            }

            // 当前行处理完 清空当前列索引
            currColumnIndex = 0;
        }

        //导出
        export2Http(response, workbook);

    }

    /**
     * 
     * Excel导出（多个sheet页）
     * 
     * @author natyu
     * @date 2016年6月28日 上午11:32:30
     * @param response
     * @param dataList 要导出excel的结果集
     * @param fileName 文件名
     * @param titles 标题
     * @param headers 表头
     * @param fields pojo字段
     * @param renders 自定义渲染
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
    public static void exportExcelMultipleSheet(HttpServletResponse response, List<List<?>> dataList, String fileName, List<String> titles, List<String[]> headers, List<String[]> fields,
                                                List<String[]> renders) {

        if (dataList.size() == titles.size() && titles.size() == headers.size() && headers.size() == fields.size() && renders != null ? fields.size() == renders.size() : true) {
            HSSFWorkbook workbook = new HSSFWorkbook();
            for (int i = 0; i < titles.size(); i++) {
                // 生成一个表格
                HSSFSheet sheet = workbook.createSheet(titles.get(i));
                // 设置表格默认列宽度为15个字节
                sheet.setDefaultColumnWidth((short) 15);
                // 生成一个标题样式
                HSSFCellStyle style = createTitleStyle(workbook);
                style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                // 生成一个单元格样式
                HSSFCellStyle style2 = workbook.createCellStyle();
                style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
                style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
                // 声明一个画图的顶级管理器
                HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

                //产生表格标题行
                HSSFRow row = sheet.createRow(0);
                String[] header = headers.get(i);
                for (int j = 0; j < header.length; j++) {
                    HSSFCell cell = row.createCell(j);
                    cell.setCellStyle(style);
                    HSSFRichTextString text = new HSSFRichTextString(header[j]);
                    cell.setCellValue(text);
                }

                //遍历集合数据，产生数据行
                int index = 0; //行号

                List<?> data = dataList.get(i);
                for (Object obj : data) {
                    index++;

                    //创建一行
                    row = sheet.createRow(index);

                    String[] field = fields.get(i);
                    for (int j = 0; j < field.length; j++) {
                        //生成单元格
                        HSSFCell cell = row.createCell(j);
                        cell.setCellStyle(style2);

                        //获取值
                        Object textValue = new Object();
                        if (renders != null) {
                            textValue = getValInPojo(field[j], obj, renders.get(i));
                        } else {
                            textValue = getValInPojo(field[j], obj, null);
                        }
                        //把值放入单元格
                        cell.setCellValue(new HSSFRichTextString(String.valueOf(textValue)));
                    }
                }
            }

            //导出
            export2HttpMultipleSheet(response, workbook, fileName);
//            export2HttpPath(response,workbook);
        } else {
            //传参数量不一致
            AppException.create(10004, "传参数量不一致");
        }

    }

    /**
     * 
     * Excel导出（多个sheet页）数据已封装
     * 
     * @author natyu
     * @date 2016年6月28日 上午11:32:30
     * @param response
     * @param dataList 要导出excel的结果集
     * @param title 标题
     * @param headers 表头
     * @param fields pojo字段
     * @param renders 自定义渲染
     * @param total 列合计
     * @param number 列合计标题合并单元格数量
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
    public static void exportExcelMultipleSheetData(HttpServletResponse response, String fileName, List<String> titles, List<Object[]> headers, List<List<Object[]>> fields, List<List<Object>> totals,
                                                    int number) {

        if (titles.size() == headers.size() && headers.size() == fields.size() && totals != null ? totals.size() == fields.size() : true) {
            HSSFWorkbook workbook = new HSSFWorkbook();
            for (int i = 0; i < titles.size(); i++) {
                // 生成一个表格
                HSSFSheet sheet = workbook.createSheet(titles.get(i));
                // 设置表格默认列宽度为15个字节
                sheet.setDefaultColumnWidth((short) 15);
                // 生成一个标题样式
                HSSFCellStyle style = createTitleStyle(workbook);
                style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                // 生成一个单元格样式
                HSSFCellStyle style2 = workbook.createCellStyle();
                style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
                style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
                // 声明一个画图的顶级管理器
                HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

                //产生表格标题行
                HSSFRow row = sheet.createRow(0);
                Object[] header = headers.get(i);
                for (int k = 0; k < header.length; k++) {
                    HSSFCell cell = row.createCell(k);
                    cell.setCellStyle(style);
                    HSSFRichTextString text = new HSSFRichTextString(header[k].toString());
                    cell.setCellValue(text);
                }

                //遍历集合数据，产生数据行
                int index = 0; //行号

                for (int j = 0; j < fields.get(i).size(); j++) {
                    index++;

                    //创建一行
                    row = sheet.createRow(index);
                    for (int k = 0; k < fields.get(i).get(j).length; k++) {
                        //生成单元格
                        HSSFCell cell = row.createCell(k);
                        cell.setCellStyle(style2);

                        //获取值
                        Object textValue = fields.get(i).get(j)[k];
                        //                        System.err.println(textValue);
                        //把值放入单元格
                        cell.setCellValue(new HSSFRichTextString(String.valueOf(textValue)));
                    }
                }

                if (totals != null) {
                    index++;

                    //创建一行
                    row = sheet.createRow(index);
                    sheet.addMergedRegion(new Region(index, (short) 0, index, (short) (number - 1)));
                    for (int j = 0; j < totals.get(i).size(); j++) {
                        //生成单元格
                        HSSFCell cell;
                        if (j == 0) {
                            cell = row.createCell(j);
                        } else {
                            cell = row.createCell(j + number - 1);
                        }
                        cell.setCellStyle(style2);

                        //获取值
                        Object textValue = totals.get(i).get(j);
                        //把值放入单元格
                        cell.setCellValue(new HSSFRichTextString(String.valueOf(textValue)));
                    }
                }
            }

            //导出
            export2HttpMultipleSheet(response, workbook, fileName);
        } else {
            //传参数量不一致
            AppException.create(10004, "传参数量不一致");
        }

    }

    public static void main(String[] args) {
        String a = "staffNo";
        System.err.println(a.split("\\.").length > 1);

    }

    /**
     * 从pojo中取字段值
     * 
     * @author natyu
     * @date 2018年7月9日 下午6:56:26
     * @param fieldName
     * @param obj
     * @param renders
     * @return
     */
    public static Object getValInPojo(String fieldName, Object obj, String[] renders) {

        String textValue = null;

        try {
            Object value = null;
            String[] fieldNames = fieldName.split("\\.");
            if (obj instanceof HashMap) { //对象是Map
                value = ((Map) obj).get(fieldName);
            } else if (fieldNames.length > 1) {
                String getMethodName = "get" + fieldNames[0].substring(0, 1).toUpperCase() + fieldNames[0].substring(1);
                Method getMethod = obj.getClass().getMethod(getMethodName, new Class[] {});
                Object mapObj = (Map) getMethod.invoke(obj, new Object[] {});
                if (mapObj != null) {
                    value = ((Map) mapObj).get(fieldNames[1]);
                }
            } else {//对象是JavaBean
                    //利用反射，动态调用pojo中的getXxx()方法得到属性值
                String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                Method getMethod = obj.getClass().getMethod(getMethodName, new Class[] {});
                value = getMethod.invoke(obj, new Object[] {});
            }

            //特殊类型格式化
            if (value instanceof java.sql.Timestamp) { //日期时间 Timestamp需放在Date前
                java.sql.Timestamp time = (java.sql.Timestamp) value;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                textValue = sdf.format(time);
            } else if (value instanceof Date || value instanceof java.sql.Date) { //日期 Date
                Date date = (Date) value;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                textValue = sdf.format(date);
            } else if (value instanceof java.util.List) { //List
                return value;
            } else {//其它数据类型都当作字符串简单处理
                textValue = String.valueOf(value == null ? "" : value);
            }

            if (textValue == null) {
                return null;
            }

            //自定义渲染
            if (renders != null && renders.length != 0) {
                for (String render : renders) {
                    String[] arr1 = render.split(":");
                    if (fieldName.equals(arr1[0])) { //定义了渲染规则
                        String[] arr2 = arr1[1].split(",");
                        for (String item : arr2) {
                            String[] arr3 = item.split("-");
                            if (textValue.equals(arr3[0])) {
                                textValue = arr3[1];
                                break;
                            } else if ("default".equals(arr3[0])) {
                                textValue = arr3[1];
                                break;
                            }
                        }
                    }
                }
            }

        } catch (SecurityException e) {
            e.printStackTrace();
            AppException.create(10004, "生成excel异常");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            AppException.create(10004, "生成excel异常");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            AppException.create(10004, "生成excel异常");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            AppException.create(10004, "生成excel异常");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            AppException.create(10004, "生成excel异常");
        }

        return textValue;

    }

    /**
     * 在定义渲染器时如果字符串出现 - ，导致原方法截取会有问题
     * 
     * @author natyu
     * @date 2018年7月9日 下午6:56:46
     * @param fieldName
     * @param obj
     * @param renders
     * @return
     */
    private static Object getValInPojoDefine(String fieldName, Object obj, String[] renders) {

        String textValue = null;

        try {
            Object value;
            if (obj instanceof HashMap) { //对象是Map
                value = ((Map) obj).get(fieldName);
            } else {//对象是JavaBean
                //利用反射，动态调用pojo中的getXxx()方法得到属性值
                String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                Method getMethod = obj.getClass().getMethod(getMethodName, new Class[] {});
                value = getMethod.invoke(obj, new Object[] {});
            }

            //特殊类型格式化
            if (value instanceof java.sql.Timestamp) { //日期时间 Timestamp需放在Date前
                java.sql.Timestamp time = (java.sql.Timestamp) value;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                textValue = sdf.format(time);
            } else if (value instanceof Date || value instanceof java.sql.Date) { //日期 Date
                Date date = (Date) value;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                textValue = sdf.format(date);
            } else if (value instanceof java.util.List) { //List
                return value;
            } else {//其它数据类型都当作字符串简单处理
                textValue = String.valueOf(value == null ? "" : value);
            }

            if (textValue == null) {
                return null;
            }

            //自定义渲染
            if (renders != null && renders.length != 0) {
                for (String render : renders) {
                    String[] arr1 = render.split(":");
                    if (fieldName.equals(arr1[0])) { //定义了渲染规则
                        textValue = arr1[1];
                    }
                }
            }

        } catch (SecurityException e) {
            e.printStackTrace();
            AppException.create(10004, "生成excel异常");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            AppException.create(10004, "生成excel异常");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            AppException.create(10004, "生成excel异常");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            AppException.create(10004, "生成excel异常");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            AppException.create(10004, "生成excel异常");
        }

        return textValue;

    }

    /**
     * 
     * 导出(多个sheet页)
     * 
     * @author natyu
     * @date 2016年6月29日 下午4:35:49
     * @param response
     * @param workbook
     */
    private static void export2HttpMultipleSheet(HttpServletResponse response, HSSFWorkbook workbook, String fn) {

        String fileName = fn + (new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date()) + ".xls";
        response.setHeader("Content-Type", "application/msexcel");
        try {
            response.setHeader("Content-disposition", "attachment; filename=" + java.net.URLEncoder.encode(fileName, "utf-8"));
        } catch (UnsupportedEncodingException e1) {
            AppException.create(10004, "excel导出异常");
        }
        response.setContentType("application/octet-stream;charset=UTF-8;");

        //导出
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            workbook.write(out);
        } catch (IOException e) {
            AppException.create(10004, "excel导出异常");
        } finally {
            try {
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 导出
     * 
     * @author natyu
     * @date 2018年7月9日 下午6:56:59
     * @param response
     * @param workbook
     */
    public static void export2Http(HttpServletResponse response, HSSFWorkbook workbook) {
        //生成唯一的文件名
        //        String fileName = (new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date());

        String fileName = workbook.getSheetName(0) + (new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date()) + ".xls";
        response.setHeader("Content-Type", "application/msexcel");
        try {
            response.setHeader("Content-disposition", "attachment; filename=" + java.net.URLEncoder.encode(fileName, "utf-8"));
        } catch (UnsupportedEncodingException e1) {
            AppException.create(10004, "excel导出异常");
        }
        response.setContentType("application/octet-stream;charset=UTF-8;");

        //导出
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            workbook.write(out);
        } catch (IOException e) {
            AppException.create(10004, "excel导出异常");
        } finally {
            try {
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 导出到系统指定目录
     *
     * @author natyu
     * @date 2018年7月9日 下午6:56:59
     * @param response
     * @param workbook
     */
    public static void export2HttpPath(HttpServletResponse response, HSSFWorkbook workbook) {
        //生成唯一的文件名
        String fileName = workbook.getSheetName(0) + (new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date()) + ".xls";
        //导出
        FileOutputStream os =null;
        try {
            os = new FileOutputStream("E://"+fileName);
            workbook.write(os);
        } catch (IOException e) {
            AppException.create(10004, "excel导出异常");
        } finally {
            try {
                os.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 生成标题style
     * 
     * @author natyu
     * @date 2018年7月9日 下午6:57:10
     * @param workbook
     * @return
     */
    public static HSSFCellStyle createTitleStyle(HSSFWorkbook workbook) {

        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        // 把字体应用到当前的样式
        style.setFont(font);

        return style;
    }

    /**
     * 生成单元格style
     * 
     * @author natyu
     * @date 2018年7月9日 下午6:57:19
     * @param workbook
     * @return
     */
    private static HSSFCellStyle createCellStyle(HSSFWorkbook workbook) {

        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.WHITE.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        // 生成另一个字体
        HSSFFont font2 = workbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        font2.setColor(HSSFColor.BLACK.index);

        // 把字体应用到当前的样式
        style2.setFont(font2);

        return style2;
    }

    /**
     * 组装excel导出的查询参数
     * 
     * @author natyu
     * @date 2018年7月9日 下午6:57:26
     * @param request
     * @param c
     * @return
     */
    public static Object packageSearchParams(HttpServletRequest request, Class c) {
        Object object = null;
        try {

            //实例化对象
            object = Class.forName(c.getName()).newInstance();

            //填充参数
            Map<String, String> paramMap = RequestUtil.getParameterMap(request);
            for (String fieldName : paramMap.keySet()) {
                String value = paramMap.get(fieldName);
                Field field = getClassField(c, fieldName);
                field.setAccessible(true);
                if (field.getType() == Long.class || field.getType() == long.class) {
                    if (StringUtils.isEmpty(value)) {
                        value = "0";
                    }
                    field.set(object, Long.valueOf(value));
                } else if (field.getType() == Integer.class || field.getType() == int.class) {
                    if (StringUtils.isEmpty(value)) {
                        value = "0";
                    }
                    field.set(object, Integer.valueOf(value));
                } else if (field.getType() == java.sql.Timestamp.class) {
                    if (!StringUtils.isEmpty(value)) {
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        field.set(object, java.sql.Timestamp.valueOf(df.format(java.sql.Date.valueOf(value))));
                    }
                } else if (field.getType() == java.sql.Date.class) {
                    if (!StringUtils.isEmpty(value)) {
                        field.set(object, java.sql.Date.valueOf(value));
                    }
                } else if (field.getType() == Boolean.class || field.getType() == boolean.class) {
                    if (!StringUtils.isEmpty(value)) {
                        field.set(object, Boolean.valueOf(value));
                    }
                } else {
                    field.set(object, value);
                }
            }

            //填充默认值
            Field iDisplayLength = getClassField(c, "iDisplayLength");
            iDisplayLength.setAccessible(true);
            iDisplayLength.set(object, 10);

            Field export = getClassField(c, "export");
            export.setAccessible(true);
            export.set(object, true);
        } catch (Exception e) {
            e.printStackTrace();
            AppException.create(10004, "参数错误");
        }
        return object;
    }

    private static Field getClassField(Class c, String fieldName) {
        Field field = null;
        try {
            field = c.getDeclaredField(fieldName);
        } catch (Exception e) {
            if (c.getGenericSuperclass() != null) {
                field = getClassField(c.getSuperclass(), fieldName);
            } else {
                e.printStackTrace();
                AppException.create(10004, "参数错误");
            }
        }
        return field;

    }

    public static void exportExcel1(HttpServletResponse response, List<?> dataList, String title, String[] headers, String[] fields, String[] renders) throws IOException {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("破片率报表.xls");
        HSSFWorkbook workbook = new HSSFWorkbook(is);
        //        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);
        // 生成一个标题样式
        HSSFCellStyle style = createTitleStyle(workbook);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成一个单元格样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

        //产生表格标题行
        HSSFRow row = sheet.createRow(1);
        for (short i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //遍历集合数据，产生数据行
        int index = 2; //行号

        for (Object obj : dataList) {
            index++;

            //创建一行
            row = sheet.createRow(index);

            for (int i = 0; i < fields.length; i++) {
                //生成单元格
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(style2);

                //获取值
                Object textValue = getValInPojo(fields[i], obj, renders);
                //把值放入单元格
                cell.setCellValue(new HSSFRichTextString(String.valueOf(textValue)));
            }
        }

        //导出
        export2Http(response, workbook);

    }

    /**
     * jsl 2017-07-27 修改到处模板
     */
    public static void newExportExcel(HttpServletResponse response, List<?> dataList, String title, String[] headers, String[] fields, String[] fields2, String[] renders) {

        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);
        // 生成一个标题样式
        HSSFCellStyle style = createTitleStyle(workbook);
        // 生成一个单元格样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

        //产生表格标题行
        HSSFRow row = sheet.createRow(0);
        //循环每一列，标题行赋值
        for (short i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            //设置列样式
            cell.setCellStyle(style);
            //获取每一列的值
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            //列赋值
            cell.setCellValue(text);
        }

        int index = 0; //行号
        int mainIndex = 0; //主行号  ，无实际意义，用于记录主行的起始行号
        //遍历集合数据，产生数据行
        for (Object obj : dataList) {
            index++;
            mainIndex = index;

            //创建一行
            row = sheet.createRow(index);

            for (int i = 0; i < fields.length; i++) {
                //生成单元格
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(style2);

                //获取值
                Object textValue = getValInPojo(fields[i], obj, renders);

                if (textValue instanceof String) {
                    //把值放入单元格
                    cell.setCellValue(new HSSFRichTextString(String.valueOf(textValue)));
                } else if (textValue instanceof java.util.List) { //处理明细
                    List<Object> dtls = (List<Object>) textValue;
                    for (int k = 0; k < dtls.size(); k++) { //开始遍历明细
                        if (k > 0) { //第2条明细开始新建行
                            index++;
                            row = sheet.createRow(index);

                            //循环列,并且填充数据
                            //2017-07-27 jsl
                            for (int f = 0; f < fields.length; f++) {
                                cell = row.createCell(f);
                                cell.setCellStyle(style2);
                                //获取值
                                Object dtlVal = getValInPojo(fields[f], obj, renders);
                                //把值放入单元格
                                cell.setCellValue(new HSSFRichTextString(String.valueOf(dtlVal)));

                            }

                        }
                        //					 if (k == dtls.size() - 1) { //最后一条明细合并主列单元格
                        //					     //填充单元格
                        //					     for (int n = 0; n < fields.length - 1; n++) {
                        //					         sheet.addMergedRegion(new Region(mainIndex, (short) n, index, (short) n));
                        //					     }
                        //					 }
                        //填充单元格
                        Object dtl = dtls.get(k);
                        for (int j = 0; j < fields2.length; j++) {
                            cell = row.createCell(i + j);
                            cell.setCellStyle(style2);
                            //获取值
                            Object dtlVal = getValInPojo(fields2[j], dtl, renders);
                            //把值放入单元格
                            cell.setCellValue(new HSSFRichTextString(String.valueOf(dtlVal)));

                        }
                    }
                }

            }
        }

        //导出
        export2Http(response, workbook);

    }

    /**
     * 导出横向三级明细
     * 
     * created by tanyicheng in 2018年6月5日-上午8:36:55
     * @param dataList
     * @param title
     * @param headers
     * @param firstFields
     * @param secondFields
     * @param twoFieldsMap
     * @param threeFieldsMap
     * @param renders
     * @throws Exception
     */
    public static void exportExcelBy3(HttpServletResponse response, List<?> dataList, String title, String[] headers, String[] firstFields, String[] secondFields, Map<String, String[]> twoFieldsMap,
                                      Map<String, String[]> threeFieldsMap, String[] renders) {

        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);
        // 生成一个标题样式
        HSSFCellStyle style = createTitleStyle(workbook);
        // 生成一个单元格样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        int index = 0; // 行号
        int oneIndex = 0; // 主行号（一级list），无实际意义，用于记录主行的起始行号
        Integer[] mainFieldColumnIndex = new Integer[firstFields.length]; // 主表列索引
        int currColumnIndex = 0; // 记录当前列索引

        // 遍历集合数据，产生数据行
        for (Object obj : dataList) {
            index++;//从第二行开始
            oneIndex = index;
            // 创建一行
            row = sheet.createRow(index);

            for (int i = 0; i < firstFields.length; i++) {
                // 生成单元格
                HSSFCell cell = row.createCell(currColumnIndex);
                cell.setCellStyle(style2);
                // 获取值
                Object textValue = getValInPojo(firstFields[i], obj, renders);

                if (textValue instanceof String) {
                    // 把值放入单元格
                    cell.setCellValue(new HSSFRichTextString(String.valueOf(textValue)));
                    // 记录主表列索引值
                    mainFieldColumnIndex[i] = currColumnIndex;
                } else if (textValue instanceof java.util.List) { // 处理二级list
                    List<Object> dtls2 = (List<Object>) textValue;
                    //二级list赋值开始
                    int rowIndex2 = index;
                    HSSFRow detailRow2 = row;
                    //获取二级明细的字段
                    String[] fields2 = twoFieldsMap.get(firstFields[i]);
                    if (fields2 == null) {
                        System.out.println("excel导出异常, " + firstFields[i] + " 不存在对应的明细列");
                        return;
                    }

                    for (int k = 0; k < dtls2.size(); k++) { // 开始遍历明细
                        if (k > 0) { // 第2条明细开始新建行
                            rowIndex2++;
                            detailRow2 = sheet.createRow(rowIndex2);
                        }
                        int strRow = rowIndex2;//起始行
                        int endRow = 0;//结束行
                        int strCol = firstFields.length - 1;//起始列
                        //三级明细行标记
                        int rowIndex3 = rowIndex2;
                        // 填充单元格
                        Object dtl2 = dtls2.get(k);
                        for (int j = 0; j < fields2.length; j++) {
                            cell = detailRow2.createCell(currColumnIndex + j);
                            cell.setCellStyle(style2);
                            // 获取值
                            Object val2 = getValInPojo(fields2[j], dtl2, renders);

                            if (val2 instanceof String) {
                                // 把值放入单元格
                                cell.setCellValue(new HSSFRichTextString(String.valueOf(val2)));
                                // 记录主表列索引值
                                mainFieldColumnIndex[i] = currColumnIndex;
                                endRow = strRow;
                            } else if (val2 instanceof java.util.List) { // 处理三级明细
                                List<Object> dtls3 = (List<Object>) val2;
                                HSSFRow detailRow3 = detailRow2;
                                String[] fields3 = threeFieldsMap.get(fields2[j]);
                                if (fields3 == null) {
                                    System.out.println("excel导出异常, " + fields2[j] + " 不存在对应的明细列");
                                    return;
                                }
                                if (dtls3.size() > 0) {
                                    endRow = strRow + dtls3.size() - 1;
                                } else {
                                    endRow = strRow;
                                }

                                for (int k3 = 0; k3 < dtls3.size(); k3++) {
                                    if (k3 > 0) { // 第2条明细开始新建行
                                        rowIndex3++;
                                        detailRow3 = sheet.createRow(rowIndex3);
                                    }

                                    Object dtl3 = dtls3.get(k3);
                                    for (int m = 0; m < fields3.length; m++) {
                                        cell = detailRow3.createCell(currColumnIndex + fields2.length - 1 + m);
                                        cell.setCellStyle(style2);
                                        Object val3 = getValInPojo(fields3[m], dtl3, renders);
                                        // 把值放入单元格
                                        cell.setCellValue(new HSSFRichTextString(String.valueOf(val3)));
                                    }
                                }
                                // 更新当前主表行索引
                                rowIndex2 = rowIndex3;
                            }
                        }
                        // 在一行数据填充完毕之后合并单元格，这样明细列可以灵活指定位置，也可以有多个明细列
                        for (int n = 0; n < fields2.length; n++) {
                            Object val2 = getValInPojo(fields2[n], dtl2, renders);
                            if (val2 instanceof String) {
                                sheet.addMergedRegion(new CellRangeAddress(strRow, endRow, strCol, strCol));
                                strCol++;
                            }
                        }
                    }

                    // 明细处理完之后更新当前列索引
                    currColumnIndex += fields2.length - 1;
                    // 更新当前主表行索引
                    index = rowIndex2;
                }

                // 更新当前列索引
                currColumnIndex++;
            }

            // 在一行数据填充完毕之后合并单元格，这样明细列可以灵活指定位置，也可以有多个明细列
            for (int n = 0; n < firstFields.length; n++) {
                Object textValue = getValInPojo(firstFields[n], obj, renders);
                if (textValue instanceof String) {
                    short shortValue = mainFieldColumnIndex[n].shortValue();
                    sheet.addMergedRegion(new Region(oneIndex, shortValue, index, shortValue));
                }
            }
            // 当前行处理完 清空当前列索引
            currColumnIndex = 0;
        }
        // 导出
        export2Http(response, workbook);
    }

}
