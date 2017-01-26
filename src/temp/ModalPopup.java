package temp;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ModalPopup {
	
	public static void main(String[] args) throws InterruptedException {

		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		DesiredCapabilities.internetExplorer().setCapability("ignoreProtectedModeSettings", true);
		System.setProperty("webdriver.ie.driver", "./BrowserDrivers/IEDriverServer.exe");
		WebDriver driver = new InternetExplorerDriver(caps);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("file:///C:/Users/laks3339.PLINTRON/Downloads/loads/loads/Customer%20Relationship%20Management.htm");
		Thread.sleep(3000);
		Object scrollBarPresent = ((JavascriptExecutor)driver).executeScript("return document.activeElement.getElementsByClassName('modal-footer')[0].innerHTML.trim()!=null;");
		System.out.println("Button :"+scrollBarPresent);
		
		Object textPresent = ((JavascriptExecutor)driver).executeScript("return document.activeElement.getElementsByClassName('modal-header')[0].innerHTML.trim()!=null;");
		System.out.println("Text :" +textPresent);
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		WebElement element = driver.findElement(By.xpath("//div[contains(text(), 'Ticket ID:')]"));
		System.out.println(jse.executeScript("return arguments[0].innerHTML;", element));
		
		System.out.println("CLick the Close button");
		element = driver.findElement(By.id("ErrmsgClose"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		System.out.println("CLicked the Close button");
		
		/*JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("document.getElementById('gbqfb').click();");*/
		
		//driver.quit();
		
	
	}}