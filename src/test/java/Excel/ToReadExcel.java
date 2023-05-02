package Excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;



public class ToReadExcel {

	WebDriver driver;
	 XSSFWorkbook wb;
		XSSFSheet Sheet1;

	public static String readData(int row, int col, String sheetname) throws Throwable {
	 String path = System.getProperty("user.dir") +"\\TestData\\API Testcase.xlsx";
	File file = new File(path);
	 FileInputStream fis = new FileInputStream(file);
	 Workbook W = WorkbookFactory.create(fis);
	Sheet S = W.getSheet(sheetname);
	Row r = S.getRow(row);
	Cell s = r.getCell(col);
	String data = s.toString();
	return data;

	}
	public static void toIgnore() {
		  try (FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\josephstephenraja.p\\OneDrive - Optisol Business Solutions Private Limited\\Desktop\\rest assured\\JenkinsRestAssured\\TestData\\API Testcase.xlsx"));
		             Workbook workbook = WorkbookFactory.create(inputStream)) {
		            
		            // Select the sheet by name
		            Sheet sheet = workbook.getSheet("Sheet1");
		            
		            // Iterate through each row in the sheet
		            for (Row row : sheet) {
		                // Get the cell value in the "Skip" column
		                Cell skipCell = row.getCell(13);
		                String skipValue = skipCell.getStringCellValue();
		                
		                // Check if the "Skip" column has a value of "Y" or "Yes"
		                if (skipValue != null && (skipValue.equalsIgnoreCase("Y") || skipValue.equalsIgnoreCase("Yes"))) {
		                    // Skip this test script
		                    continue;
		                }
		                
		                // Execute the test script
		                // ...
		            }
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }

}
