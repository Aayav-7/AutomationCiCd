package SeleniumFramework1.TestCases;	

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumFramework1.pageObjects.CheckoutPage;
import SeleniumFramework1.pageObjects.ThankYouPage;
import SeleniumFramework1.pageObjects.cartPage;
import SeleniumFramework1.pageObjects.landingLoginPage;
import SeleniumFramework1.pageObjects.productCatelogue;
import SeleniumFramework2.AbstractComponents.AbstractComponents;
import SeleniumFramework2.TestComponents.CommonBaseTest;
import SeleniumFramework2.TestComponents.RetryListenerClass;

public class ExtentReportConcept extends CommonBaseTest{

// Here we will learn how to implement extent report in real code.
// Copied code from ErrorValidationTest class.	
// Extent report code is common for all classes so we will declare it as globle in "ExtentReporterNG" and CommonBaseTest
	
	// Here we will take SS and report it using listener.
	// For listener we have created "ListenersToUseForExtentReportConcept" class in same project.
	// We have to run listener code from xml file
	// xml file created in 'Selenium Framework' project > takingSScodeUsingListener.xml file.	
	



	@Test(dataProvider="getData", retryAnalyzer=RetryListenerClass.class)
	public void loginErrorValidation(HashMap<String,String> input) throws IOException
	{
		landingLoginPage landingLoginPage =llP.loginToApplication(input.get("email"), input.get("password"));
		Assert.assertEquals("Incorrect email or password.",landingLoginPage.getErrorMessage());
		driver.close();
	}
	
	
	@Test(dataProvider="getData")
	public void productErrorValidation(HashMap<String,String> input) throws IOException
	{
		landingLoginPage landingLoginPage =llP.loginToApplication(input.get("email"), input.get("password"));
		String countryName = "India";
		productCatelogue catelogue = new productCatelogue(driver);
		List<WebElement> productsPresentOnPage = catelogue.productsPresentOnDashboardPage(); // Wait apply karun list bhetli.
		catelogue.addProductToCart(input.get("needThisProductToADDTOCART")); // Add product to cart. Wait for SuccessMessage and loading icon.
		cartPage cart = new cartPage(driver);
		cart.clickOnAddToCartButtonMethod();
		boolean verifyProductIsPresentinCart = cart.VerifyProductDisplay(input.get("needThisProductToADDTOCART"));
		Assert.assertTrue(verifyProductIsPresentinCart);
		cart.clickOnCheckoutButton();
    	CheckoutPage checkout = new CheckoutPage(driver);
    	checkout.performActionsOnCountryDropDown(countryName);
    	ThankYouPage thankyou = new ThankYouPage(driver);
    	String confirmationMessage = thankyou.verifyConfirmationMessage();
	    Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order.")); //Validate Title.		
	}
	
	@DataProvider
	public Object[][] getData()
	{
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", "mhatresayali@gmail..com");
		map.put("password", "Bruno@12345");
		map.put("needThisProductToADDTOCART", "ZARA COAT 3");
		
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email", "Lanumhatre@gmail.com");
		map1.put("password", "Bruno@12345");
		map1.put("needThisProductToADDTOCART", "ADIDAS ORIGINAL");
		
		return new Object[][] {{map},{map1}};
	}
		
	 }
		
		
		
	


