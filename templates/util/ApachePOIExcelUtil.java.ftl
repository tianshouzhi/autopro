package ${basePackage}.util;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import sun.misc.BASE64Encoder;

/**
 * @author tianshouzhi@126.com<br>
 * 
 *         <b>介绍:</b>用于导入和导出Excel的工具类，此工具类的编写基于Apache poi 3.10版本 <BR/>
 *         依赖于以下jar包： 1、poi-3.10-FINAL-20140208.jar 2、 poi-ooxml-3.10-FINAL-20140208.jar 3、 xmlbeans-2.3.0.jar
 *         4、poi-ooxml-schemas-3.10-FINAL-20140208.jar
 * 
 * <br>
 *         <b>使用说明:</b> 1】如果要生成xls格式的excel，请调用createExcel_xls方法，并以“.xls”为后缀保存文件--本文档格式是基于office2003<br>
 *         2】如果要生成xlsx格式的excel，请调用createExcel_xlsx方法，并以".xlsx"为后缀名保存文件--本文档格式基于office2007 <br>
 *         <b>本工具类的特点：</b><br>
 *         1、所有单元格可以自适应宽度，在网上有很多人咨询，poi不支持中文自适应宽度，这是一个误解！ <br>
 *         之所以他们使用中文没有自适应宽度有两个原因： a)、没有设置单元格字体为中文字体，导致计算出来的列的宽度小于真实宽度
 *         b)、在建立workbook之初，也就是没有创建任何cell，或者没有往cell中填充任何内容，就使用了sheet.autoSizeColumn(i);
 *         这种情况下，poi默认会以第一行每一列的宽度为基准，导致即使设置了中文字体，也没有自适应宽度，正确 的做法应该是等所有单元格内容填充完之后再针对每一列自适应大小。 <br>
 * 
 *         2、支持数据类型的自动转换，例如将Date类型，Boolean类型等。 <br>
 *         <b>提示:</b>，在poi导出的数据量过大时，很多人都发现了效率很低。 建议如果需要大批量导出数据的时候，不要一次性全部导出，分批次导出。<BR>
 *         假设有10000条记录，可以分5次导出，每次导出都将数据保存到一个临时文件中，最后将所有的临时文件合并。 10000条记录同时在内存中和分五次，每次2000条记录在内存中，效率当然不一样
 */
 @SuppressWarnings("restriction")
public class ApachePOIExcelUtil {
    // 设置日期类型默认转换为的字符串格式
    private SimpleDateFormat defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    // 设置每个单元格一行默认最多显示20个字符
    private int defaultMaxColumnWidth = 20;

    private Workbook wb;

    private String wbName;

    private String mime;
    
    private int sheetCount;

	public ApachePOIExcelUtil createWorkBook(String workBookName) {
		return createWorkBook(workBookName,false);
	}

    /**
     * 
     * @param workBookName excel的名称，不需要加后缀
     * @param is_xlsx 生成Excel的后缀是否是xlsx
     * @return
     */
    public ApachePOIExcelUtil createWorkBook(String workBookName, boolean is_xlsx) {
        if (is_xlsx) {
            wb = new XSSFWorkbook();
            mime = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
            wbName = workBookName + ".xlsx";
        } else {
            wb = (Workbook) new HSSFWorkbook();
            mime = "application/vnd.ms-excel";
            wbName = workBookName + ".xls";
        }

        return this;
    }

	 /**
     * 在excel中添加一个sheet
     * 
     * @param sheetName sheet的名称
     * @param header_propertyName_pairs 为实体中的每一个属性起一个友好名称，以便普通用户查看
     * @param datas 当前sheet要显示的数据
     * @return
     */
	public <T> ApachePOIExcelUtil addSheet(
		Map<String, String> header_propertyName_pairs, List<T> datas) {
		sheetCount++;
		return addSheet("sheet"+sheetCount,header_propertyName_pairs,datas);
	}

    /**
     * 在excel中添加一个sheet
     * 
     * @param sheetName sheet的名称
     * @param header_propertyName_pairs 为实体中的每一个属性起一个友好名称，以便普通用户查看
     * @param datas 当前sheet要显示的数据
     * @return
     */
    public <T>ApachePOIExcelUtil addSheet(String sheetName, Map<String, String> header_propertyName_pairs, List<T> datas) {
        if (!header_propertyName_pairs.isEmpty()) {

            String headers[] = new String[header_propertyName_pairs.size()];
            String propertyNames[] = new String[header_propertyName_pairs.size()];
            Set<Entry<String, String>> entrySet = header_propertyName_pairs.entrySet();
            int i = 0;
            for (Entry<String, String> entry: entrySet) {
                propertyNames[i] = entry.getKey();
                headers[i] = entry.getValue();
                i++;
            }
            createSheetData(sheetName, headers, datas, propertyNames);
        }
        return this;
    }

