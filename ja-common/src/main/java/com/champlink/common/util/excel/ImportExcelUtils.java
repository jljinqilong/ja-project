package com.champlink.common.util.excel;

import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.champlink.common.util.annotations.Excel;
import com.champlink.common.util.annotations.Validation;
import com.champlink.common.util.exception.AppException;

/**
 * Excel 工具类
 * 
 * @author natyu
 * @date 2016年3月29日 上午10:56:51
 *
 */
public class ImportExcelUtils {
    /**
     * 系统日志配置.
     */
    private static Logger logger = LoggerFactory.getLogger(ImportExcelUtils.class);

    /**
     * 导入Excel
     * 
     * @author natyu
     * @date 2016年3月29日 上午11:13:38
     * @param request
     * @param classe
     * @return
     */
    public static List export(HttpServletRequest request, Class classe) {
        List list = new ArrayList();
        export(request, classe, list);
        return list;
    }

    /**
     * 导入Excel
     * 
     * @author natyu
     * @date 2016年3月29日 上午11:13:47
     * @param request
     * @param classe
     * @param list
     */
    public static void export(HttpServletRequest request, Class classe, List list) {
    	export(request, classe, list, 0,0);
    }
    
    public static void exportCustom(HttpServletRequest request, Class classe, List list, int sourceRowNum, int sheetNum) {
    	export(request, classe, list, sourceRowNum,sheetNum);
    }
    
    /**
     * 导入Excel
     * @param request
     * @param classe
     * @param list
     * @param sourceRowNum 实际数据从第几行开始读取
     * @param sheetNum 从第几个sheet页开始读取
     * 
     */
    public static void export(HttpServletRequest request, Class classe, List list,  int sourceRowNum, int sheetNum) {
    	MultipartHttpServletRequest mulRequest = (MultipartHttpServletRequest) request;
    	MultipartFile file = mulRequest.getFile("excel");
    	if (null == file) {
    		throw new AppException("文件错误");
    	}
    	String filename = file.getOriginalFilename();
    	if (filename == null || "".equals(filename) || (!filename.endsWith(".xls") && !filename.endsWith(".xlsx"))) {
    		throw new AppException("请选择excel文件！");
    	}
    	boolean isExcel2003 = filename.endsWith(".xls");
    	try {
    		InputStream input = file.getInputStream();
    		export(input, classe, list, isExcel2003, sourceRowNum,sheetNum);
    	} catch (AppException e) {
    		throw e;
    	} catch (Exception e) {
    		logger.error("ExcelUtils", e);
    		throw new AppException("存在非法内容，请检查");
    	}
    }

    /**
     * 导入Excel 扩展 修改: 传入一个数据，用于获取指定的sheet
     * 
     * @author natyu
     * @date 2016年3月29日 上午11:13:47
     * @param request
     * @param classe
     * @param list
     */
    /*public static void export(HttpServletRequest request, Class classe, List list, int i) {
    	export2(request, classe, list, 0, i);
    }*/

    /**
     * 导入Excel
     * 
     * @author natyu
     * @date 2016年3月29日 上午11:13:53
     * @param is
     * @param classe
     * @param list
     * @param isExcel2003
     * @throws Exception
     */
    public static void export(InputStream is, Class classe, List list, boolean isExcel2003) throws Exception {
    	export(is, classe, list, isExcel2003, 0, 0);
    }
    
    /**
     * 
     * @param is
     * @param classe
     * @param list
     * @param isExcel2003
     * @param i 实际的读取数据行数
     * @throws Exception
     */
    public static void export(InputStream is, Class classe, List list, boolean isExcel2003, int sourceRowNum, int sheetNum) throws Exception {
    	Workbook wb = null;
    	Sheet sheet = null;
    	Row row = null;
    	if (isExcel2003) {
    		wb = new HSSFWorkbook(is);
    	} else {
    		wb = new XSSFWorkbook(is);
    	}
    	sheet = wb.getSheetAt(sheetNum);
    	row = sheet.getRow(sourceRowNum);
    	int colNum = row.getPhysicalNumberOfCells();
    	List<String> excelList = readExcelExcelTitle(row, colNum);
    	excel2Bean2(sheet, list, excelList, colNum, classe,sourceRowNum+1);
    }

    /**
     * 导入Excel 扩展 修改：获取指定的sheet页面数据
     * @author natyu
     * @date 2016年3月29日 上午11:13:53
     * @param is
     * @param classe
     * @param list
     * @param isExcel2003
     * @throws Exception
     */
    /*public static void export(InputStream is, Class classe, List list, boolean isExcel2003, int i) throws Exception {
    	export(is, classe, list, isExcel2003, 0, i);
    }*/

    /**
     * 读取Excel标题
     * 
     * @author natyu
     * @date 2016年3月29日 上午11:13:59
     * @param row
     * @param colNum
     * @return
     */
    private static List<String> readExcelExcelTitle(Row row, int colNum) {
        List<String> excelList = new ArrayList<String>();
        for (int i = 0; i < colNum; i++) {
            excelList.add(i, (row.getCell((short) i).getRichStringCellValue().getString()));
        }
        return excelList;
    }

    /**
     * Excel转为Bean
     * 
     * @author natyu
     * @date 2016年3月29日 上午11:14:07
     * @param sheet
     * @param list
     * @param excelList
     * @param colNum
     * @param t
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws ParseException
     */
    /*private static void excel2Bean(Sheet sheet, List list, List<String> excelList, int colNum, Class t)
        throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, ParseException {
    	excel2Bean2(sheet, list, excelList, colNum, t, 0);
    }*/
    
