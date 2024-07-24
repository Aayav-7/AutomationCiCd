package SeleniumFramework1.TestCases;
import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("mhatresayali@gmail.com"); //Enter Username
		driver.findElement(By.id("userPassword")).sendKeys("Bruno@12345"); // Enter Password.
		driver.findElement(By.id("login")).click(); // Click on Login button.
		String requiredProductName = "ZARA COAT 3"; // Ha product cart madhe add karaychay.
		
	// Wait apply karaycha ki sarva products yetil. Required product pan ala pahije.
	// So add explicit wait for visibility of our required product.
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mb-3")));
		
	// Okay, now I want to click on perticular product. Product location or ID might get changed.
	// So, first we will grab all products in a list. Then will click on product which matches our expectations.
	// Instead of for loop, we will go with JavaStreams.	
	// We could use productsPresentOnPage.stream().filter(requiredProduct->requiredProduct.getText().equalsIgnoreCase("ZARA COAT 3"));
	// But if you inspect that code, that "ZARA COAT 3" is not easily visible, we need to dig down till end and then you will find it.
	// So, we use findElement code	
		List<WebElement> productsPresentOnPage =  driver.findElements(By.cssSelector("div.mb-3")); // Many sub classes separated by space, we used one of them. Tagname.className
		WebElement requiredProduct = productsPresentOnPage
		.stream()
		.filter(Product->Product.findElement(By.cssSelector("b"))
		.getText().equals(requiredProductName))
		.findFirst()
		.orElse(null);
		
	// Now click on 'Add to Cart'
	// That button is also deep inside somewhere.
	// We already for our required product in "requiredProduct"
	// In that we will find out button by using CSS (Parent to child traverse) and new concept "last-of-type" means select last button.
	// Paren to Child karat "div>div>div>button.w-10" ithparyant ala ki colon dyaycha ani "last-of-type" select karaycha.
		requiredProduct.findElement(By.cssSelector("div>div>div>button.w-10:last-of-type")).click();
		
	// Toast Container.
	// When you click on Add to Cart, then success message appears called as 'Toast Container'.
	// Add implicit wait for success message to appear.	
	// When you click on Add to Cart, then loading window is there.
	// Add implicit wait for loading window to disappar.
	// Loding icon visible hoiparyant thambaychay and nantr disappear hoiparyant.	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("button[routerlink*='dashboard/cart']")).click(); //Click on Add to Cart. Used CSS point5 technique.
				
	// Now, we have to check whether required product is added to cart or not.
	// When you inspect product name, then it comes under h3 tag.
	// In selector hub, when you search h3, then it highlights page title also along with cart item title.	
	// We have used CSS 7 technique here.
	// We will grab all items which are present in cart.
	// Again if required product is present there, then assertion will pass and click on Checkout. (CSS 7)	
		
		List<WebElement> itemsPresentInCart = driver.findElements(By.cssSelector("div.cartSection h3"));
		Boolean isItemMatches = itemsPresentInCart.stream()
		.anyMatch(eachCartProduct->eachCartProduct.getText().equalsIgnoreCase(requiredProductName));
		Assert.assertTrue(isItemMatches);
		driver.findElement(By.cssSelector("li.totalRow button")).click();
		
	// We will use action class here.
		WebElement countryDropDown = driver.findElement(By.cssSelector("div.form-group input")); //Locate drop-down.
		Actions action = new Actions(driver); //Invoke action class.
		action.sendKeys(countryDropDown, "India").build().perform(); // Send data to countryDropDown
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section.ta-results"))); //Wait until auto-suggestion section fully shows data.
	// New Locator Concept. TagName.ClassName mg colon dyaycha and nth-of-type select karaycha, aapla 2nd number la data ahe.
		driver.findElement(By.cssSelector("button.ta-item:nth-of-type(2)")).click(); //India var click karaycha.
		driver.findElement(By.cssSelector("a.action__submit")).click(); //Click on Place Order.
		String confirmationMessage = driver.findElement(By.cssSelector("h1.hero-primary")).getText(); //Grab title.
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order.")); //Validate Title.
		driver.close(); //Close Browser.
		
		
		
		
		
		
		
		
	}

}
