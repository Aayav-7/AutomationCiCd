package SeleniumFramework1.pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFramework2.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents{

	
	
	
	WebDriver driver;
	public CheckoutPage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	By CountryDropdownList = By.cssSelector("section.ta-results");
	
	
	// Location of Country drop-down
	@FindBy(css="div.form-group input")
	WebElement CountryDropDown;
	// Click on India option from drop-down list.
	@FindBy(css="button.ta-item:nth-of-type(2)")
	WebElement clickOnINDIAoption;
	//Click on Place Order.
	@FindBy(css="a.action__submit")
	WebElement clickOnPlaceOrderButton;
	
	public void performActionsOnCountryDropDown(String countryName)
	{
		Actions action = new Actions(driver);
		action.sendKeys(CountryDropDown, countryName).build().perform();
		waitForElementToAppear(CountryDropdownList);
		clickOnINDIAoption.click();
		clickOnPlaceOrderButton.click();
	
	}
	
	
	
	
	
	
	
	
}
