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

public class ErrorValidationTest extends CommonBaseTest{

// Here we will learn error validations.
// I will provide wrong email or password.	
// In application when you hit incorrect emailORpws, then error message comes.
// But when you inspect it shows quickly one class and disappears, so in this case you can inspect like 
	// RightClick > SelectorHub > Copy relative xpath.
// We will locate in landingLoginPage class.	
// Also it takes few seconds to appear, so will use wait mechanison to load and disappear	
// Also we are creating multiple @Tests here.	
	
	
	
	@Test(groups= {"errorHandeling"})
	public void loginErrorValidation() throws IOException
	{
	
//	landingLoginPage llP = launchApplication();
		landingLoginPage landingLoginPage =llP.loginToApplication("mhatresi@gmail.com", "Bruno@1245");
		Assert.assertEquals("Incorrect email or password.",landingLoginPage.getErrorMessage());

	}
	
	
	@Test
	public void productErrorValidation() throws IOException
	{
	
//	landingLoginPage llP = launchApplication();
		
		landingLoginPage landingLoginPage =llP.loginToApplication("mhatresayali@gmail.com", "Bruno@12345");
		String needThisProductToADDTOCART = "ZARA COAT 3"; 
		productCatelogue catelogue = new productCatelogue(driver);
		List<WebElement> productsPresentOnPage = catelogue.productsPresentOnDashboardPage(); // Wait apply karun list bhetli.
		catelogue.addProductToCart(needThisProductToADDTOCART); // Add product to cart. Wait for SuccessMessage and loading icon.
		cartPage cart = new cartPage(driver);
		cart.clickOnAddToCartButtonMethod();
		boolean verifyProductIsPresentinCart = cart.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(verifyProductIsPresentinCart);
		
		
	}
		
	 }
		
		
		
	


