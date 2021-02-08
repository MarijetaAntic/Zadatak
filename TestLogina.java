package kontrolni3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import objekti.Login;

public class TestLogina {

	public static WebDriver driver ;
	
	@BeforeClass
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver","C:\\Chrome driver ya selenium\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
	}
	
	@Test
	public void TestLogin() {
		File f = new File("data.xlsx");
		
		try {
			InputStream inp = new FileInputStream(f);
			XSSFWorkbook wb = new XSSFWorkbook(inp);
			Sheet sheet = wb.getSheetAt(0);
			
			SoftAssert sa = new SoftAssert();
			
			int brRed = sheet.getLastRowNum();
			
			for(int i = 1 ; i<=brRed; i++) {
				Row row = sheet.getRow(i);
				
				Cell cell1 = row.getCell(0);
				Cell cell2 = row.getCell(1);
				
				String username = cell1.toString();
				String password = cell2.toString();
				
				driver.navigate().to(Login.URL);
				Login.inputUsername(driver, username);
				Login.inputPassword(driver, password);
				Login.login(driver);
				
				String actual = driver.getCurrentUrl();
				String expected = "https://www.saucedemo.com/inventory.html";
				
				sa.assertEquals(actual, expected);
			}
			
			wb.close();
	
			
		} catch (IOException e) {
			System.out.println("Nije pronadjen fajl");
			e.printStackTrace();
		}
		
		
	}
	
}
