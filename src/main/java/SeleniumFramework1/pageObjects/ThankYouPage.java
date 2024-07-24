package SeleniumFramework1.pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFramework2.AbstractComponents.AbstractComponents;

public class ThankYouPage extends AbstractComponents{

	
	
	
	WebDriver driver;
	public ThankYouPage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
	
	// To Grab page title
	@FindBy(css="h1.hero-primary")
	WebElement grabPageTitle;
	
	
	// Verify title.
	public String  verifyConfirmationMessage()
	{
		return grabPageTitle.getText();
		
	}
	
	
	
	
	
	
	
	
}
