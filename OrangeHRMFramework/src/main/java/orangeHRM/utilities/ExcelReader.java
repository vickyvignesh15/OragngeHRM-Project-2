package orangeHRM.utilities;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelReader {
	
	@DataProvider(name="GetData")
	public static String[][] getExcelData(Method m) throws IOException {
		String sheetName=m.getName();
		String fileLocation = ".\\src\\test\\resources\\TestData\\TestData.xlsx";
		XSSFWorkbook wb = new XSSFWorkbook(fileLocation);

		XSSFSheet sheet = wb.getSheet(sheetName);

		int totalRows = sheet.getLastRowNum();
		int totalCol = sheet.getRow(1).getLastCellNum();
//		System.out.println(totalRows);
//		System.out.println(totalCol);
		
		DataFormatter format = new DataFormatter();

		String[][] testData = new String[totalRows][totalCol];
		
		for(int i=1; i<=totalRows; i++) {
			for(int j=0; j<totalCol; j++) {
				testData[i-1][j] = format.formatCellValue(sheet.getRow(i).getCell(j));
//				System.out.println(testData[i-1][j]);
			}
		}
		wb.close();
		return testData;
		
		
	}

}
