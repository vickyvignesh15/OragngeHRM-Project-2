package orangeHRM.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import orangeHRM.utilities.Utils;

public class AdminPage extends Utils {

	// Drop down
	@FindBy(xpath = "//span[text()='Admin']")
	WebElement admin_btn;

	@FindBy(xpath = "//span[text()='User Management ']")
	WebElement usermangement_btn;

	@FindBy(xpath = "//a[text()='Users']")
	WebElement user_btn;

	@FindBy(xpath = "//div[@class='oxd-input-group oxd-input-field-bottom-space']//label[text()='User Role']")
	WebElement userRole_btn;

	@FindBy(xpath = "//div[@class='oxd-table-filter-area']//div[2]//div[1]//div[2]//div[1]//div[1]//div[2]//i[1]")
	WebElement u_dropDown_icon;
	
	@FindBy(xpath="//div[@class='oxd-select-dropdown --positon-bottom']//span")
	List<WebElement> u_dropDown_values;

	@FindBy(xpath = "//div[@class='oxd-input-group oxd-input-field-bottom-space']//label[text()='Status']")
	WebElement status_btn;

	@FindBy(xpath = "//form[1]/div[1]/div[1]/div[4]/div[1]/div[2]/div[1]/div[1]/div[2]/i[1]")
	WebElement s_dropDown_btn;
	
	@FindBy(xpath = "//div[@class='oxd-select-dropdown --positon-bottom']//span")
	List<WebElement> s_dropDown_values;
	
	
	
	public AdminPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void dropdownValidation() {
		clickElement(admin_btn, "Admin page button");
		clickElement(usermangement_btn, "User management header tab");
		clickElement(user_btn, "User field under user management");
		
		//Validating user role drop down values
		if(elementAvilability(userRole_btn, "UserRole DropDown")) {
			if(u_dropDown_icon.isDisplayed()) {
				clickElement(u_dropDown_icon, "UserRole dropDown icon");
				for(WebElement value:u_dropDown_values) {
					if(value.isDisplayed()) {
						test.log(Status.PASS, "DropDown value: '"+ value.getText()+"' is displayed");
					}
					else {
						test.log(Status.FAIL, "DropDown values are not displayed");
					}
					
				}
			}
		}else {
			test.log(Status.FAIL, "DropDown is not available");
		}
		
		// Validating status dropdown values
		
		if(elementAvilability(status_btn, "Status DropDown")) {
//			String value1="";
//			String value2="";
			if(s_dropDown_btn.isDisplayed()) {
				clickElement(s_dropDown_btn, "Status dropDown icon");
				for(WebElement value:s_dropDown_values) {
					if(value.isDisplayed()) {
//						value1=value.getText();
//						value2=value.getText();
						test.log(Status.PASS, "DropDown value: '"+ value.getText()+"' is displayed");
						
					}
					else {
						test.log(Status.FAIL, "DropDown values are not displayed");
					}
					
				}
			}
		}else {
			test.log(Status.FAIL, "DropDown is not available");
		}
		
	}

}
