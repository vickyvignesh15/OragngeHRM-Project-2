package orangeHRM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import orangeHRM.utilities.Utils;

public class Emp_ActivationPage extends Utils {
	
	// Search Elements
		@FindBy(xpath = "//span[text()='PIM']")
		WebElement pim_btn;

		@FindBy(xpath = "//div[@class='oxd-form-row']//div[@class='oxd-grid-item oxd-grid-item--gutters']//input[@class='oxd-input oxd-input--active']")
		WebElement empID_search;

		@FindBy(xpath="//div[@class='oxd-form-row']//div[@class='oxd-grid-item oxd-grid-item--gutters'][4]//div[@class='oxd-select-text-input']")
		WebElement include_dropdown;
		
		@FindBy(xpath="//div[@class='oxd-form-row']//div[@class='oxd-grid-item oxd-grid-item--gutters'][4]//div[@class='oxd-select-dropdown --positon-bottom']//span[text()='Current and Past Employees']")
		WebElement current_past;
		
		@FindBy(xpath = "//button[@type='submit']")
		WebElement search_btn;

		// Job details locators
		@FindBy(xpath = "//a[text()='Job']")
		WebElement job_page;

	
	@FindBy(xpath="//button[text()=' Activate Employment ']")
	WebElement activate_button;
	
	
	public Emp_ActivationPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void activateEmp(String empID) {
		
		clickElement(pim_btn, "PIM Page button");
		typeText(empID_search, "Emp ID search field", empID);
		
		clickElement(include_dropdown, "Include dropdown button");
		
		clickElement(current_past, "Current and past employees");
		
		clickElement(search_btn, "EMP search button");
		
		

		try {
			WebElement emp = driver.findElement(By.xpath(
					"//div[@class='oxd-table-body']//div[@class='oxd-table-row oxd-table-row--with-border oxd-table-row--clickable']//div[text()='"
							+ empID + "']"));

			if (emp.isDisplayed()) {
				test.log(Status.PASS, "Employee with Emp ID: " + empID + " is found");
				clickElement(emp, "Employee with Emp ID: " + empID);
			} else {
				test.log(Status.FAIL, "Employee with Emp ID: " + empID + " is  not found");
			}
			
			clickElement(job_page, "Job page");
		
		clickElement(activate_button, "Activate employee button");
	}catch(Exception e) {
		
	}
}
}
