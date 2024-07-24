package SeleniumFramework1.pageObjects;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import SeleniumFramework2.AbstractComponents.AbstractComponents;

public class productCatelogue extends AbstractComponents {

// We have created all code in StandAloneTest2 class.
// Page object means we are separating reusable locators into different-different classes.
// This class is regarding all locators of Login page.
// Initially it will give error for driver, so to resolve issue we have to create constructor.
	
	WebDriver driver;
	public productCatelogue(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	   // To find product list.
	   @FindBy(css="div.mb-3")
	   List <WebElement> productsPresentOnPage; //It's WebElements so we have to use List.
	   
	   //For Explicit wait > Invisibility of element located.
	   @FindBy(css=".ng-animating")
	   WebElement pageLoadingIcon;
	   
	   By successMessagePopUP = By.cssSelector("#toast-container");
	   By loadingIcon = By.cssSelector("#toast-container");
	   By addToCartLimitedScope = By.cssSelector("div>div>div>button.w-10:last-of-type");
	   By productsPresentOnPageLocator = By.cssSelector("div.mb-3");
	   // Varchi line tevhach use karave lagtat jevha ByLocator concept asto (explained in AbstractComponents class)
	   //Before getting all products present on page, we have to wait, so using method from Abstract class.
	   // PageFactory is only for driver.findelements.
	   // But explicit wait is directly by.id, so we have to pass arguments
	   // List sathi method cha return type List yenar.
	   public  List<WebElement> productsPresentOnDashboardPage()
	   {
		   waitForElementToAppear(productsPresentOnPageLocator);
		   return productsPresentOnPage;	 
	   }
	   
	   
	   // Now creating method for List > Stream > Filter > Get name of product > If matches with declared name then OKAY.
	   // From productsPresentOnDashboardPage() this method we are getting list of products. So directly we are using that.
	   public WebElement requiredProduct(String requiredProductName)
	   {
		   WebElement requiredProduct = productsPresentOnDashboardPage()
					.stream()
					.filter(Product->Product.findElement(By.cssSelector("b"))
					.getText().equals(requiredProductName))
					.findFirst()
					.orElse(null);
		   return requiredProduct;
	   }
	   
	   
	   // Creating method to add to cart.
	   // We are limiting search scope from driver. to requiredProduct.
	   // For this there is no PageFactory, we have to treat it like wait By FindBy.
	   // I have declared by mechanism at the top 
	   // And here first get that product name from above method and then use it here in this method.
	   public void addProductToCart(String needThisProductToADDTOCART)
	   {
			WebElement requiredProduct = requiredProduct(needThisProductToADDTOCART);
		   requiredProduct.findElement(addToCartLimitedScope).click();
		   waitForElementToAppear(successMessagePopUP);
		   waitForElementToDisappear(pageLoadingIcon);
	   }
	  
	   

	   
	
	
	
	
	
	
	
	
	
}
