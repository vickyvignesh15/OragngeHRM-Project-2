package orangeHRM.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import orangeHRM.utilities.Utils;

public class Emp_TerminatePage extends Utils {

	// Search Elements
	@FindBy(xpath = "//span[text()='PIM']")
	WebElement pim_btn;

	@FindBy(xpath = "//div[@class='oxd-form-row']//div[@class='oxd-grid-item oxd-grid-item--gutters']//input[@class='oxd-input oxd-input--active']")
	WebElement empID_search;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement search_btn;

	// Job details locators
	@FindBy(xpath = "//a[text()='Job']")
	WebElement job_page;
	
	// Emp termination locators
	@FindBy(xpath="//button[@class='oxd-button oxd-button--medium oxd-button--label-danger --termination-button']")
	WebElement termination_btn;

	
	@FindBy(xpath="//div[@class='oxd-sheet oxd-sheet--rounded oxd-sheet--white oxd-dialog-sheet oxd-dialog-sheet--shadow oxd-dialog-sheet--gutters orangehrm-dialog-modal']//form[@class='oxd-form']//div[1]//input")
	WebElement termination_date_field;
	
	@FindBy(xpath="//div[@class='oxd-sheet oxd-sheet--rounded oxd-sheet--white oxd-dialog-sheet oxd-dialog-sheet--shadow oxd-dialog-sheet--gutters orangehrm-dialog-modal']//form[@class='oxd-form']//div[@class='oxd-form-row'][2]//div[@class='oxd-select-text-input']")
	WebElement termination_reason_dropdown;
	
	@FindBy(xpath="//div[@class='oxd-sheet oxd-sheet--rounded oxd-sheet--white oxd-dialog-sheet oxd-dialog-sheet--shadow oxd-dialog-sheet--gutters orangehrm-dialog-modal']//form[@class='oxd-form']//div[@class='oxd-form-row'][2]//div[@class='oxd-select-dropdown --positon-bottom']//span")
	List<WebElement> termination_reason_list;
	
	@FindBy(xpath="//div[@class='oxd-sheet oxd-sheet--rounded oxd-sheet--white oxd-dialog-sheet oxd-dialog-sheet--shadow oxd-dialog-sheet--gutters orangehrm-dialog-modal']//form[@class='oxd-form']//button[2]")
	WebElement save_btn;
	
	
	
	public Emp_TerminatePage() {
		PageFactory.initElements(driver, this);
	}

	// Emp termination

	public void empTermination(String empID, String termDate, String terReason) {

		clickElement(pim_btn, "PIM Page button");
		typeText(empID_search, "Emp ID search field", empID);
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

			clickElement(job_page, "Job Page");
			clickElement(termination_btn, "Terminate employee button");
			
			typeText(termination_date_field, "Termination date field", termDate);
			
			clickElement(termination_reason_dropdown, "Termination reason dropdown");
			
			for(WebElement ter_reason: termination_reason_list) {
				if(ter_reason.getText().equals(terReason)) {
					clickElement(ter_reason, terReason+": value");
					clickElement(save_btn, "Save button");
					Thread.sleep(5000);
				}
			}

		} catch (Exception e) {

		}

	}
	
	
}
