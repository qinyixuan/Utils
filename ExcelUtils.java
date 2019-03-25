import org.apache.poi.hssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;

/**
 * ExcelUtils
 *
 * @author QinYixuan
 * @date 2018/8/29
 */
public class ExcelUtils {

    /**
     * 获取HSSFWorkbook
     *
     * @author QinYixuan
     * @date 2018/8/29
     */
    public static HSSFWorkbook getHSSFWorkbook(String sheetName, String[] title, String[][] values, HSSFWorkbook hssfWorkbook) {

        // 创建HSSFWorkbook
        if (hssfWorkbook == null) {
            hssfWorkbook = new HSSFWorkbook();
        }

        // 创建Sheet
        HSSFSheet hssfSheet = hssfWorkbook.createSheet(sheetName);

        // 创建表头
        HSSFRow hssfRow = hssfSheet.createRow(0);

        // 创建居中样式
        HSSFCellStyle hssfCellStyle = hssfWorkbook.createCellStyle();
        hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        // 创建列
        HSSFCell hssfCell = null;

        // 表头写入数据
        for (int i = 0; i < title.length; i++) {
            hssfCell = hssfRow.createCell(i);
            hssfCell.setCellValue(title[i]);
            hssfCell.setCellStyle(hssfCellStyle);
        }

        // 表格写入数据
        for (int i = 0; i < values.length; i++) {
            hssfRow = hssfSheet.createRow(i + 1);
            for (int j = 0; j < values[i].length; j++) {
                hssfCell = hssfRow.createCell(j);
                hssfCell.setCellValue(values[i][j]);
            }
        }

        return hssfWorkbook;
    }

    /**
     * 发送响应流方法
     *
     * @author QinYixuan
     * @date 2018/8/29
     */
    public static void setResponseHeader(HttpServletResponse httpServletResponse, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (Exception e) {
                e.printStackTrace();
            }
            httpServletResponse.setContentType("application/octet-stream;charset=ISO8859-1");
            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            httpServletResponse.addHeader("Pargam", "no-cache");
            httpServletResponse.addHeader("Cache-Control", "no-cache");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
