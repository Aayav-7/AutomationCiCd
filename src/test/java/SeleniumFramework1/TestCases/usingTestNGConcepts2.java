package SeleniumFramework1.TestCases;	

import java.io.IOException;
import java.util.HashMap;
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
import SeleniumFramework2.TestComponents.RetryListenerClass;

public class usingTestNGConcepts2 extends CommonBaseTest{

// Copied same script from usingTestNGConcepts1.java
// Here we will learn HashMap concept.
// Here we are using @DataProvider for 3 values(email, password and itemToAddToCart)
// But what id want to use @DataProvider for 20 values? Then above technique is not useful, instead use Hashmap concept.	
	

	@Test(groups= {"errorHandeling", "toCheckDataProviderConcept", "toCheckHashMapConcept"}, dataProvider="getData")
	public void submitOrder(HashMap<String,String> input) throws IOException
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
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", "mhatresayali@gmail.com");
		map.put("password", "Bruno@12345");
		map.put("needThisProductToADDTOCART", "ZARA COAT 3");
		
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email", "Lanumhatre@gmail.com");
		map1.put("password", "Bruno@12345");
		map1.put("needThisProductToADDTOCART", "ADIDAS ORIGINAL");
		
		return new Object[][] {{map},{map1}};
	}
	
	
	
	
	
		
	 }
		
		
		
	


