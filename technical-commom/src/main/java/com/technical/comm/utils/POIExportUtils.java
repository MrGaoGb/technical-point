package com.technical.comm.utils;

import com.technical.comm.annotation.ExportExcel;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @Description Excel操作工具类
 * @Author gaogba
 * @Date 2020/10/22 18:19
 * @Version 1.0
 */
public class POIExportUtils<T> {

    /**
     * 默认列宽
     */
    private static final int DEFAULT_COL_WIDTH = 15;

    public POIExportUtils() {
    }

    public void ResponseInit(HttpServletResponse response, String fileName) throws Exception {
        //response.reset();
        //设置content-disposition响应头控制浏览器以下载的形式打开文件
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1") + ".xlsx");
        //让服务器告诉浏览器它发送的数据属于excel文件类型:
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Prama", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
    }

    /**
     * 写入并关闭流资源
     *
     * @param response
     * @param wb
     */
    public void POIOutPutStream(HttpServletResponse response, SXSSFWorkbook wb) {

        try {
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            wb.write(out);
            wb.close();
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 导出EXCEL
     *
     * @param objClass
     * @param dataList
     * @param response
     * @param fileName
     * @throws Exception
     */
    public void export(Class<T> objClass, List<T> dataList, HttpServletResponse response, String fileName) throws Exception {

        ResponseInit(response, fileName);

        Class excelClass = Class.forName(objClass.toString().substring(6));
        Field[] fields = excelClass.getDeclaredFields();

        Map<Integer, String> mapCol = new TreeMap<>();
        Map<Integer, String> mapMethod = new TreeMap<>();

        Arrays.stream(fields).forEach(f -> {
            Annotation[] annotations = f.getAnnotations();
            Arrays.stream(annotations).forEach(ann -> {
                if (ann instanceof ExportExcel) {
                    ExportExcel exportExcelAnnotation = (ExportExcel) ann;
                    Optional.ofNullable(exportExcelAnnotation).ifPresent(exportExcel -> {
                        mapCol.put(exportExcelAnnotation.order(), exportExcelAnnotation.colName());
                        mapMethod.put(exportExcelAnnotation.order(), f.getName());
                    });
                }
            });
        });

        //内存中保留 10000 条数据，以免内存溢出，其余写入 硬盘
        SXSSFWorkbook wb = new SXSSFWorkbook(10000);
        POIBuildBody(POIBuildHead(wb, "sheet1", mapCol), excelClass, mapMethod, dataList);

        POIOutPutStream(response, wb);
    }

    /**
     * 构建Excel表头
     *
     * @param wb
     * @param sheetName
     * @param mapCol
     * @return
     */
    public SXSSFSheet POIBuildHead(SXSSFWorkbook wb, String sheetName, Map<Integer, String> mapCol) {
        SXSSFSheet sheet01 = wb.createSheet(sheetName);
        sheet01.setDefaultColumnWidth(DEFAULT_COL_WIDTH);//设置默认列宽
//        sheet01.setDefaultRowHeight();
        SXSSFRow row = sheet01.createRow(0);

        SXSSFCell cell;
        int i = 0;
        for (Map.Entry<Integer, String> entry : mapCol.entrySet()) {
            cell = row.createCell(i++);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(entry.getValue());
        }
        return sheet01;
    }

    /**
     * 构建Excel内容体
     *
     * @param sheet01
     * @param excelClass
     * @param mapMethod
     * @param dataList
     * @throws Exception
     */
    public void POIBuildBody(SXSSFSheet sheet01, Class<T> excelClass, Map<Integer, String> mapMethod, List<T> dataList) throws Exception {

        SXSSFRow r = null;
        SXSSFCell c = null;

        if (dataList != null && dataList.size() > 0) {
            for (int i = 0; i < dataList.size(); i++) {
                r = sheet01.createRow(i + 1);
                //r.setHeightInPoints(25);
                int j = 0;
                for (Map.Entry<Integer, String> entry : mapMethod.entrySet()) {
                    c = r.createCell(j++);
                    StringBuilder sb = new StringBuilder();
                    String firstLetter = entry.getValue().substring(0, 1).toUpperCase();
                    sb.append("get").append(firstLetter).append(entry.getValue().substring(1));
                    String methodName = sb.toString();
                    Object obj = excelClass.getDeclaredMethod(methodName).invoke(dataList.get(i));
                    c.setCellType(CellType.STRING);
                    c.setCellValue(obj == null ? "" : obj + "");
                }
            }
        }
    }

}
