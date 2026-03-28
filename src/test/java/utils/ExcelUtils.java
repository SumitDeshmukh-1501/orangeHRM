package utils;
import java.lang.Exception;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.imageio.stream.FileImageInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class ExcelUtils{

    //read Data for Dataprovider.
    public static Object[][] data;
    public static Object[][] ReadFromExcel(String sheetName) throws IOException {
        FileInputStream fis= new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/testData/testdata.xlsx");
        XSSFWorkbook wk=new XSSFWorkbook(fis);
        XSSFSheet sheet = wk.getSheet(sheetName);
        int rowCount=sheet.getLastRowNum();
        int cellCount=sheet.getRow(0).getLastCellNum();
        System.out.println(rowCount);
        data=new Object[rowCount][cellCount];
        for(int i=1; i<=rowCount; i++){
            for(int j=0; j<cellCount; j++){
                data[i-1][j]=sheet.getRow(i).getCell(j).getStringCellValue();
            }
        }

        System.out.println(data.length);
        return data;
    }


}
