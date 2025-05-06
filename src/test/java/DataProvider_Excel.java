import lombok.Data;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DataProvider_Excel {
    //multiple sets of data to our tests
    //array
    //5Sets of data as 5 arrays from data provider to your tests
    // then tests run 5 times - 5 different arrays
    DataFormatter df = new DataFormatter();
@Test(dataProvider = "driveTest")
public void TestDataProvider_Excel(String greeting, String message, int id) {
System.out.println(greeting+" "+message+" "+id);
}
@DataProvider(name ="driveTest")
public Object[][] getData(String methodName) throws IOException {
   // Object[][] data = {{"Hello","Text",1},{"Hi","Message",2},{"Greeting","Test",3}};
   // return data;
        //send the excel data to the object array [][] and then proceed
        FileInputStream fis = new FileInputStream("C:\\Users\\GadapaAnusha\\Desktop\\SeleniumNotes\\Excel_DataProvider_Integration.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet =workbook.getSheetAt(0);
        int rowCount = sheet.getPhysicalNumberOfRows();
        XSSFRow row =sheet.getRow(0);
        int columnCount =row.getLastCellNum();
        Object data[][]=new Object[rowCount-1][columnCount];
        for(int i=0;i<rowCount;i++){
            row =sheet.getRow(i+1);
            for(int j=0;j<columnCount;j++){
                XSSFCell cell =row.getCell(j);
                data[i][j]=df.formatCellValue(cell);
            }
        }
return data;
    }

}
