package orangeHRM.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import orangeHRM.utilities.Utils;

public class Emp_ContactDetailsPage extends Utils{

	@FindBy(xpath = "//span[text()='PIM']")
	WebElement pim_btn;

	@FindBy(xpath = "//div[@class='oxd-form-row']//div[@class='oxd-grid-item oxd-grid-item--gutters']//input[@class='oxd-input oxd-input--active']")
	WebElement empID_search;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement search_btn;
	
	// Contact Details page
	@FindBy(xpath="//a[text()='Contact Details']")
	WebElement contact_details_page;

	//Address Locators
	@FindBy(xpath="//form[@class='oxd-form']//div[@class='oxd-form-row'][1]//div[@class='oxd-grid-item oxd-grid-item--gutters'][1]//input")
	WebElement add_st1_field;
	
	@FindBy(xpath="//form[@class='oxd-form']//div[@class='oxd-form-row'][1]//div[@class='oxd-grid-item oxd-grid-item--gutters'][2]//input")
	WebElement add_st2_field;
	
	@FindBy(xpath="//form[@class='oxd-form']//div[@class='oxd-form-row'][1]//div[@class='oxd-grid-item oxd-grid-item--gutters'][3]//input")
	WebElement add_city_field;
	
	@FindBy(xpath="//form[@class='oxd-form']//div[@class='oxd-form-row'][1]//div[@class='oxd-grid-item oxd-grid-item--gutters'][4]//input")
	WebElement state_field;
	
	@FindBy(xpath="//form[@class='oxd-form']//div[@class='oxd-form-row'][1]//div[@class='oxd-grid-item oxd-grid-item--gutters'][5]//input")
	WebElement postalCode_field;
	
	@FindBy(xpath="//form[@class='oxd-form']//div[@class='oxd-form-row'][1]//div[@class='oxd-grid-item oxd-grid-item--gutters'][6]//div[@class='oxd-select-text-input']")
	WebElement country_dropdown;
	
	@FindBy(xpath="//form[@class='oxd-form']//div[@class='oxd-form-row'][1]//div[@class='oxd-grid-item oxd-grid-item--gutters'][6]//div[@class='oxd-select-dropdown --positon-bottom']//span")
	List<WebElement> country_list;
	
	
	//Telephone details locators
	@FindBy(xpath="//form[@class='oxd-form']//div[@class='oxd-form-row'][2]//div[@class='oxd-grid-item oxd-grid-item--gutters'][1]//input")
	WebElement home_num_field;
	
	@FindBy(xpath="//form[@class='oxd-form']//div[@class='oxd-form-row'][2]//div[@class='oxd-grid-item oxd-grid-item--gutters'][2]//input")
	WebElement mob_num_field;
	
	@FindBy(xpath="//form[@class='oxd-form']//div[@class='oxd-form-row'][2]//div[@class='oxd-grid-item oxd-grid-item--gutters'][3]//input")
	WebElement work_num_field;
	
	//Email details locators
	@FindBy(xpath="//form[@class='oxd-form']//div[@class='oxd-form-row'][3]//div[@class='oxd-grid-item oxd-grid-item--gutters'][1]//input")
	WebElement work_email_field;
	
	@FindBy(xpath="//form[@class='oxd-form']//div[@class='oxd-form-row'][3]//div[@class='oxd-grid-item oxd-grid-item--gutters'][2]//input")
	WebElement other_email_field;
	
	@FindBy(xpath="//form//button[@type='submit']")
	WebElement save_btn;
	
	
	public Emp_ContactDetailsPage() {
		PageFactory.initElements(driver, this);

	}
	
	//Emp contact details updation
	public void empContactDetailsUpdation(String empID,String street1,String street2
			,String city, String state, String postalcode,String country_val
			,String homeNum,String mobNum, String workNum,String workEmail, String otherEmail) {
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
				clickElement(contact_details_page, "Contact Details Page Tab");
				deleteText(add_st1_field);
				Thread.sleep(2000);
				deleteText(add_st1_field);
				typeText(add_st1_field, "Address: Street1 field", street1);
				
				deleteText(add_st2_field);
				typeText(add_st2_field, "Address: Street2 field", street2);
				
				deleteText(add_city_field);
				typeText(add_city_field, "Address: City field", city);
				
				deleteText(state_field);
				typeText(state_field, "Address: State field", state);
				
				deleteText(postalCode_field);
				typeText(postalCode_field, "Address: Postalcode field", postalcode);
				
				clickElement(country_dropdown, "Address: Country Dropdown");
				
				for(WebElement country: country_list) {
					if(country.getText().equals(country_val)) {
						clickElement(country, country_val+": value");
					}
				}
				
				
			}	catch(Exception e) {	
			
		}
		try {
			//deleteText(add_st1_field);
			//Thread.sleep(2000);
			
			deleteText(home_num_field);
			typeText(home_num_field, "Home mobile number field", homeNum);
			
			deleteText(mob_num_field);
			typeText(mob_num_field, "Mobile number field", mobNum);
			
			deleteText(work_num_field);
			typeText(work_num_field, "Work number field", workNum);
		} catch (Exception e) {
			
		}
		
		try {
			
			deleteText(work_email_field);
			typeText(work_email_field, "Work email id field", workEmail);
			
			deleteText(other_email_field);
			typeText(other_email_field, "Other email id field", otherEmail);
			
			clickElement(save_btn, "Save button");
			Thread.sleep(5000);
		} catch (Exception e) {
			
		}
		
		
	}
	
}
