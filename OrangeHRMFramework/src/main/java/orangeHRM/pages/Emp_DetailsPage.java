package orangeHRM.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import orangeHRM.utilities.Utils;

public class Emp_DetailsPage extends Utils {

	@FindBy(xpath = "//span[text()='PIM']")
	WebElement pim_btn;

	@FindBy(xpath = "//div[@class='oxd-form-row']//div[@class='oxd-grid-item oxd-grid-item--gutters']//input[@class='oxd-input oxd-input--active']")
	WebElement empID_search;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement search_btn;

	@FindBy(xpath = "//div[@role='tablist']//a")
	List<WebElement> tabs;

	public Emp_DetailsPage() {
		PageFactory.initElements(driver, this);
	}

	// Validating emp personal deatils page tabs
	public void empDetailsTab(String empID) {

		clickElement(pim_btn, "PIM page button");
		typeText(empID_search, "Emp ID search field", empID);
		clickElement(search_btn, "Search button");

		WebElement emp = driver.findElement(By.xpath(
				"//div[@class='oxd-table-body']//div[@class='oxd-table-row oxd-table-row--with-border oxd-table-row--clickable']//div[text()='"
						+ empID + "']"));

		if(emp.getText().contains(empID)) {
			
			test.log(Status.INFO, "Employee deatils with EMP ID: "+empID+" is found...");
			emp.click();
			
			for(WebElement tab:tabs) {
				if(tab.isDisplayed()) {
					test.log(Status.PASS, tab.getText()+": tab is displayed");
				}else {
					test.log(Status.FAIL, tab.getText()+": tab is not displayed");
				}
			}
			test.log(Status.PASS, "Test case passed...");
		}
		if(!emp.getText().contains(empID)) {
			System.out.println("Test case failed");
			test.log(Status.INFO, "Employee deatils with EMP ID: "+empID+" is not found...");
		}

	}
}
