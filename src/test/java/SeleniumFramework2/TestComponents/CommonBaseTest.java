package SeleniumFramework2.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeleniumFramework1.pageObjects.landingLoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CommonBaseTest {

// Common to all tests.	
// We have created new package SeleniumFramework3.resources
// Under that we have created file GlobalData.properties (use .properties extension)
// By using that extension you can fetch that file data into your properties class.	
// Also we are running tests here in TestNG	

	public WebDriver driver;
	public landingLoginPage llP; 
	public WebDriver initializeDriver() throws IOException {
		// Create object for properties class
		Properties prop = new Properties();
		// Give location of file as an argument.
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//SeleniumFramework3//resources//GlobalData.properties");
		prop.load(fis); // Here you have to sent that file in the form of input stream, so we are converting that file above.
		String browserName =System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
		//String browserName = prop.getProperty("browser");
		// Smart way. Either aaplyala browser value properties file madhun milel or system madhun (maven cmd)
		// So use ternery operator, explained as below.
		// Jar System ne browser dila tar tyacha lagech next code execute hoil.
		// Jar System ne nahi dila (null) tar last code execute hoil jo ki properties file madhun yetoy.

		if (browserName.contains("chrome")) 
		{
			// To run tests in headless mode, use below 2 lines of code.
			ChromeOptions options = new ChromeOptions();	
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless"))
			{
			options.addArguments("headless");
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			}
			driver = new ChromeDriver();
		} 
		else if (browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if (browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}	

	@BeforeMethod(alwaysRun=true)	
	public landingLoginPage launchApplication() throws IOException
		{
			 driver = initializeDriver();
			  llP = new landingLoginPage(driver);
			 llP.navigateToUrl();
			return llP;
		}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.close();
	}
	
	
		
	// This class we are extending everywhere so we are creating Object of DataReader class here.
	// We are just placing everything here.
	// Copying this from DataReader class.
	// And this is generic code for all json files.
	// So we will remove that path and put/use that path at the time of execution.
	// And in bracket put String filePath
    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	
	{
		// Read json and convert it to String. Copy path of that json file.
		String jsonContent = FileUtils.readFileToString(new File(filePath)
				, StandardCharsets.UTF_8);
		// Now, convert String to HashMap, for that need Jackson databind dependency from Mevan Repository.
		// Gapchup path karaycha, kay ata karnar.
		// It will convert string to HashMap and put it in a List.
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>()
		{});
		return data;
	}
    
 	
 
 // Extent report utility
 	public static ExtentReports getReportObject() // If declare method as static, then you can declare it without creating object.
	{
		// This coding will automatically create new folder inside ExtentReports project and in that report will save.
				String path = System.getProperty("user.dir")+"\\reports\\Test_Report_1.html";
				ExtentSparkReporter reporter = new ExtentSparkReporter(path);
				reporter.config().setReportName("Web Automation Results");
				reporter.config().setDocumentTitle("Test Results");
				
				ExtentReports exreport = new ExtentReports(); // He pn object creation ch but some part we have declare globally, so here we have used exre = new ExtentReports();
				exreport.attachReporter(reporter);
				exreport.setSystemInfo("Tester", "Sayali Mhatre");
				return exreport;
				// Ethe metadata create kela and yachi test listeners madhe create karnar.
	}
 	
 	
 // Writing code for SS.
  	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException
  	{
  		TakesScreenshot ts = (TakesScreenshot)driver;
  		File source = ts.getScreenshotAs(OutputType.FILE);
  		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName+ ".png");
  		FileUtils.copyFile(source, file);
  		return System.getProperty("user.dir") + "//reports//" + testCaseName+ ".png";
  	}
	
// File name is too long and if you give this file to someone else to run then they won't bcoz it's in your laptop.
		// C:\\Users\\mroha\\OneDrive\\Desktop\\Sayali Mhatre\\Selenium\\SeleniumFramework\\
// So use below code instead 		
// System.getProperty("user.dir")+"\\src\\main\\java\\SeleniumFramework3\\resources\\GlobalData.properties"	

}
