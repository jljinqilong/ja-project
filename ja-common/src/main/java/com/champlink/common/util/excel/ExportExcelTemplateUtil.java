package com.champlink.common.util.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import com.champlink.common.util.exception.AppException;

/**
 * 
 * poi根据模板导出excel，根据excel坐标赋值，如（B1）
 * 
 * @author natyu
 * @date 2016年10月10日 下午2:47:49
 * 
 */
public class ExportExcelTemplateUtil {
    //模板map
    private Map<String, HSSFWorkbook> tempWorkbook = new HashMap<String, HSSFWorkbook>();
    //模板输入流map
    private Map<String, FileInputStream> tempStream = new HashMap<String, FileInputStream>();

    /**
     * 
     * 临时单元格数据
     * 
     * @author natyu
     * @date 2016年10月10日 下午4:25:51
     * 
     */
    class TempCell {
        private int row;
        private int column;
        private CellStyle cellStyle;
        private Object data;
        //用于列表合并，表示几列合并
        private int columnSize = -1;

        public int getColumn() {
            return column;
        }

        public void setColumn(int column) {
            this.column = column;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public CellStyle getCellStyle() {
            return cellStyle;
        }

        public void setCellStyle(CellStyle cellStyle) {
            this.cellStyle = cellStyle;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public int getColumnSize() {
            return columnSize;
        }

        public void setColumnSize(int columnSize) {
            this.columnSize = columnSize;
        }
    }

    /**
     * 
     * 按模板向Excel中列表填充数据。 只支持列合并,按列
     * 
     * @author natyu
     * @date 2016年10月10日 下午4:26:14
     * @param tempFilePath
     * @param heads
     * @param datalist
     * @param sheetNo
     * @throws FileNotFoundException
     * @throws IOException
     */
    public HSSFWorkbook writeDateListByCols(String tempFilePath, List<List<Object>> datalist, String startSite, int sheetNo) throws FileNotFoundException, IOException {
        //读取模板
        HSSFWorkbook wbModule = getTempWorkbook(tempFilePath);
        //数据填充的sheet
        Sheet wsheet = wbModule.getSheetAt(sheetNo);
        //列表数据模板cell
        Integer column = Integer.parseInt(startSite.split(",")[0]);
        Integer row = Integer.parseInt(startSite.split(",")[1]);
        //赋值
        for (int i = 0; i < datalist.size(); i++) {
            List<Object> dataMap = datalist.get(i);
            for (int j = 0; j < dataMap.size(); j++) {
                TempCell tempCell = getCell(column + i, row + j, null, wsheet);
                //取得合并单元格位置  -1：表示不是合并单元格
                int pos = isMergedRegion(wsheet, tempCell.getRow(), tempCell.getColumn());
                if (pos > -1) {
                    CellRangeAddress range = wsheet.getMergedRegion(pos);
                    tempCell.setColumnSize(range.getLastColumn() - range.getFirstColumn());
                }
                tempCell.setRow(tempCell.getRow());
                tempCell.setData(dataMap.get(j));
                setCell(tempCell, wsheet);
            }
        }
        return wbModule;
    }

    /**
     * 
     * 按模板向Excel中列表填充数据。 只支持列合并,按行
     * 
     * @author natyu
     * @date 2016年10月10日 下午4:26:14
     * @param tempFilePath
     * @param heads
     * @param datalist
     * @param sheetNo
     * @throws FileNotFoundException
     * @throws IOException
     */
    public HSSFWorkbook writeDateListByRows(String tempFilePath, List<List<Object>> datalist, String startSite, int sheetNo) throws FileNotFoundException, IOException {
        //读取模板
        HSSFWorkbook wbModule = getTempWorkbook(tempFilePath);
        //数据填充的sheet
        Sheet wsheet = wbModule.getSheetAt(sheetNo);
        //列表数据模板cell
        Integer column = Integer.parseInt(startSite.split(",")[0]);
        Integer row = Integer.parseInt(startSite.split(",")[1]);
        //赋值
        for (int i = 0; i < datalist.size(); i++) {
            List<Object> dataMap = datalist.get(i);
            for (int j = 0; j < dataMap.size(); j++) {
                TempCell tempCell = getCell(column + j, row + i, null, wsheet);
                //取得合并单元格位置  -1：表示不是合并单元格
                int pos = isMergedRegion(wsheet, tempCell.getRow(), tempCell.getColumn());
                if (pos > -1) {
                    CellRangeAddress range = wsheet.getMergedRegion(pos);
                    tempCell.setColumnSize(range.getLastColumn() - range.getFirstColumn());
                }
                tempCell.setRow(tempCell.getRow());
                tempCell.setData(dataMap.get(j));
                setCell(tempCell, wsheet);
            }
        }
        return wbModule;
    }

    /**
     * 
     * 按模板向Excel中列表填充数据。 只支持列合并,按列
     * 
     * @author natyu
     * @date 2016年10月10日 下午4:26:14
     * @param tempFilePath
     * @param heads
     * @param datalist
     * @param sheetNo
     * @throws FileNotFoundException
     * @throws IOException
     */
    public HSSFWorkbook writeDateListByMoreCols(String tempFilePath, List<List<List<Object>>> datalist, String[] startSite, int sheetNo) throws FileNotFoundException, IOException {
        //读取模板
        HSSFWorkbook wbModule = getTempWorkbook(tempFilePath);
        //数据填充的sheet
        Sheet wsheet = wbModule.getSheetAt(sheetNo);
        for (int k = 0; k < datalist.size(); k++) {
            List<List<Object>> data = datalist.get(k);
            //列表数据模板cell
            Integer column = Integer.parseInt(startSite[k].split(",")[0]);
            Integer row = Integer.parseInt(startSite[k].split(",")[1]);
            //赋值
            for (int i = 0; i < data.size(); i++) {
                List<Object> dataMap = data.get(i);
                for (int j = 0; j < dataMap.size(); j++) {
                    TempCell tempCell = getCell(column + i, row + j, null, wsheet);
                    //取得合并单元格位置  -1：表示不是合并单元格
                    int pos = isMergedRegion(wsheet, tempCell.getRow(), tempCell.getColumn());
                    if (pos > -1) {
                        CellRangeAddress range = wsheet.getMergedRegion(pos);
                        tempCell.setColumnSize(range.getLastColumn() - range.getFirstColumn());
                    }
                    tempCell.setRow(tempCell.getRow());
                    tempCell.setData(dataMap.get(j));
                    setCell(tempCell, wsheet);
                }
            }
        }
        return wbModule;
    }

    /**
     * 
     * 按模板向Excel中列表填充数据。 只支持列合并,按行
     * 
     * @author natyu
     * @date 2016年10月10日 下午4:26:14
     * @param tempFilePath
     * @param heads
     * @param datalist
     * @param sheetNo
     * @throws FileNotFoundException
     * @throws IOException
     */
    public HSSFWorkbook writeDateListByMoreRows(String tempFilePath, List<List<List<Object>>> datalist, String[] startSite, int sheetNo) throws FileNotFoundException, IOException {
        //读取模板
        HSSFWorkbook wbModule = getTempWorkbook(tempFilePath);
        //数据填充的sheet
        Sheet wsheet = wbModule.getSheetAt(sheetNo);
        for (int k = 0; k < datalist.size(); k++) {
            List<List<Object>> data = datalist.get(k);
            //列表数据模板cell
            Integer column = Integer.parseInt(startSite[k].split(",")[0]);
            Integer row = Integer.parseInt(startSite[k].split(",")[1]);
            //赋值
            for (int i = 0; i < data.size(); i++) {
                List<Object> dataMap = data.get(i);
                for (int j = 0; j < dataMap.size(); j++) {
                    TempCell tempCell = getCell(column + j, row + i, null, wsheet);
                    //取得合并单元格位置  -1：表示不是合并单元格
                    int pos = isMergedRegion(wsheet, tempCell.getRow(), tempCell.getColumn());
                    if (pos > -1) {
                        CellRangeAddress range = wsheet.getMergedRegion(pos);
                        tempCell.setColumnSize(range.getLastColumn() - range.getFirstColumn());
                    }
                    tempCell.setRow(tempCell.getRow());
                    tempCell.setData(dataMap.get(j));
                    setCell(tempCell, wsheet);
                }
            }
        }
        return wbModule;
    }

    /**
     * 
     * 获取输入工作区
     * 
     * @author natyu
     * @date 2016年10月10日 下午4:26:23
     * @param tempFilePath
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    private HSSFWorkbook getTempWorkbook(String tempFilePath) throws FileNotFoundException, IOException {
        if (!tempWorkbook.containsKey(tempFilePath)) {
            //            if (tempFilePath.endsWith(".xlsx")) {
            //                tempWorkbook.put(tempFilePath, new XSSFWorkbook(getFileInputStream(tempFilePath)));
            //            } else if (tempFilePath.endsWith(".xls")) {
            tempWorkbook.put(tempFilePath, new HSSFWorkbook(getFileInputStream(tempFilePath)));
            //            }
        }
        return tempWorkbook.get(tempFilePath);
    }

    /**
     * 
     * 获得模板输入流
     * 
     * @author natyu
     * @date 2016年10月10日 下午4:26:31
     * @param tempFilePath
     * @return
     * @throws FileNotFoundException
     */
    private FileInputStream getFileInputStream(String tempFilePath) throws FileNotFoundException {
        if (!tempStream.containsKey(tempFilePath)) {
            tempStream.put(tempFilePath, new FileInputStream(tempFilePath));
        }
        return tempStream.get(tempFilePath);
    }

    /**
     * 
     * 设置单元格数据,样式 (根据坐标：B3)
     * 
     * @author natyu
     * @date 2016年10月10日 下午4:26:41
     * @param point
     * @param data
     * @param sheet
     * @return
     */
    private TempCell getCell(Integer column, Integer row, Object data, Sheet sheet) {
        TempCell tempCell = new TempCell();
        tempCell.setColumn(column);
        tempCell.setRow(row);

        //获取模板指定单元格样式，设置到tempCell （写列表数据的时候用）
        Row rowIn = sheet.getRow(tempCell.getRow());
        if (rowIn == null) {
            rowIn = sheet.createRow(tempCell.getRow());
        }
        Cell cellIn = rowIn.getCell(tempCell.getColumn());
        if (cellIn == null) {
            cellIn = rowIn.createCell(tempCell.getColumn());
        }
        tempCell.setCellStyle(cellIn.getCellStyle());

        tempCell.setData(data);
        return tempCell;
    }

    /**
     * 
     * 给指定坐标赋值
     * 
     * @author natyu
     * @date 2016年10月10日 下午4:27:06
     * @param tempCell
     * @param sheet
     */
    private void setCell(TempCell tempCell, Sheet sheet) {
        if (tempCell.getColumnSize() > -1) {
            CellRangeAddress rangeAddress = mergeRegion(sheet, tempCell.getRow(), tempCell.getRow(), tempCell.getColumn(), tempCell.getColumn() + tempCell.getColumnSize());
            setRegionStyle(tempCell.getCellStyle(), rangeAddress, sheet);
        }

        Row rowIn = sheet.getRow(tempCell.getRow());
        if (rowIn == null) {
            rowIn = sheet.createRow(tempCell.getRow());
        }
        Cell cellIn = rowIn.getCell(tempCell.getColumn());
        if (cellIn == null) {
            cellIn = rowIn.createCell(tempCell.getColumn());
        }
        //根据data类型给cell赋值
        if (tempCell.getData() == null) {

        } else if (tempCell.getData() instanceof String) {
            cellIn.setCellValue((String) tempCell.getData());
        } else if (tempCell.getData() instanceof Integer) {
            cellIn.setCellValue((int) tempCell.getData());
        } else if (tempCell.getData() instanceof Double) {
            cellIn.setCellValue((double) tempCell.getData());
        } else {
            cellIn.setCellValue((String) tempCell.getData());
        }
        //样式
        if (tempCell.getCellStyle() != null && tempCell.getColumnSize() == -1) {
            cellIn.setCellStyle(tempCell.getCellStyle());
        }

    }

    /**
     * 
     * 写到输出流并移除资源
     * 
     * @author natyu
     * @date 2016年10月10日 下午4:27:24
     * @param tempFilePath
     * @param os
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void writeAndClose(String tempFilePath, OutputStream os) throws FileNotFoundException, IOException {
        if (getTempWorkbook(tempFilePath) != null) {
            getTempWorkbook(tempFilePath).write(os);
            tempWorkbook.remove(tempFilePath);
        }
        if (getFileInputStream(tempFilePath) != null) {
            getFileInputStream(tempFilePath).close();
            tempStream.remove(tempFilePath);
        }
    }

    /**
     * 
     * 判断指定的单元格是否是合并单元格
     * 
     * @author natyu
     * @date 2016年10月10日 下午4:27:33
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    private Integer isMergedRegion(Sheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if (row >= firstRow && row <= lastRow) {
                if (column >= firstColumn && column <= lastColumn) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 
     * 合并单元格
     * 
     * @author natyu
     * @date 2016年10月10日 下午4:27:41
     * @param sheet
     * @param firstRow
     * @param lastRow
     * @param firstCol
     * @param lastCol
     * @return
     */
    private CellRangeAddress mergeRegion(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {
        CellRangeAddress rang = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
        sheet.addMergedRegion(rang);
        return rang;
    }

    /**
     * 
     * 设置合并单元格样式
     * 
     * @author natyu
     * @date 2016年10月10日 下午4:27:48
     * @param cs
     * @param region
     * @param sheet
     */
    private static void setRegionStyle(CellStyle cs, CellRangeAddress region, Sheet sheet) {
        for (int i = region.getFirstRow(); i <= region.getLastRow(); i++) {
            Row row = sheet.getRow(i);
            if (row == null)
                row = sheet.createRow(i);
            for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++) {
                Cell cell = row.getCell(j);
                if (cell == null) {
                    cell = row.createCell(j);
                    cell.setCellValue("");
                }
                cell.setCellStyle(cs);
            }
        }
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

        String fileName = fn + (new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date()) + ".xlsx";
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
     * 
     * 按列添加数据
     * 
     * @author natyu
     * @date 2016年10月13日 下午1:47:06
     * @param response
     * @param resFileName 模板文件名
     * @param fileName 导出文件名
     * @param startSite 数据插入起始位置 例：（0,1） 1列2行
     * @param data 插入数据
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void exportExcelTemplateByCols(HttpServletResponse response, String resFileName, String fileName, String startSite, List<List<Object>> data)
        throws FileNotFoundException, IOException {
        ExportExcelTemplateUtil excel = new ExportExcelTemplateUtil();
        String tempFilePath = "excelTemplate/" + resFileName;
        ClassLoader classLoader = ExportExcelTemplateUtil.class.getClassLoader();
        URL resource = classLoader.getResource(tempFilePath);
        HSSFWorkbook workbook = excel.writeDateListByCols(resource.getPath(), data, startSite, 0);
        excel.export2HttpMultipleSheet(response, workbook, fileName);
    }

    /**
     * 
     * 按行添加数据
     * 
     * @author natyu
     * @date 2016年10月13日 下午1:47:06
     * @param response
     * @param resFileName 模板文件名
     * @param fileName 导出文件名
     * @param startSite 数据插入起始位置 例：（0,1） 1列2行
     * @param data 插入数据
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void exportExcelTemplateByRows(HttpServletResponse response, String resFileName, String fileName, String startSite, List<List<Object>> data)
        throws FileNotFoundException, IOException {
        ExportExcelTemplateUtil excel = new ExportExcelTemplateUtil();
        String tempFilePath = "excelTemplate/" + resFileName;
        ClassLoader classLoader = ExportExcelTemplateUtil.class.getClassLoader();
        URL resource = classLoader.getResource(tempFilePath);
        HSSFWorkbook workbook = excel.writeDateListByRows(resource.getPath(), data, startSite, 0);
        excel.export2HttpMultipleSheet(response, workbook, fileName);
    }

    /**
     * 
     * 多数据块按列添加数据
     * 
     * @author natyu
     * @date 2016年10月18日 下午1:47:06
     * @param response
     * @param resFileName 模板文件名
     * @param fileName 导出文件名
     * @param startSite 数据插入起始位置 例：（0,1） 1列2行
     * @param data 插入数据
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void exportExcelTemplateByMoreCols(HttpServletResponse response, String resFileName, String fileName, String[] startSite, List<List<List<Object>>> data)
        throws FileNotFoundException, IOException {
        ExportExcelTemplateUtil excel = new ExportExcelTemplateUtil();
        String tempFilePath = "excelTemplate/" + resFileName;
        ClassLoader classLoader = ExportExcelTemplateUtil.class.getClassLoader();
        URL resource = classLoader.getResource(tempFilePath);
        HSSFWorkbook workbook = excel.writeDateListByMoreCols(resource.getPath(), data, startSite, 0);
        excel.export2HttpMultipleSheet(response, workbook, fileName);
    }

    /**
     * 
     * 多数据块按行添加数据
     * 
     * @author natyu
     * @date 2016年10月18日 下午1:47:06
     * @param response
     * @param resFileName 模板文件名
     * @param fileName 导出文件名
     * @param startSite 数据插入起始位置 例：（0,1） 1列2行
     * @param data 插入数据
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void exportExcelTemplateByMoreRows(HttpServletResponse response, String resFileName, String fileName, String[] startSite, List<List<List<Object>>> data)
        throws FileNotFoundException, IOException {
        ExportExcelTemplateUtil excel = new ExportExcelTemplateUtil();
        String tempFilePath = "excelTemplate/" + resFileName;
        ClassLoader classLoader = ExportExcelTemplateUtil.class.getClassLoader();
        URL resource = classLoader.getResource(tempFilePath);
        HSSFWorkbook workbook = excel.writeDateListByMoreRows(resource.getPath(), data, startSite, 0);
        excel.export2HttpMultipleSheet(response, workbook, fileName);
    }

}
