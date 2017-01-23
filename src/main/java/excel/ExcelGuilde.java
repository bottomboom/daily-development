package excel;

import com.microsoft.schemas.office.visio.x2012.main.CellType;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.WorkbookUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

;

/**
 * Created by LiLi on 17/1/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-beanConfig.xml"})
public class ExcelGuilde {
    /**
     *  .xls 2003 版本以下，HSSFWorkbook
     *  .xlsx 2007 版本以上，XSSFWorkbook
     *
     *  RichTextString 和 String 的区别：前者可以在文本上增加字体颜色、形状等效果，而后者纯文本
     *
     *  wb.getCreationHelper.createDataFormat  和 wb.createDataFormat 区别：
     *  个人认为是没有区别的，CreationHelper正如其名，还可以创建RichTextString,ClientAnchor，Hyperlink
     */
    //1. new WorkBook
    @Test
    public void newWorkBook() {
        Workbook workbook = new HSSFWorkbook();
        FileOutputStream fos = null;
        writeOutputToFile(workbook, fos);
    }

    //2.new sheet
    @Test
    public void newSheet() {
        Workbook workbook = new HSSFWorkbook();//or XSSFWorkbook
        String safeSheetName = WorkbookUtil.createSafeSheetName("[first]sheet");//replace invalid character with a space
        workbook.createSheet(safeSheetName);
        safeSheetName = WorkbookUtil.createSafeSheetName("[second]sheet");
        workbook.createSheet(safeSheetName);

        FileOutputStream fos = null;
        writeOutputToFile(workbook, fos);
    }

    //3.creating cells
    @Test
    public void createCells() {
        Workbook workbook = new HSSFWorkbook();//or XSSFWorkbook
        CreationHelper creationHelper = workbook.getCreationHelper();
        String safeSheetName = WorkbookUtil.createSafeSheetName("new sheet");
        Sheet sheet = workbook.createSheet(safeSheetName);
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue(1);
        row.createCell(1).setCellValue(1.2);
        row.createCell(2).setCellValue(creationHelper.createRichTextString("This is a String."));
        row.createCell(3).setCellValue(true);

        FileOutputStream fos = null;
        writeOutputToFile(workbook,fos);
    }

    //4. creating Date Cells
    @Test
    public void createDateCells() {
        Workbook workbook = new HSSFWorkbook();
        CreationHelper creationHelper = workbook.getCreationHelper();
        Sheet sheet = workbook.createSheet("new sheet");

        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue(new Date());

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("MM/dd/yy HH:mm:ss"));
        row.createCell(1).setCellValue(new Date());
        row.getCell(1).setCellStyle(cellStyle);

        row.createCell(2).setCellValue(Calendar.getInstance());
        row.getCell(2).setCellStyle(cellStyle);

        FileOutputStream fos = null;
        writeOutputToFile(workbook, fos);

    }

    //5. working with different types of cells
    @Test
    public void differentTypesOfCells() {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("new sheet");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue(new Integer(1));
        row.createCell(1).setCellValue(1.1);
        row.createCell(2).setCellValue(new Date());
        row.createCell(3).setCellValue(Calendar.getInstance());
        row.createCell(4).setCellValue("a String.");
        row.createCell(5).setCellValue(true);
        row.createCell(6).setCellType(CellType.EQUAL);

        FileOutputStream fos = null;
        writeOutputToFile(workbook,fos);
    }

    //6. various alignment options
    @Test
    public void alignmentOptions() {
        Workbook workbook = new HSSFWorkbook();
        Row row = workbook.createSheet("new sheet").createRow(2);

        createCell(workbook,row, 0, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_BOTTOM);
        createCell(workbook, row, 1, CellStyle.ALIGN_CENTER_SELECTION, CellStyle.VERTICAL_CENTER);
        createCell(workbook, row, 2, CellStyle.ALIGN_FILL, CellStyle.VERTICAL_JUSTIFY);
        createCell(workbook, row, 3, CellStyle.ALIGN_GENERAL, CellStyle.VERTICAL_TOP);
        createCell(workbook, row, 4, CellStyle.ALIGN_JUSTIFY, CellStyle.VERTICAL_BOTTOM);
        createCell(workbook, row, 5, CellStyle.ALIGN_LEFT, CellStyle.VERTICAL_BOTTOM);
        createCell(workbook, row, 6, CellStyle.ALIGN_RIGHT, CellStyle.VERTICAL_CENTER);

        FileOutputStream fos = null;
        writeOutputToFile(workbook, fos);
    }

    private void createCell(Workbook workbook, Row row, int column, short alignmnet, short vertiAlignment) {
        row.createCell(column).setCellValue("align");
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(alignmnet);
        cellStyle.setVerticalAlignment(vertiAlignment);
        row.getCell(column).setCellStyle(cellStyle);
    }

    //7. iterator at rows and cells
    /**
     * for (Sheet sheet : workbook) {
     *      for (Row row : sheet) {
     *          for (Cell cell : row) {
     *              //do something here
     *           }
     *       }
     *    }
     */
    @Test
    public void iterator() {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.getSheetAt(0);
        int rowStart =  Math.min(15, sheet.getFirstRowNum());
        int rowEnd = Math.max(1400, sheet.getLastRowNum());

        for (int i = rowStart; i < rowEnd; i++) {
            Row row = sheet.getRow(i);
            if (null == row) {
                continue;
            }
            int lastColumn = Math.max(row.getLastCellNum(), 50);
            for (int col = 0; col < lastColumn; col++) {
                Cell cell = row.getCell(col, Row.RETURN_BLANK_AS_NULL);
                if (null == cell) {
                    //The spreadsheet is empty
                }
                else {
                    //Do something useful with the cell's content
                }
            }

        }

    }

    //8. Getting the cell content
    // to get the content of the cell, you first need to know what kind of cell it is
    @Test
    public void getCellContent() throws IOException{
        FileInputStream fis = new FileInputStream("workbook.xls");
        Workbook wb = new HSSFWorkbook(fis);

        DataFormatter formatter = new DataFormatter();
        Sheet sheet = wb.getSheetAt(0);

        for (Row row : sheet) {
            for (Cell cell : row) {
                CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
                System.out.println(cellRef.formatAsString());
                System.out.println("-");

                //get the text that appears in the cell by getting the cell value and applying any date format
                //see the print result, this one looks better than the next
                String text = formatter.formatCellValue(cell);
                System.out.println(text);

                //alternatively, get the value and format it yourself
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING :
                        System.out.println(cell.getRichStringCellValue().toString());//cell.getStringCellValue()
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            System.out.println(cell.getDateCellValue());
                        }
                        else {
                            System.out.println(cell.getNumericCellValue());
                        }
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        System.out.println(cell.getBooleanCellValue());
                        break;
                    case Cell.CELL_TYPE_FORMULA:
                        System.out.println(cell.getCellFormula());
                        break;
                    case Cell.CELL_TYPE_BLANK:
                        System.out.println();
                        break;
                    default:
                        System.out.println();

                }
            }
        }
        fis.close();
    }

    private void writeOutputToFile(Workbook workbook, FileOutputStream fos) {
        try {
            fos = new FileOutputStream("workbook.xls");
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //format的几点疑惑
    /**
     * cell.getCellStyle.getFormat 或者 cell.getCellStyle.getFormatString  获得单元格Style类型
     * formatter.formatCellValue 直接取出了格式化的数据
     */
    @Test
    public void dataFormatTest() throws IOException{
        FileInputStream fis = new FileInputStream("workbook.xls");
        Workbook wb = new HSSFWorkbook(fis);
        Sheet sheet = wb.getSheetAt(0);
        Row row = sheet.getRow(0);
        DataFormatter formatter = new DataFormatter();
        for (Cell cell : row) {
            short format = cell.getCellStyle().getDataFormat();
            String formatString = cell.getCellStyle().getDataFormatString();

            String formatString2 = formatter.formatCellValue(cell);
            System.out.println("format:" + format + "-----formatString:" + formatString + "------formatter: " + formatString2);
        }
        System.out.println(new Date());
    }

    @Test
    public void test() throws IOException {
//        NumberFormat num = NumberFormat.getPercentInstance();
//        num.setMaximumIntegerDigits(3);
//        num.setMaximumFractionDigits(1);
//        double csdn = 0.23456;
//        System.out.println(num.format(csdn));

        FileOutputStream fos = new FileOutputStream("percent.xls");
        Workbook wb = new HSSFWorkbook();
        CellStyle cellStyle = wb.createCellStyle();
        Cell cell = wb.createSheet().createRow(0).createCell(0);
        cell.setCellValue(0.4329);
        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%"));
        cell.setCellStyle(cellStyle);
        wb.write(fos);
    }

}
