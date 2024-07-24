package SeleniumFramework2.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.Test;

public class RetryListenerClass implements IRetryAnalyzer {

// This is listener class.
// Instead of "ITestListener" we will use "IRetryAnalyzer"	
// This will rerun the failed test cases again.	
// When your test fails, it will go to that ITestListener class, it will report failure and SS
// Then it will come to this class to rerun.	
	
	
	int count = 0;
	int maxTry = 1; // Kiti vela re-run karaychay te.
	@Override
	public boolean retry(ITestResult result) 
	{
		if(count<maxTry)
		{
			count++;
			return true;
		}
		return false;
	}

	
// Only ITestListener we can run from Listener class.
// If you want to run IRetryAnalyzer class then in main test you have to write in bracket.	
// @Test (dependsOnMethods= {"submitOrder"}, dataProvider="getData", retryAnalyzer=RetryListenerClass.class)	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
