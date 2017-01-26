package library;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Alerts {

	public static WebDriver driver;
	public static void popUpAlerts() throws InterruptedException, AWTException{
		
		
		//System.setProperty("webdriver.chrome.driver", "input//chromedriver.exe");
		//driver = new ChromeDriver();
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://192.168.110.157:4103/");
		driver.findElement(By.id("UserName")).sendKeys("admin");
		driver.findElement(By.id("Password")).sendKeys("123456");
		driver.findElement(By.id("Login")).click();
		waitUntilExistID("dropdownMenu1");
		/*driver.findElement(By.id("dropdownMenu1")).click();
		
		Thread.sleep(3000);
		
		Alert alert1 = driver.switchTo().alert();
		
		alert1.accept();*/
		
		

		/*Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);*/

		//driver.quit();
		
	}
	
	
	
	public static void waitUntilExistID(String element){
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element)));
		
	}
	
	
	public static void main(String[] args) throws InterruptedException, AWTException {
		
		popUpAlerts();
		
	}
	
	
}
