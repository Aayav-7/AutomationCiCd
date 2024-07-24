package SeleniumFramework1.TestCases;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumFramework1.pageObjects.CheckoutPage;
import SeleniumFramework1.pageObjects.ThankYouPage;
import SeleniumFramework1.pageObjects.cartPage;
import SeleniumFramework1.pageObjects.landingLoginPage;
import SeleniumFramework1.pageObjects.productCatelogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	// Creating object of landingLoginPage so that we can use methods/locators of landingLoginPage.
		landingLoginPage landingLoginPage= new landingLoginPage(driver);
		landingLoginPage.navigateToUrl();
		landingLoginPage.loginToApplication("mhatresayali@gmail.com", "Bruno@12345");
		
	// Ha product cart madhe add karaychay mhanun declare kelay
		String needThisProductToADDTOCART = "ZARA COAT 3"; 
		String countryName = "India";
		
	// Creating object of productCatelogue so that we can use methods/locators of productCatelogue. 
	// productCatelogue already extend kartoy AbstractComponents class la, so we can use wait methods also.
		productCatelogue catelogue = new productCatelogue(driver);
		List<WebElement> productsPresentOnPage = catelogue.productsPresentOnDashboardPage(); // Wait apply karun list bhetli.
		catelogue.addProductToCart(needThisProductToADDTOCART); // Add product to cart. Wait for SuccessMessage and loading icon.
	
		
	// Creating object of cartPage so that we can use methods/locators of cartPage. But can not use again and again, not looks good.
	// So we can create object creation step in method only.	
	// and here write like this (Nahi lihila karan samajla nagia)
		cartPage cart = new cartPage(driver);
		cart.clickOnAddToCartButtonMethod();
		boolean verifyProductIsPresentinCart = cart.VerifyProductDisplay(needThisProductToADDTOCART);
		Assert.assertTrue(verifyProductIsPresentinCart);
		cart.clickOnCheckoutButton();

	// Checkout Page. 
		CheckoutPage checkout = new CheckoutPage(driver);
		checkout.performActionsOnCountryDropDown(countryName);
	// Thank you
		ThankYouPage thankyou = new ThankYouPage(driver);
		String confirmationMessage = thankyou.verifyConfirmationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order.")); //Validate Title.
		driver.close();
		
		
		

		
		
		
	}

}
