import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Excel_DataDriven {
    public ArrayList<String> getData(String testcaseName) throws IOException {
        ArrayList<String> data = new ArrayList<String>();
        //this accepts fileinputstream argument
        FileInputStream fis = new FileInputStream("C:\\Users\\GadapaAnusha\\Desktop\\SeleniumNotes\\ExcelWorkBook_practice.xlsx");

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        // this class, object needs to know how to access hte work book, excel path, pass into this
        int sheetsCount = workbook.getNumberOfSheets();
        for (int i = 0; i < sheetsCount; i++) {
            if (workbook.getSheetName(i).equals("testData")) {
                XSSFSheet sheet = workbook.getSheetAt(i);
                Iterator<Row> rows = sheet.iterator();
                Row firstrow = rows.next();
                Iterator<Cell> cell = firstrow.cellIterator();//row is collection of cells
                cell.next();
                int k = 0;
                int column = 0;
                while (cell.hasNext()) {
                    Cell value = cell.next();
                    if (value.getStringCellValue().equalsIgnoreCase("TestCases")) {
                        //desired column
                        column = k;
                    }
                    k++;
                }
                System.out.println(column);
                while (rows.hasNext()) {
                    Row r = rows.next();
                    if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName)) {
                        Iterator<Cell> cv = r.cellIterator();
                        while (cv.hasNext()) {
                            Cell c = cv.next();
                            if(c.getCellType()== CellType.STRING) {
                                data.add(c.getStringCellValue());
                            }
                            else{
                                NumberToTextConverter.toText(c.getNumericCellValue());

                            }
                        }
                    }
                }
            }
        }
        //identify the test cases column by scanning entire column
        //once column is identified scan entire column to identify the Purchase testcase row
        //after you grab purchase test case row, pull the corresponding data


        return data;
    }


    public static void main(String[] args) throws IOException {

    }
}

