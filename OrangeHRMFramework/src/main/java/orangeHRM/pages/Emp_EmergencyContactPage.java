package orangeHRM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import orangeHRM.utilities.Utils;

public class Emp_EmergencyContactPage extends Utils {

	// Search Elements
	@FindBy(xpath = "//span[text()='PIM']")
	WebElement pim_btn;

	@FindBy(xpath = "//div[@class='oxd-form-row']//div[@class='oxd-grid-item oxd-grid-item--gutters']//input[@class='oxd-input oxd-input--active']")
	WebElement empID_search;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement search_btn;

	// Emergency contact details element
	@FindBy(xpath = "//a[text()='Emergency Contacts']")
	WebElement emer_contact_page;
	
	@FindBy(xpath="//div[1]/div/div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']/div[1]/button[1]")
	WebElement add_btn;

	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][1]//div[@class='oxd-grid-item oxd-grid-item--gutters'][1]//input")
	WebElement name_field;

	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][1]//div[@class='oxd-grid-item oxd-grid-item--gutters'][2]//input")
	WebElement relationship_field;

	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][2]//div[@class='oxd-grid-item oxd-grid-item--gutters'][1]//input")
	WebElement home_num_field;

	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][2]//div[@class='oxd-grid-item oxd-grid-item--gutters'][2]//input")
	WebElement mob_num_field;

	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][2]//div[@class='oxd-grid-item oxd-grid-item--gutters'][3]//input")
	WebElement work_num_field;

	@FindBy(xpath = "//form[@class='oxd-form']//button[2]")
	WebElement save_btn;
	

	public Emp_EmergencyContactPage() {
		PageFactory.initElements(driver, this);
	}
	
	// Employee emergency contact page
	public void empEmergencyContactsUpdation(String empID,String name,String relationship, String home_num, String mob_num, String work_num) {
		
		clickElement(pim_btn, "PIM Page button");
		typeText(empID_search, "Emp ID search field", empID);
		clickElement(search_btn, "EMP search button");
		
		try {
			WebElement emp = driver.findElement(By.xpath(
					"//div[@class='oxd-table-body']//div[@class='oxd-table-row oxd-table-row--with-border oxd-table-row--clickable']//div[text()='"
							+ empID + "']"));
			
			if(emp.isDisplayed()) {
				test.log(Status.PASS, "Employee with Emp ID: "+ empID+ " is found");
				clickElement(emp, "Employee with Emp ID: "+ empID);
			}else {
				test.log(Status.FAIL, "Employee with Emp ID: "+ empID+ " is  not found");
			}
			
			clickElement(emer_contact_page, "Emergency Contact Page");
			clickElement(add_btn, "Add button");
			
			deleteText(name_field);
			typeText(name_field, "Name field", name);
			
			deleteText(relationship_field);
			typeText(relationship_field, "Relationship field", relationship);
			
			deleteText(home_num_field);
			typeText(home_num_field, "Home Number field", home_num);
			
			deleteText(mob_num_field);
			typeText(mob_num_field, "Mobile number field", mob_num);
			
			deleteText(work_num_field);
			typeText(work_num_field, "Work Number field", work_num);
			
			clickElement(save_btn, "Save button");
		}catch(Exception e) {
			
		}
	}

}
