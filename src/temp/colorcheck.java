package temp;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class colorcheck {
	
	public static void main(String[] args) throws InterruptedException {

		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		DesiredCapabilities.internetExplorer().setCapability("ignoreProtectedModeSettings", true);
		System.setProperty("webdriver.ie.driver", "./BrowserDrivers/IEDriverServer.exe");
		WebDriver driver = new InternetExplorerDriver(caps);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("file:///C:/Users/laks3339.PLINTRON/Desktop/Customer%20Relationship%20Management.html");
		Thread.sleep(3000);
		
		System.out.println("Getting the element");
		//WebElement element = driver.findElement(By.xpath("//div[@class='col-md-12 errorMessage']"));
		//WebElement element = driver.findElement(By.xpath("//div[@id='dvVoucherDetails']//*//*//div[@class='col-md-12 errorMessage']"));
		WebElement element = driver.findElement(By.xpath("//div[contains(text(), 'invalid')]"));
		
		//div[@id='dvVoucherDetails']//*//*//div[@class='col-md-12 errorMessage']
		System.out.println("The color of the element is : "+element.getText());
		
		System.out.println("The color of the element is : "+element.getCssValue("color"));
		
		
		/*
		System.out.println(jse.executeScript("return arguments[0].innerHTML;", element));
		
		System.out.println("CLick the Close button");
		element = driver.findElement(By.id("ErrmsgClose"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		System.out.println("CLicked the Close button");*/
		
		/*JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("document.getElementById('gbqfb').click();");*/
		
		//driver.quit();
		
	
	}}