package objekti;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Home {

	public static final String URL = "https://www.saucedemo.com/";
	
	public static void clickLogBtn(WebDriver driver) {
		driver.findElement(By.name("submit")).click();
	}
}
