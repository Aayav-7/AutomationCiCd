package SeleniumFramework2.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import SeleniumFramework3.resources.ExtentReporterNG;

public class ListenersToUseForExtentReportConcept extends CommonBaseTest implements ITestListener{
	
	ExtentReports exreport = CommonBaseTest.getReportObject(); // As we declare method as static, we are calling it directly className.MethodName
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); // If you run tests parellaly, then it will take care of now mixing results.

	@Override
	public void onTestStart(ITestResult result) 
	{
		// On test start, control will come here and we have already created extent report code.
		// We will use that and create test here and perform action on that.
		// Here we have to give Test Case Name. We are not gonna give same name for all Test cases right?
		// So we will use "result.getMethod().getMethodName()"	
		test = exreport.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}
	
	

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		test.log(Status.PASS, "Test is Passed"); // This will just print whatever written.
		//extentTest.get().log(Status.PASS, "Test is Passed");
	}
	
	

	@Override
	public void onTestFailure(ITestResult result) 
	{
		test.fail(result.getThrowable());	// This will give error logs of failed method.
		// extentTest.get().fail(result.getThrowable());
		// SS yenar nahi, because driver navta. So driver add kela filePath chya step madhe.
		// Tya driver la life dili below step ne. result chya suite madhe ja > Tithla test madhe class madhe ja > Mg actual class ani tithun driver ghe.
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		
		}
		String filePath = null;
		// Below code is for taking SS and attaching it to report.
		// SS code we have added in different class and that class extent CommonBaseTest, so here also we are extending CommonBaseTest.		
		 try 
		 {
			 filePath = getScreenShot(result.getMethod().getMethodName(), driver);
		 } catch (IOException e) 
		 {
			e.printStackTrace();
		 } // in () give location of test case name.
		 test.addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
		 // extentTest.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
	}

	/*--------------------------------------------------------------------------
	@Override
	public void onTestSkipped(ITestResult result) 
	{
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
	{
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) 
	{
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) 
	{
		ITestListener.super.onStart(context);
	}
	--------------------------------------------------------------------------*/

	@Override
	public void onFinish(ITestContext context) 
	{
		exreport.flush();
	}

}
