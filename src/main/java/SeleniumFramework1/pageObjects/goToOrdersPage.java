package SeleniumFramework1.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFramework2.AbstractComponents.AbstractComponents;

public class goToOrdersPage extends AbstractComponents{

	
	
	
	WebDriver driver;
	public goToOrdersPage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
	
	
	
	@FindBy(css="tr td:nth-child(3)")
	List <WebElement> itemsPresentInOrderPage;
	
	public Boolean VerifyOrderDisplay(String needThisProductToADDTOCART)
	{
		Boolean match = itemsPresentInOrderPage
				        .stream()
				        .anyMatch(eachOrderProduct->eachOrderProduct
				        .getText().equalsIgnoreCase(needThisProductToADDTOCART));
		return match;
		
	}
	
	
	
	
	
	
}
