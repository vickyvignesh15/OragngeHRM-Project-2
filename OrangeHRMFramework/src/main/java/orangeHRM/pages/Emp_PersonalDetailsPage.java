package orangeHRM.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import orangeHRM.utilities.Utils;

public class Emp_PersonalDetailsPage extends Utils {

	@FindBy(xpath = "//span[text()='PIM']")
	WebElement pim_btn;

	@FindBy(xpath = "//div[@class='oxd-form-row']//div[@class='oxd-grid-item oxd-grid-item--gutters']//input[@class='oxd-input oxd-input--active']")
	WebElement empID_search;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement search_btn;

	@FindBy(xpath = "//i[@class='oxd-icon bi-chevron-right']")
	WebElement nxt_btn;

	// Emp personal details locators
	@FindBy(xpath = "//form[@class='oxd-form']/div[1]/child::div[2]/child::div/div/child::div[2]/input")
	WebElement nickName_field;

	@FindBy(xpath = "//form[1]/div[2]/child::div[1]/div[2]//child::div[2]//input")
	WebElement otherID_field;

	@FindBy(xpath = "//form[@class='oxd-form']/div[2]/div[2]//child::div/div/child::div[2]/child::input")
	WebElement driving_lic_field;

	@FindBy(xpath = "//form[@class='oxd-form']/div[2]/child::div[2]/child::div[2]/div[1]/div[2]/div/div/child::input")
	WebElement licence_expiry_date;

	@FindBy(xpath = "//form[@class='oxd-form']/div[2]/div[3]/child::div[1]/div[1]/child::div[2]/input")
	WebElement ssn_field;

	@FindBy(xpath = "//form[@class='oxd-form']/child::div[2]/child::div[3]/div[2]//child::input")
	WebElement sin_field;

	@FindBy(xpath = "//label[text()='Nationality']")
	WebElement nationality;

	@FindBy(xpath = "//form[@class='oxd-form']/child::div[3]/div/div[1]/child::div//div[@class='oxd-select-text--after']/child::i")
	WebElement nationality_dropdown;

	@FindBy(xpath = "//div[@class='oxd-select-dropdown --positon-bottom']/div/span")
	List<WebElement> nationality_list;

	@FindBy(xpath = "//form[@class='oxd-form']/child::div[3]/div[1]/child::div[2]//i")
	WebElement marital_status_dropdown;

	@FindBy(xpath = "//div[@class='oxd-select-dropdown --positon-bottom']/div/span")
	List<WebElement> marital_status_list;

	@FindBy(xpath = "//form[@class='oxd-form']/child::div[3]/child::div[2]/div/div/div[2]/child::div/div/input[1]")
	WebElement dob;

	@FindBy(xpath = "//label[text()='Male']")
	WebElement G_male;

	@FindBy(xpath = "//label[text()='Female']")
	WebElement G_female;

	@FindBy(xpath = "//form[@class='oxd-form']/child::div[4]/div/child::div/div/child::div[2]/input")
	WebElement militaryService_field;

	@FindBy(xpath = "//form[@class='oxd-form']/child::div[5]/button[@type='submit']")
	WebElement form1_save_btn;

	@FindBy(xpath = "//div[@class='orangehrm-custom-fields']//form[@class='oxd-form']//div[@class='oxd-select-text-input']")
	WebElement blood_grp_dropdown;

	@FindBy(xpath = "//div[@class='oxd-select-dropdown --positon-bottom']//div//span")
	List<WebElement> blood_grp_list;

	@FindBy(xpath = "//div[@class='orangehrm-custom-fields']//button")
	WebElement form2_save_btn;

	public Emp_PersonalDetailsPage() {
		PageFactory.initElements(driver, this);
	}

