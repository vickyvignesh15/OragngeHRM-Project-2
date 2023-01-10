package orangeHRM.utilities;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Utils {

	public static WebDriver driver;
	public static String dateandtime;
	public static ExtentSparkReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;

	// Getting prop value
	public static String getPropValue(String key) {

		String value = null;
		try {
			Properties p = new Properties();
			FileInputStream fp = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\ConfigFiles\\config.properties");
			p.load(fp);
			value = p.getProperty(key);

		} catch (Exception e) {
			System.out.println("Error in getting the prop value: " + e.getMessage());
		}
		return value;
	}

	// Browser launch
	public static void browserLaunch(String bname) {
		switch (bname) {
		case "Chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "Firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "Edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		default:
			System.out.println("No valide name found so launching the default chrome browser");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// Launch url
	public static void launchUrl(String url) {
		try {
			driver.get(url);
		} catch (Exception e) {
			System.out.println("Error in launching the url...");
			e.printStackTrace();
		}
	}

	// Check the element availability
	public static boolean waitforme(WebElement element, String id) {
		boolean flag = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(element));
			test.log(Status.PASS, id + " is available");
			flag = true;

		} catch (Exception e) {
			System.out.println("Error occured while waiting for the element " + id + "with exception" + e.getMessage());
			test.log(Status.FAIL, id + " is not available");
		}
		return flag;

	}

	// Click a webElement
	public void clickElement(WebElement element, String id) {

		try {
			if (waitforme(element, id)) {
				element.click();
				test.log(Status.PASS, id + " is clicked");
			}
		} catch (Exception e) {
			System.out.println("Error in clicking the webelement: " + id + e.getMessage());
			test.log(Status.FAIL, "Failed to click the element: " + id);
		}
	}

	// Type text
	public void typeText(WebElement element, String id, String text) {
		try {
			if (waitforme(element, id)) {
				element.sendKeys(text);
				test.log(Status.PASS, "Typed '" + text + "' in the text box " + id);
			}
		} catch (Exception e) {
			System.out.println("Error in clicking the webelement :" + id + e.getMessage());
			test.log(Status.FAIL, "Failed to type the text: " + text + " the element :" + id);
		}
	}

	// Clear Text
		public void deleteText(WebElement element) {
			element.sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);;
		}
	
	// Extent report Starter
	public void reportStarter() {
		try {
			dateandtime = GetDateandTime();
			htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\Reports\\TestReport.html");
			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
			extent.setSystemInfo("Product", "OrangeHRM");
			extent.setSystemInfo("Environment", "Project Assignement");

			htmlReporter.config().setDocumentTitle("OrangeHRM TestReport");
			htmlReporter.config().setReportName("Functional test report");
			htmlReporter.config().setTheme(Theme.DARK);

		} catch (Exception e) {
			System.out.println("Error in ExtentReportStarter: " + e.getMessage());

		}
	}

	// ExtentReport finisher
	public static void extentReportFinisher() {

		try {
			extent.flush();
		} catch (Exception e) {
			System.out.println("Error in ExtentReportFinisher : " + e.getMessage());
		}
	}

	// Getting date and time
	public static String GetDateandTime() {
		DateFormat dateFormat = null;
		Date date = null;
		try {
			dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			date = new Date();
		} catch (Exception e) {
			System.out.println("Error in Getdateandtime : " + e.getMessage());
		}

		return dateFormat.format(date);
	}

	// Capture ScreenShot2
	public static String captureScreenshot() {
		TakesScreenshot ss = (TakesScreenshot) driver;
		String base64 = ss.getScreenshotAs(OutputType.BASE64);
		System.out.println("ScreenShot saved Sucessfully");
		return base64;
	}

	// Element is available or not
	public static boolean elementAvilability(WebElement element, String id) {
		boolean flag = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOf(element));
			test.log(Status.PASS, id + " is displayed");
			flag = true;
		} catch (Exception e) {
			test.log(Status.FAIL, id + " is not displayed");
		}
		return flag;
	}

	// Element is clickable or not
	public static boolean elemantIsClickable(WebElement element, String id) {
		boolean flag = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			test.log(Status.PASS, id+ " is clickable");
			flag=true;
		} catch (Exception e) {
			test.log(Status.FAIL, id + " is not clickable");
		}
		return flag;
	}
	
	// Close the browser

	public void closeBrowser() {
		driver.close();
	}

	// Quit the browser

	public void quitBrowser() {
		driver.quit();

	}

}
