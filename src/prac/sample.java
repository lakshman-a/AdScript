package prac;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class sample {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "D:\\D Drive\\AUTOMATION\\WORKSPACE\\CRM\\Selenium_CRM_Framework\\BrowserDrivers\\chromedriver.exe");
		RemoteWebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get("http://192.168.110.157:4100/");
		driver.findElement(By.id("UserName")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123456");
		driver.findElement(By.id("Login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("")));
		
		
		Thread.sleep(5000);
		
		driver.close();
	
		
		//driver.get("http://keenthemes.com/preview/metronic/theme/admin_1/ui_tree.html");
		
		/*WebElement dragElement=driver.findElement(By.xpath("//a[@id='j3_7_anchor']"));
		WebElement dropElement=driver.findElement(By.xpath("//a[@id='j3_1_anchor']"));
		
		Actions action = new Actions(driver);
		action.clickAndHold(dragElement)
		.moveToElement(dropElement)
		.build()
		.perform();
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("")));
		
		
		Actions action = new Actions(driver);
		action.dragAndDrop(dragElement, dropElement)
		.build()
		.perform();*/
		
		System.out.println("Completed");
//		drive
		
	}
	
}
