package SeleniumFramework1.TestCases;	

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumFramework1.pageObjects.CheckoutPage;
import SeleniumFramework1.pageObjects.ThankYouPage;
import SeleniumFramework1.pageObjects.cartPage;
import SeleniumFramework1.pageObjects.goToOrdersPage;
import SeleniumFramework1.pageObjects.landingLoginPage;
import SeleniumFramework1.pageObjects.productCatelogue;
import SeleniumFramework2.TestComponents.CommonBaseTest;

public class usingTestNGConcepts1 extends CommonBaseTest{

// Copied same script from StandAloneTest4.java
// Here we are adding more TestNG Concepts like dependencies n all.	
	
	//String needThisProductToADDTOCART = "ZARA COAT 3"; 

	@Test(groups= {"errorHandeling", "toCheckDataProviderConcept"}, dataProvider="getData")
	public void submitOrder(String email, String password, String needThisProductToADDTOCART ) throws IOException
	{
	
		landingLoginPage landingLoginPage =llP.loginToApplication(email, password);
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
	}
	
// Creating one more @Test to check that our product is displayed in Order section or not.
// Below test is depend on above test right? Because, it should first successfully place order and then will display in Order section.
// Order page chan location declaring AbstractComponents class madhe lihilay, as that page is common for entire application.	
	
	@Test (dependsOnMethods= {"submitOrder"}, dataProvider="getData")
	public void orderHistory(String needThisProductToADDTOCART)
	{
		landingLoginPage landingLoginPage =llP.loginToApplication("mhatresayali@gmail.com", "Bruno@12345");
		goToOrdersPage orderPageProducts = landingLoginPage.goToOrdersPageAndCheck();
		Assert.assertTrue(orderPageProducts.VerifyOrderDisplay(needThisProductToADDTOCART));
		
		
	}
	
// Creating @Test for dataProvider.
// He data creation jara different ahe, soppa.	
	
	@DataProvider
	public Object[][] getData()
	{
		return new Object [][] {{"mhatresayali@gmail.com", "Bruno@12345", "ZARA COAT 3"}, {"Lanumhatre@gmail.com", "Bruno@12345", "ADIDAS ORIGINAL"}};
	}
	
	
	
	
	
		
	 }
		
		
		
	


