package SeleniumFramework3.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
// Ethe aapn global level cha ek extent report cha class create kela.
// now we will use this exre object in each @Test (wherever want)
// That is also not possible to write it in each @Test.
// So we will use Listeners class concept under....
// package SeleniumFramework2.TestComponents; class ListenersToUseForExtentReportConcept {
	
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
	
	
	
	
// exreport.createTest("TestName")
// This step we have to write in each and every test method.	
// This step create Test for given method.
// But it's not good idea to write this step in each and every test method.
// So, we smartly we will write it in Listeners class.	
	
	
	
	
	

}
