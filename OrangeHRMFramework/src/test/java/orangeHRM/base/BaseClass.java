package orangeHRM.base;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.Status;
import com.beust.jcommander.Parameter;

import orangeHRM.utilities.Utils;

public class BaseClass extends Utils {

	@BeforeMethod
	@Parameters("tc")
	public void suiteInitializer(String testCaseName) {
		test = extent.createTest(testCaseName);
		browserLaunch(getPropValue("browser"));
		launchUrl(getPropValue("url"));

	}

	@BeforeSuite
	public void testInitializer() {
		reportStarter();
	}

	@AfterMethod
	public void testCloserIT(ITestResult result) {
		extentReportFinisher();
		closeBrowser();
	}

	@AfterSuite
	public void suiteCloser() {
		extentReportFinisher();
	}

}