	// Updating employee personal details page
	public void empDetailsUpdation(String empID, String NickName, String otherID, String licence_num,
			String lic_expiry_date, String SSN_num, String SIN_num, String national, String matitalStatus, String DOB,
			String gender, String militaryService, String bld_grp) throws InterruptedException {

		clickElement(pim_btn, "PIM page button");
		typeText(empID_search, "EMP ID search field", empID);
		clickElement(search_btn, "Search button");

		WebElement emp = driver.findElement(By.xpath(
				"//div[@class='oxd-table-body']//div[@class='oxd-table-row oxd-table-row--with-border oxd-table-row--clickable']//div[text()='"
						+ empID + "']"));
		boolean flag = emp.isDisplayed();

		if (flag == true) {
			test.log(Status.INFO, "Employee with the EMP ID: " + empID + " is found");
			clickElement(emp, "Employee with the EMP ID: " + empID);
		} else {
			test.log(Status.INFO, "Employee with the EMP ID: " + empID + " is not found");
		}

		deleteText(nickName_field);
		typeText(nickName_field, "NickName field", NickName);

		deleteText(otherID_field);
		typeText(otherID_field, "Other ID field", otherID);

		deleteText(driving_lic_field);
		typeText(driving_lic_field, "Driving licence number field", licence_num);

		deleteText(licence_expiry_date);
		typeText(licence_expiry_date, "Driving licence expiry date field", lic_expiry_date);

		deleteText(ssn_field);
		typeText(ssn_field, "SSN Number field", SSN_num);

		deleteText(sin_field);
		typeText(sin_field, "SIN Number field", SIN_num);

		try {
			if (nationality.isDisplayed()) {

				clickElement(nationality_dropdown, "Nationality dropdown button");

				for (WebElement nat_value : nationality_list) {
					if (nat_value.getText().equals(national)) {
						clickElement(nat_value, national + ": value");

					}
				}
			}
		} catch (StaleElementReferenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (marital_status_dropdown.isDisplayed()) {
			clickElement(marital_status_dropdown, "Marital status dropdown");
			for (WebElement marital_status : marital_status_list) {
				if (marital_status.getText().equals(matitalStatus)) {
					clickElement(marital_status, matitalStatus + ": value");

					deleteText(dob);
					typeText(dob, "Date of birth field", DOB);

					if (gender.equals(G_male.getText())) {
						clickElement(G_male, "Gender: " + gender);
					} else if (gender.equals(G_female.getText())) {
						clickElement(G_female, "Gender: " + gender);
					}

					deleteText(militaryService_field);
					typeText(militaryService_field, "Military service field", militaryService);
					clickElement(form1_save_btn, "Personal details Save button");

					String nickNamevalue = nickName_field.getAttribute("value");
					if (nickNamevalue.isEmpty()) {
						// System.out.println(value);
						test.log(Status.FAIL, nickNamevalue + ": Value is not entered");
					} else {
						test.log(Status.PASS, nickNamevalue + ": Value entered in nick name field sucessfully");
					}

					if (otherID_field.getAttribute("value").isEmpty()) {
						test.log(Status.FAIL, otherID_field.getAttribute("value") + ": Value is not entered");
					} else {
						test.log(Status.PASS,
								otherID_field.getAttribute("value") + ": Value entered in other id field sucessfully");
					}

					if (driving_lic_field.getAttribute("value").isEmpty()) {
						test.log(Status.FAIL, " value is not enterd sucessfully ");
					} else {
						test.log(Status.PASS, driving_lic_field.getAttribute("value")
								+ ": value enterd in driving license id field successfully");
					}

					if (licence_expiry_date.getAttribute("value").isEmpty()) {
						test.log(Status.FAIL, " value is not enterd sucessfully ");
					} else {
						test.log(Status.PASS, licence_expiry_date.getAttribute("value")
								+ ": value enterd in driving license expiry date field successfully");
					}

					if (ssn_field.getAttribute("value").isEmpty()) {
						test.log(Status.FAIL, " value is not enterd sucessfully ");
					} else {
						test.log(Status.PASS,
								ssn_field.getAttribute("value") + ": value enterd in SSN Number field successfully");
					}

					if (sin_field.getAttribute("value").isEmpty()) {
						test.log(Status.FAIL, " value is not enterd sucessfully ");
					} else {
						test.log(Status.PASS,
								sin_field.getAttribute("value") + ": value enterd in SIN Number field successfully");
					}

					WebElement nat_value = driver
							.findElement(By.xpath("//form[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]"));
					if (nat_value.getText().isEmpty()) {
						test.log(Status.FAIL, " value is not enterd sucessfully ");
					} else {
						test.log(Status.PASS, nat_value.getText() + ": value enterd in nationality field successfully");
					}

					WebElement gen_value = driver.findElement(
							By.xpath("//form[1]/div[3]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/child::div[1]"));
					if (gen_value.getText().isEmpty()) {
						test.log(Status.FAIL, " value is not enterd sucessfully ");

					} else {
						test.log(Status.PASS,
								gen_value.getText() + ": value enterd in gender dropdown field successfully");

					}

					if (dob.getAttribute("value").isEmpty()) {
						test.log(Status.FAIL, " value is not enterd sucessfully ");
					} else {
						test.log(Status.PASS,
								dob.getAttribute("value") + ": value enterd in date of birth field successfully");
					}

					if (G_male.isEnabled() || G_female.isEnabled()) {
						test.log(Status.PASS, gender + ": value is selected");
					} else {
						test.log(Status.FAIL, gender + ": value is not selected");
					}

					if (militaryService_field.getAttribute("value").isEmpty()) {
						test.log(Status.FAIL, " value is not enterd sucessfully ");
					} else {
						test.log(Status.PASS, militaryService_field.getAttribute("value")
								+ ": value enterd in military services field successfully");
					}

					clickElement(blood_grp_dropdown, "Blood group dropdown button");

					for (WebElement blood_grp : blood_grp_list) {
						if (blood_grp.getText().contains(bld_grp)) {
							clickElement(blood_grp, blood_grp.getText() + ": Blood group");
							clickElement(form2_save_btn, "Customfields save button");

						}
					}

					if (blood_grp_dropdown.getText().isEmpty()) {
						test.log(Status.FAIL, " value is not enterd sucessfully ");

					} else {
						test.log(Status.PASS,
								blood_grp_dropdown.getText() + ": value enterd in gender dropdown field successfully");

					}

				}
			}

		}

	}

}
