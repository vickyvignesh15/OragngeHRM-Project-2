package orangeHRM.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import orangeHRM.utilities.Utils;

public class UserCreationPage extends Utils {

	@FindBy(xpath = "//span[text()='PIM']")
	WebElement pim_btn;

	@FindBy(xpath = "//div[@class='orangehrm-paper-container']/div/button")
	WebElement add_btn;

	@FindBy(xpath = "//input[@name='firstName']")
	WebElement fname_field;

	@FindBy(xpath = "//input[@name='middleName']")
	WebElement mname_field;

	@FindBy(xpath = "//input[@name='lastName']")
	WebElement lname_field;

	@FindBy(xpath = "//div[@class='oxd-grid-item oxd-grid-item--gutters']/div/div/input")
	WebElement empid_field;

	@FindBy(xpath = "//span[@class='oxd-switch-input oxd-switch-input--active --label-right']")
	WebElement create_login_toggle;

	@FindBy(xpath = "//div[@class='orangehrm-employee-form']/div[3]/div/div/div/div[2]/input")
	WebElement login_uname_field;

	@FindBy(xpath = "//label[normalize-space()='Enabled']//span[@class='oxd-radio-input oxd-radio-input--active --label-right oxd-radio-input']")
	WebElement enabled_radio_btn;

	@FindBy(xpath = "//div[@class='oxd-grid-item oxd-grid-item--gutters user-password-cell']//input[@type='password']")
	WebElement password_field;

	@FindBy(xpath = "//div[@class='oxd-grid-item oxd-grid-item--gutters']//input[@type='password']")
	WebElement confirmPassword_field;

	@FindBy(xpath = "//div[@class='oxd-form-actions']//button[@type='submit']")
	WebElement save_btn;

	@FindBy(xpath = "//a[text()='Employee List']")
	WebElement emp_lst_btn;

	// Error messages
	@FindBy(xpath = "//span[text()='Employee Id already exists']")
	WebElement empid_error_msg;

	@FindBy(xpath = "//span[text()='Username already exists']")
	WebElement uname_error_msg;

	@FindBy(xpath = "//span[text()='Should have at least 8 characters']")
	WebElement psw_error_msg;

	public UserCreationPage() {
		PageFactory.initElements(driver, this);
	}

	// New user creation
	public void userCreation(String fname, String mname, String lname, String empID, String username, String psw)
			throws InterruptedException {

		clickElement(pim_btn, "PIM page button");
		clickElement(add_btn, "Add new user button");
		Thread.sleep(2000);
		typeText(fname_field, "FirstName field", fname);
		Thread.sleep(2000);
		typeText(mname_field, "MiddleName field", mname);
		typeText(lname_field, "LastName field", lname);
		deleteText(empid_field);

		typeText(empid_field, "Emp ID field", empID);

		try {
			if (empid_error_msg.isDisplayed()) {
				test.log(Status.WARNING, "The given '" + empID + "' is already exist..");
				test.log(Status.FAIL, "Test case failed...");

			}
		} catch (NoSuchElementException e) {

			clickElement(create_login_toggle, "Create login toggle");
			typeText(login_uname_field, "Create login-Username filed", username);
			typeText(password_field, "Create password field", psw);
			
			try {
				if (psw_error_msg.isDisplayed()) {
					test.log(Status.WARNING, "The given '" + psw + "' should must have 8 characters..");
					test.log(Status.FAIL, "Test case failed...");

				}
			} catch (NoSuchElementException e1) {
				
				typeText(confirmPassword_field, "Confirm password field", psw);
				clickElement(save_btn, "Save button...");
				Thread.sleep(5000);
				
				if (emp_lst_btn.isEnabled()) {
					test.log(Status.PASS, "Test case passed....");
				} else {
					test.log(Status.FAIL, "Test case failed....");
				}
				
			}

		}

		
		
		
	}

}