    /**
     * 创建表格
     * 
     * @param wb
     * @param datas
     * @return
     */
    private <T>Workbook createSheetData(String sheetName, String[] headers, List<T> datas, String[] propertyNames) {
        // 得到创建的表格应该有多少行，list集合中封装了多少条数据，就有多少行
        int rowCounts = datas.size();
        // 得到创建的表格应该有多少列，表头有几个极端，创建的excel就有几列
        int columnCounts = headers.length;
        //创建Sheet
        Sheet sheet = createHeader(sheetName, headers, columnCounts);
        //创建单元格格式
        CellStyle contentStyle = setCellStyle();
        //创建数据单元格，并填充值
        setDataCellValue(datas, propertyNames, rowCounts, columnCounts, sheet, contentStyle);
        // 自适应宽高
        autoSize(columnCounts, sheet);
        return wb;
    }

    /**
     * 创建excel表头
     * @param sheetName
     * @param headers
     * @param columnCounts
     * @return
     */
    private Sheet createHeader(String sheetName, String[] headers, int columnCounts) {
        Sheet sheet = wb.createSheet(sheetName);
        // 创建表头
        Row header = sheet.createRow(0);
        // 设置表头显示样式，字体加粗
        // 创建字体
        Font headersfont = wb.createFont();
        headersfont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headersfont.setFontName("宋体");
        // 创建样式
        CellStyle headersStyle = wb.createCellStyle();
        headersStyle.setFont(headersfont);
        headersStyle.setAlignment(CellStyle.ALIGN_CENTER);
        headersStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        for (int i = 0; i < columnCounts; i++) {
            Cell cell = header.createCell(i);// 创建表头单元格
            cell.setCellStyle(headersStyle);// 设置单元格样式
            cell.setCellValue(headers[i]);//
        }
        return sheet;
    }

    /**
     * 设置单元格格式
     * 
     * @return
     */
    private CellStyle setCellStyle() {
        Font cellfont = wb.createFont();// 创建内容单元格的字体
        cellfont.setFontName("宋体");
        CellStyle contentStyle = wb.createCellStyle();
        contentStyle.setFont(cellfont);
        contentStyle.setWrapText(true);// 自动换行
        contentStyle.setAlignment(CellStyle.ALIGN_CENTER);
        contentStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        return contentStyle;
    }

