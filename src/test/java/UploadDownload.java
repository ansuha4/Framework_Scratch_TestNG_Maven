import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;

public class UploadDownload {
    static String filename = "C:\\Users\\GadapaAnusha\\Downloads\\download.xlsx";
    static String columName = "Price";
    static String fruitName = "Apple";
    int column =0;
    WebDriver driver;
    public void initialiseDriver() {
     driver = new ChromeDriver();
    driver.get("https://rahulshettyacademy.com/upload-download-test/index.html");
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    public static void main(String[] args) throws IOException {

        //download
        UploadDownload uploadDownload = new UploadDownload();
        uploadDownload.initialiseDriver();
        uploadDownload.downloadFile();
        uploadDownload.ChooseFile();
        uploadDownload.ExplicitWait();
        uploadDownload.WebTable();
        int col = uploadDownload.getColumnNumber(filename, columName);
        int roww = uploadDownload.rowNumber(filename, fruitName);
        Assert.assertTrue(updateCell(filename,roww,col,"599"));


        //edit excel
        //upload
        //wait for success message to show up
    }

    public void downloadFile(){
        driver.findElement(By.id("downloadButton")).click();
    }
    public WebElement ChooseFile(){
       WebElement upload = driver.findElement(By.xpath("//input[@type='file']"));
       upload.sendKeys("C:\\Users\\GadapaAnusha\\Downloads\\download.xlsx");
       return upload;
    }
    public String ExplicitWait() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'Toastify__')]//following-sibling::div"))).getText();
        Assert.assertEquals("Updated Excel Data Successfully.", text);
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[contains(@class,'Toastify__')]//following-sibling::div"))));
        System.out.println(text);
        return text;
    }
    public void WebTable(){
        String priceColumn = driver.findElement(By.xpath("//div[text()='Price']")).getDomAttribute("data-column-id");
        String actualPrice = driver.findElement(By.xpath("//div[text()='Apple']/parent::div/parent::div/div[@id='cell-"+priceColumn+"-undefined']")).getText();
        System.out.println(actualPrice);
        Assert.assertEquals("345", actualPrice);
    }
    public int getColumnNumber(String filename, String columnName) throws IOException {
        //edit Excel
        //getcolumn number of price  - get rownumber of apple - update excel with row,col

        FileInputStream fis = new FileInputStream(filename);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        int sheetsCount =workbook.getNumberOfSheets();
        for(int i =0; i<sheetsCount;i++){
        if(workbook.getSheetName(i).equalsIgnoreCase("Sheet1")){
          XSSFSheet  sheet =workbook.getSheetAt(i);
          Iterator<Row> rows = sheet.iterator();
            Row rowValue= rows.next();
            Iterator<Cell> cell =rowValue.cellIterator();
            cell.next();
            int k =1;
            while(cell.hasNext()){
                Cell value = cell.next();
                if(value.getStringCellValue().equalsIgnoreCase(columnName)){
                   column =k;
                }
                k++;
            }
            System.out.println(column);
          }

        }
        return column;

    }
    public int rowNumber(String filename, String fruitName) throws IOException {
        int rowIndex = -1;
        FileInputStream fis = new FileInputStream(filename);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        int sheetsCount =workbook.getNumberOfSheets();
        for(int i =0; i<sheetsCount;i++) {
            if (workbook.getSheetName(i).equalsIgnoreCase("Sheet1")) {
                XSSFSheet sheet = workbook.getSheetAt(i);
                Iterator<Row> rows = sheet.iterator();
                int p =1;

                Row firstRow = rows.next();
                while(rows.hasNext()){
                    Row rowValue = rows.next();
                    if(rowValue.getCell(column).getStringCellValue().equalsIgnoreCase(fruitName)){
                        Iterator<Cell> cellValue =rowValue.iterator();
                        while(cellValue.hasNext()){
                            Cell cell = cellValue.next();
                           if( cell.getCellType()== CellType.STRING && cell.getStringCellValue().equalsIgnoreCase(fruitName)){
                                rowIndex = p;
                           }
                        }
                    }
                }

            }
        }
        return rowIndex;
    }

    private static boolean updateCell(String filename, int roww, int col, String updatedValue) throws IOException {
        FileInputStream fis = new FileInputStream(filename);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        Row rowField =sheet.getRow(roww-1);
        Cell cellField =rowField.getCell(col-1);
        cellField.setCellValue(updatedValue);
        FileOutputStream fos = new FileOutputStream(filename);
        workbook.write(fos);
        workbook.close();
        fis.close();
        return true;

    }
}