    /**
     * 
     * @param sheet
     * @param list
     * @param excelList
     * @param colNum
     * @param t
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws ParseException
     */
    private static void excel2Bean2(Sheet sheet, List list, List<String> excelList, int colNum, Class t,int ii)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, ParseException {
            Field[] fields = t.getDeclaredFields();
            // 得到总行数
            int rowNum = sheet.getLastRowNum();
            List<String> errMsgs = new ArrayList<String>();
            for (int i = ii; i <= rowNum; i++) {
                Row row = sheet.getRow(i);
                Object object = t.newInstance();
                cPoint: for (Field field : fields) {
                    if (field.isAnnotationPresent(Excel.class)) {
                        boolean titleValidate = false;
                        Annotation p = field.getAnnotation(Excel.class);
                        Method m = p.getClass().getDeclaredMethod("title", null);
                        String value = (String) m.invoke(p, null);
                        String rule = "";
                        String msg = "";
                        if (field.isAnnotationPresent(Validation.class)) {
                            Annotation validateAnt = field.getAnnotation(Validation.class);
                            Method ruleMethod = validateAnt.getClass().getDeclaredMethod("rule", null);
                            rule = (String) ruleMethod.invoke(validateAnt, null);
                            Method msgMethod = validateAnt.getClass().getDeclaredMethod("msg", null);
                            msg = (String) msgMethod.invoke(validateAnt, null);
                        }
                        for (int j = 0; j < colNum; j++) {
                            if (value.equals(excelList.get(j).trim())) {
                                titleValidate = true;
                                String setMethodName = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                                Method setMethod = t.getMethod(setMethodName, field.getType());
                                Method isNullMethod = p.getClass().getDeclaredMethod("isNull", null);
                                boolean isNull = (boolean) isNullMethod.invoke(p, null);
                                Object cellValue = getCellValue(row.getCell((short) j), field.getType(), rule, msg, i, errMsgs, isNull, value);
                                if (cellValue != null) {
                                    setMethod.invoke(object, cellValue);
                                }
                                continue cPoint;
                            }
                        }
                        if (!titleValidate) {
                            throw new AppException("标题:" + value + "不能为空");
                        }
                    }
                }
                list.add(object);
            }
            if (!errMsgs.isEmpty()) {
                String errString = "";
                for (String errMsg : errMsgs) {
                    errString += (errMsg + "\n");
                }
                throw new AppException(errString);
            }
        }

    /**
     * 读入表格内容
     * 
     * @author natyu
     * @date 2016年3月29日 上午11:14:15
     * @param cell
     * @param type
     * @param rule
     * @param msg
     * @param i
     * @param errMsgs
     * @param isNull
     * @param title
     * @return
     * @throws ParseException
     */
    private static Object getCellValue(Cell cell, Class type, String rule, String msg, int i, List<String> errMsgs, boolean isNull, String title) throws ParseException {
        if (cell != null) {
            if (type == Date.class) {
                if (cell.getCellType() != Cell.CELL_TYPE_NUMERIC) {
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    String cellvalue = cell.getRichStringCellValue().getString();
                    if (StringUtils.isEmpty(cellvalue)) {
                        if (!isNull) {
                            errMsgs.add("第" + (i + 1) + "行" + title + "不能为空");
                        }
                        return null;
                    }
                    if (!StringUtils.isEmpty(rule)) {
                        if (!cellvalue.matches(rule)) {
                            errMsgs.add("第" + (i + 1) + "行" + msg);
                            return null;
                        } else {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            return new Date(sdf.parse(cellvalue).getTime());
                        }
                    } else {
                        if (!cellvalue.matches("^([0-9]{4})-((1[0-2])|(0?[1-9]))-((3[0-1])|(0?[1-9])|([1,2][0-9]))$")) {
                            errMsgs.add("第" + (i + 1) + "行" + "日期格式有误");
                            return null;
                        } else {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            return new Date(sdf.parse(cellvalue).getTime());
                        }
                    }
                } else if (cell.getDateCellValue() == null) {
                    if (!isNull) {
                        errMsgs.add("第" + (i + 1) + "行" + title + "不能为空");
                    }
                    return null;
                }
                Date date = new Date(cell.getDateCellValue().getTime());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                if (calendar.get(Calendar.YEAR) > 9999) {
                    errMsgs.add("第" + (i + 1) + "行" + "日期格式有误");
                    return null;
                }
                return date;
            } else {
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                String cellvalue = cell.getRichStringCellValue().getString();
                if (StringUtils.isEmpty(cellvalue)) {
                    if (!isNull) {
                        errMsgs.add("第" + (i + 1) + "行" + title + "不能为空");
                    }
                    return null;
                } else if (!StringUtils.isEmpty(rule) && !cellvalue.matches(rule)) {
                    errMsgs.add("第" + (i + 1) + "行" + msg);
                    return null;
                }
                if (type == Integer.TYPE || type == Integer.class)
                    return Integer.valueOf(cellvalue);
                else if (type == Short.TYPE || type == Short.class)
                    return Short.valueOf(cellvalue);
                else if (type == Boolean.TYPE || type == Boolean.class)
                    return Boolean.valueOf(cellvalue);
                else if (type == Byte.TYPE || type == Byte.class)
                    return Byte.valueOf(cellvalue);
                else if (type == Short.TYPE || type == Short.class)
                    return Short.valueOf(cellvalue);
                else if (type == Long.TYPE || type == Long.class)
                    return Long.valueOf(cellvalue);
                else if (type == Float.TYPE || type == Float.class)
                    return Float.valueOf(cellvalue);
                else if (type == Double.TYPE || type == Double.class)
                    return Double.valueOf(cellvalue);
                else
                    return cellvalue;
            }
        }
        if (!isNull) {
            errMsgs.add("第" + (i + 1) + "行" + title + "不能为空");
        }
        return null;
    }
}
