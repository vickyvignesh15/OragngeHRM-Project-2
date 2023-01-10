package orangeHRM.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import orangeHRM.utilities.Utils;

public class Emp_DependentsPage extends Utils {

	// Search Elements
	@FindBy(xpath = "//span[text()='PIM']")
	WebElement pim_btn;

	@FindBy(xpath = "//div[@class='oxd-form-row']//div[@class='oxd-grid-item oxd-grid-item--gutters']//input[@class='oxd-input oxd-input--active']")
	WebElement empID_search;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement search_btn;

	// Dependents page locators
	@FindBy(xpath = "//a[text()='Dependents']")
	WebElement dependents_Page;

	@FindBy(xpath = "//div[1]/div/div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']/div[1]/button[1]")
	WebElement add_btn;

	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][1]//div[@class='oxd-grid-item oxd-grid-item--gutters'][1]//input")
	WebElement name_field;

	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][1]//div[@class='oxd-grid-item oxd-grid-item--gutters'][2]//div[@class='oxd-select-text-input']")
	WebElement relationship_field;

	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][1]//div[@class='oxd-grid-item oxd-grid-item--gutters'][2]//div[@class='oxd-select-dropdown --positon-bottom']//span")
	List<WebElement> relationship_list;

	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][2]//div[@class='oxd-grid-item oxd-grid-item--gutters'][1]//input")
	WebElement dob_field;

	@FindBy(xpath = "//form[@class='oxd-form']//button[2]")
	WebElement save_btn;

	public Emp_DependentsPage() {
		PageFactory.initElements(driver, this);
	}

	// Updating emp dependents details
	public void empDependententsUpdation(String empID, String name, String relationship, String dob) {

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
				if (dependents_Page.isDisplayed()) {
					clickElement(dependents_Page, "Dependents Page");
				}
				clickElement(add_btn, "Add button");
				typeText(name_field, "Name field", name);

				try {
					clickElement(relationship_field, "Relationship field");
					for (WebElement relation : relationship_list) {
						if (relation.getText().equals(relationship)) {
							clickElement(relation, relationship + ": value");
							typeText(dob_field, "Date of birth field", dob);
							clickElement(save_btn, "Save button");
						}
					}

				} catch (Exception e) {

				}

			}

		} catch (Exception e) {
			test.log(Status.FAIL, "Employee with Emp ID: " + empID + " is  not found");

		}

	}
}