    /**
     * 往数据单元格中填充值
     * @param datas
     * @param propertyNames
     * @param rowCounts
     * @param columnCounts
     * @param sheet
     * @param contentStyle
     */
    @SuppressWarnings("unchecked")
    private <T>void setDataCellValue(List<T> datas, String[] propertyNames, int rowCounts, int columnCounts,
                                     Sheet sheet, CellStyle contentStyle) {
        if (!datas.isEmpty()) {
            Class<T> clazz = (Class<T>) datas.get(0).getClass();
            for (int i = 1; i <= rowCounts; i++) {
                Row row = sheet.createRow(i);
                // 创建列
                for (int j = 0; j < columnCounts; j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellStyle(contentStyle);

                    try {
                        // 获取对象的属性描述器
                        PropertyDescriptor pd = new PropertyDescriptor(propertyNames[j], clazz);
                        // 得到属性的值
                        Object value = pd.getReadMethod().invoke(datas.get(i - 1));
                        // 往单元格中填充值
                        setCellValue(cell, value);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        }
    }

    /**
     * 自适应宽高
     * 
     * @param columnCounts
     * @param sheet
     */
    private void autoSize(int columnCounts, Sheet sheet) {
        // 每一列都自适应宽度，注意一定要写在这里--而且所有单元格必须已经设置了中文字体，
        // 如果在workbook一创建没有填充任何内容的时候就自适应宽度，就会根据第一行的每一列来设置宽度
        for (int i = 0; i < columnCounts; i++) {

            sheet.autoSizeColumn(i);// 自动设置每一行的宽度
            int columnWidth = sheet.getColumnWidth(i);

            if (columnWidth > defaultMaxColumnWidth * 2 * 256) {// 如果某一列的宽度大于20个字符的长度，就分行显示
                // 长度乘以2是为了解决纯数字列宽度不足会显示科学计数法问题，乘以256得到的数据才是excel真实列宽。
                sheet.setColumnWidth(i, defaultMaxColumnWidth * 2 * 256);

            }

        }
    }

    /**
     *  给每一个单元格设置值，对特殊的值进行调换
     * @param cell
     * @param value
     */
    private void setCellValue(Cell cell, Object value) {
        // 如果值为null，使用“-”代替
        if (value == null) {
            cell.setCellValue("-");
            return;
        }
        // 如果是字符串，直接设置值
        if (value instanceof String) {
            String strvalue = (String) value;
            cell.setCellValue(strvalue);
            return;
        }

        // 如果是数字，转换为Double形式
        if (value instanceof Number) {
            Number num = (Number) value;

            cell.setCellValue(num.doubleValue());

            return;
        }

        // 如果值是日期类型：先将日期转换为字符串
        if (value instanceof Date) {

            String strDateValue = defaultDateFormat.format(value);
            cell.setCellValue(strDateValue);
            return;
        }

        // 如果是布尔值，将布尔值转换成对应的中文：是、否
        if (value instanceof Boolean) {
            Boolean booleanvalue = (Boolean) value;
            if (booleanvalue) {
                cell.setCellValue("是");
            } else {
                cell.setCellValue("否");
            }
            return;
        }

    }

    /**
     * 将excel输入流写出到浏览器
     * 
     * @param request
     * @param response
     */
    public void wrtieToBrowser(HttpServletRequest request, HttpServletResponse response) {
        try {

            OutputStream fileOut = response.getOutputStream();
            response.reset();
            String encodedName = encodeDownloadFilename(wbName, request.getHeader("user-agent"));// 得到浏览器类型
            response.setHeader("content-disposition", "attachment;fileName=" + encodedName);// 对中文名称进行URL编码，针对火狐和其他浏览器有不同的处理方式
            response.setContentType(mime);
            wb.write(fileOut);
            // 设置输出形式
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对下载文件的名称进行url编码，防止中文乱码
     * 
     * @param filename
     * @param agent
     * @return
     * @throws IOException
     */
    private String encodeDownloadFilename(String filename, String agent) throws IOException {
        if (agent.contains("Firefox")) { // 火狐浏览器
            filename = "=?UTF-8?B?" + new BASE64Encoder().encode(filename.getBytes("utf-8")) + "?=";
        } else { // IE及其他浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        }
        return filename;
    }

    public Workbook getWb() {
        return wb;
    }

    public void setWb(Workbook wb) {
        this.wb = wb;
    }

    public SimpleDateFormat getDefaultDateFormat() {
        return defaultDateFormat;
    }

    public ApachePOIExcelUtil setDefaultDateFormat(SimpleDateFormat defaultDateFormat) {
        this.defaultDateFormat = defaultDateFormat;
        return this;
    }

 /**
  * 
  * @param in--Excel输入流
  * @param clazz--将Excel中的每一行数据，封装到这个字节码对应的实体中
  * @param columnIndex_propertyName_pair--Excel文件中列的索引与要封装到的实体的属性的对应关系
  * @param is_xlsx--是否是xlsx为后缀的Excel
  * @return  excel符合日期的格式的数字会被自动转换为Date类型，其他的数字会被转为Double，再有值则被转为String
  * @throws Exception 
  */
    public <T> List<Map<String,Object>> importExcel(InputStream in,Class<T> clazz,Map<Integer, String> columnIndex_propertyName_pair,boolean is_xlsx) throws Exception{
        //1、根据输入流创建workbook对象
        if (is_xlsx) {
            wb = new XSSFWorkbook(in);
        } else {
            wb = (Workbook) new HSSFWorkbook(in);
        }
        
        //2、解析workbook中的数据，封装到List集合中，并返回
        List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
            Sheet sheet = wb.getSheetAt(0);
            for (Row row: sheet) {//迭代出每一行row
                if(row.getRowNum()>=1){
                    Map<String, Object> map=new HashMap<String, Object>();
                    int columnIndex=0;
                    for (Cell cell: row) {//迭代出每一行中的每一个单元格Cell
                        String propertyName = columnIndex_propertyName_pair.get(columnIndex);
                        Object cellValue = null;
                        switch (cell.getCellType()) {//取出单元格中的值
                            case Cell.CELL_TYPE_BLANK:
                                return list;
                            case Cell.CELL_TYPE_STRING:
                                cellValue=cell.getRichStringCellValue().getString();
                                break;
                            case Cell.CELL_TYPE_NUMERIC:
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    cellValue=cell.getDateCellValue();
                                } else {
                                    cellValue=cell.getNumericCellValue();
                                }
                                break;
                            case Cell.CELL_TYPE_BOOLEAN:
                                cellValue=cell.getBooleanCellValue();
                                break;
                            case Cell.CELL_TYPE_FORMULA:
                                //cellValue=cell.getCellFormula();
                                try {  
                                    cellValue = String.valueOf(cell.getStringCellValue());  
                             } catch (IllegalStateException e) {  
                                 cellValue = String.valueOf(cell.getNumericCellValue());  
                             }  
                                break;
                            default:;
                        }
                        map.put(propertyName, cellValue); 
                        columnIndex++;
                    }
                    
                    list.add(map);
                }
            }
        return list;
    }
}
