package excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by LiLi on 17/1/5.
 */
public class ExcelUtil {
    // basics tips refer to ExcelGuide
    // in daily development, we always write a ExcelUtil
    private static final String EXCEL_XSL = ".xsl";
    private static final String EXCEL_XSLX = ".xslx";

    /**
     * 根据文件后缀，自适应上传文件的版本
     * @param in
     * @param fileName
     * @return
     * @throws IOException
     */
    public Workbook getWorkbook(InputStream in, String fileName) throws IOException{
        Workbook workbook = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (EXCEL_XSL.equals(fileType)) {
            workbook = new HSSFWorkbook();
        }
        else if (EXCEL_XSLX.equals(fileType)) {
            workbook = new XSSFWorkbook();
        }
        else {
            throw new IOException("文件格式有误");
        }
        return workbook;
    }

    /**
     * 从http请求获得Excel文件
     * @param excelName
     * @param multipartRequest
     * @return
     */
//    public MultipartFile getFileFromHttpRequest(String excelName, MultipartHttpServletRequest multipartRequest) {
//        if (null == excelName) {
//            return multipartRequest.getFile(excelName);
//        }
//        Map<String, MultipartFile> multipartFileMap =  multipartRequest.getFileMap();
//        if (CollectionUtils.isEmpty(multipartFileMap)) {
//            return null;
//        }
//        else {
//            return multipartFileMap.values().iterator().next();
//        }
//
//    }

}
