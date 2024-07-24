package SeleniumFramework1.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFramework2.AbstractComponents.AbstractComponents;

public class cartPage extends AbstractComponents{

	
	
	
	WebDriver driver;
	public cartPage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
	// Click on Add to Cart button
	@FindBy(css="button[routerlink*='dashboard/cart']")
	WebElement clickOnAddToCartButton;
	
	public void clickOnAddToCartButtonMethod()
	{
		clickOnAddToCartButton.click();
	}
	
	
	// List all products when went to AddToCart page.
	// We are using boolean for matching product name.
	@FindBy(css="div.cartSection h3")
	List <WebElement> itemsPresentInCart; 
	@FindBy(css="li.totalRow button")
	WebElement checkoutButton;
	
	public Boolean VerifyProductDisplay(String needThisProductToADDTOCART)
	{
		Boolean match = itemsPresentInCart
				        .stream()
				        .anyMatch(eachCartProduct->eachCartProduct
				        .getText().equalsIgnoreCase(needThisProductToADDTOCART));
		return match;
		
	}
	
	public cartPage clickOnCheckoutButton()
	{
		checkoutButton.click();
		cartPage cart = new cartPage(driver);
		return cart;
	}
	
	
	
	
	
	
}
