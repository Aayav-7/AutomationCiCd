package SeleniumFramework2.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumFramework1.pageObjects.goToOrdersPage;

public class AbstractComponents {

/* Some of the components there which we are going to use again and again.
 * Example: Window maximize, explicit wait, switching windows, taking SS
 * Consider this as a parent class.
 * Means Main Project > Multiple page objects (modules) > But reusable code for all modules.
 */ 

	
	// Constructor
	WebDriver driver;
	public AbstractComponents(WebDriver driver) 
	{
		this.driver=driver;
	}

	// If code starts from "driver.findElements(By.cssSelector("div.cartSection h3"))" then it's a 'webelement'.
	// But, if code starts from only "By.cssSelector("div.cartSection h3"))" then it's a 'ByLocator' with return type 'By'
	//So, if you find ByLocator, then while creating method for it you have to write "By findBy" in bracket (like explicit wait)
	// But if you find webelement, then while creating method for it you have to write "WebElement element" in bracket
	// When you copy-paste code for explicit wait then it gives error for driver.
	// But somewhere we have already declare driver in child class. We can fetch that from there.
	// As we are fetching driver from child class then we have to use Super() keyword there. So obviously constructor here.
	// Jithe jithe ha parent class extend kelay tithe tithe ata super use karava lagnar ahe.
	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementToDisappear(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement ordersHeader;
	
	public goToOrdersPage goToOrdersPageAndCheck()
	{
		ordersHeader.click();
		goToOrdersPage orderPageProducts = new goToOrdersPage(driver);
		return orderPageProducts;
	}
	
	
	
	
	
	
	
	
}
