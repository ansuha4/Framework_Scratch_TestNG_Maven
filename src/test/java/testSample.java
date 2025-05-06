import java.io.IOException;
import java.util.ArrayList;

public class testSample {
    public static void main(String[] args) throws IOException {
        Excel_DataDriven excel = new Excel_DataDriven();
        ArrayList<String> data = excel.getData("Add Profile");
        System.out.println(data.get(0));
    }
}
