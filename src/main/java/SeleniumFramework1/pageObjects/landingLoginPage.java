package SeleniumFramework1.pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import SeleniumFramework2.AbstractComponents.AbstractComponents;

public class landingLoginPage extends AbstractComponents{

// We have created all code in StandAloneTest2 class.
// Page object means we are separating reusable locators into different-different classes.
// This class is regarding all locators of Login page.
// Initially it will give error for driver, so to resolve issue we have to create constructor.
	
	WebDriver driver;
	public landingLoginPage(WebDriver driver)
	{
	// Here we write initialize code.
	// This code will execute at the start before executing code of this class.
	// StandAloneTest2 class madhe object create karaycha.
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	// From here we will declare our locators.
	// WebElement userEmailAddress =  driver.findElement(By.id("userEmail"));
	// This is one way to declare but there is one more good way as below, called as Page Factory Designing Pattern.
	// But it doesn't know anything about driver, so we have to declare it in construcor.
	   @FindBy(id="userEmail")
	   WebElement userEmailAddress;
	   
	   @FindBy(id="userPassword")
	   WebElement userEPassword;
	   
	   @FindBy(id="login")
	   WebElement clickOnLoginButton;
	   
	   @FindBy(css="[class*='flyInOut']")
	   WebElement errorValidationMessage;
	   
	   
	// Here we will declare action method.
	// We are performing three actions: Go to URL, Enter UN, Enter PW and click on Login.	
	// First we will create method for URL.   
	// This is generic class so we can not directly give UN and PW here. So, we will declare parameters here.   
	   
	   public void navigateToUrl()
	   {
		   driver.get("https://rahulshettyacademy.com/client");
	   }
	   
	   
	   public landingLoginPage loginToApplication(String email, String password)
	   {
		   userEmailAddress.sendKeys(email);
		   userEPassword.sendKeys(password);
		   clickOnLoginButton.click();
			landingLoginPage landingLoginPage = new landingLoginPage(driver);
			return landingLoginPage;
	 }
	   
	   public String getErrorMessage()
	   {
		   waitForWebElementToAppear(errorValidationMessage);
		   return errorValidationMessage.getText();
	   }
	   
	
	
	
	
	
	
	
	
	
}
