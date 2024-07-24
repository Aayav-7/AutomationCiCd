package SeleniumFramework1.TestCases;	

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import SeleniumFramework1.pageObjects.CheckoutPage;
import SeleniumFramework1.pageObjects.ThankYouPage;
import SeleniumFramework1.pageObjects.cartPage;
import SeleniumFramework1.pageObjects.landingLoginPage;
import SeleniumFramework1.pageObjects.productCatelogue;
import SeleniumFramework2.TestComponents.CommonBaseTest;

public class StandAloneTest4 extends CommonBaseTest{

// Few more modifications to StandAloneTest3 class.
// Using @Before and after test in CommonBaseTest class.
// Due to which commenting 	landingLoginPage llP = launchApplication(); and driver.close(); code
	
	
	@Test
	public void submitOrder() throws IOException
	{
	
//	landingLoginPage llP = launchApplication();
		
		landingLoginPage landingLoginPage =llP.loginToApplication("mhatresayali@gmail.com", "Bruno@12345");
		String needThisProductToADDTOCART = "ZARA COAT 3"; 
		String countryName = "India";
		productCatelogue catelogue = new productCatelogue(driver);
		List<WebElement> productsPresentOnPage = catelogue.productsPresentOnDashboardPage(); // Wait apply karun list bhetli.
		catelogue.addProductToCart(needThisProductToADDTOCART); // Add product to cart. Wait for SuccessMessage and loading icon.
		cartPage cart = new cartPage(driver);
		cart.clickOnAddToCartButtonMethod();
		boolean verifyProductIsPresentinCart = cart.VerifyProductDisplay(needThisProductToADDTOCART);
		Assert.assertTrue(verifyProductIsPresentinCart);
		cart.clickOnCheckoutButton();
    	CheckoutPage checkout = new CheckoutPage(driver);
    	checkout.performActionsOnCountryDropDown(countryName);
    	ThankYouPage thankyou = new ThankYouPage(driver);
    	String confirmationMessage = thankyou.verifyConfirmationMessage();
	    Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order.")); //Validate Title.
//	driver.close();
		
	}
		
	 }
		
		
		
	


