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

public class usingJsonConcept extends CommonBaseTest{

// Copied same script from usingTestNGConcepts2.java
// In usingTestNGConcepts2 class we have learned how to use HashMap concept.
// There we have to create hashmap manually.
// Here we will learn How to read data from Json file. Create New Package > New > File > fileName.json
// If we are using json, then we need to create utility which will create hashmap automatically out of it. 
	

	@Test(groups= {"errorHandeling", "toCheckJsonConcept", "toCheckHashMapConcept"}, dataProvider="getData")
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
// We will create utility in dataReader class in same package where this json file is located.	
	
	@Test (dependsOnMethods= {"submitOrder"}, dataProvider="getData")
	public void orderHistory(String needThisProductToADDTOCART)
	{
		landingLoginPage landingLoginPage =llP.loginToApplication("mhatresayali@gmail.com", "Bruno@12345");
		goToOrdersPage orderPageProducts = landingLoginPage.goToOrdersPageAndCheck();
		Assert.assertTrue(orderPageProducts.VerifyOrderDisplay(needThisProductToADDTOCART));
		
		
	}
	
// Creating @Test for dataProvider.
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//SeleniumFramework3//data//PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
	
	
	
		
	 }
		
		
		
	


