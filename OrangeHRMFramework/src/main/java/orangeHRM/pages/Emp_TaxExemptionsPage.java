package orangeHRM.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import orangeHRM.utilities.Utils;

public class Emp_TaxExemptionsPage extends Utils {

	// Search Elements
	@FindBy(xpath = "//span[text()='PIM']")
	WebElement pim_btn;

	@FindBy(xpath = "//div[@class='oxd-form-row']//div[@class='oxd-grid-item oxd-grid-item--gutters']//input[@class='oxd-input oxd-input--active']")
	WebElement empID_search;

	@FindBy(xpath = "//div[@class='oxd-form-row']//div[@class='oxd-grid-item oxd-grid-item--gutters'][4]//div[@class='oxd-select-text-input']")
	WebElement include_dropdown;

	@FindBy(xpath = "//div[@class='oxd-form-row']//div[@class='oxd-grid-item oxd-grid-item--gutters'][4]//div[@class='oxd-select-dropdown --positon-bottom']//span[text()='Current and Past Employees']")
	WebElement current_past;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement search_btn;

	@FindBy(xpath = "//a[text()='Tax Exemptions']")
	WebElement taxExemptions_page;

	// Federal incomtax
	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][1]//div[@class='oxd-grid-item oxd-grid-item--gutters'][1]//div[@class='oxd-select-text-input']")
	WebElement status_dropdown;

	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][1]//div[@class='oxd-grid-item oxd-grid-item--gutters'][1]//div[@class='oxd-select-dropdown --positon-bottom']//span")
	List<WebElement> status_list;

	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][1]//div[@class='oxd-grid-item oxd-grid-item--gutters'][2]//input")
	WebElement exemption_field;

	// State income tax
	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][2]//div[@class='oxd-grid-item oxd-grid-item--gutters'][1]//div[@class='oxd-select-text-input']")
	WebElement state_dropdown;

	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][2]//div[@class='oxd-grid-item oxd-grid-item--gutters'][1]//div[@class='oxd-select-dropdown --positon-bottom']//span")
	List<WebElement> state_list;

	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][2]//div[@class='oxd-grid-item oxd-grid-item--gutters'][2]//div[@class='oxd-select-text-input']")
	WebElement si_status_dropdown;

	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][2]//div[@class='oxd-grid-item oxd-grid-item--gutters'][2]//div[@class='oxd-select-dropdown --positon-bottom']//span")
	List<WebElement> si_status_list;

	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][2]//div[@class='oxd-grid-item oxd-grid-item--gutters'][3]//input")
	WebElement si_exemption_field;

	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][2]//div[@class='oxd-grid-item oxd-grid-item--gutters'][4]//div[@class='oxd-select-text-input']")
	WebElement unemployement_state_dropdown;

	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][2]//div[@class='oxd-grid-item oxd-grid-item--gutters'][4]//div[@class='oxd-select-dropdown --positon-bottom']//span")
	List<WebElement> unemp_state_list;

	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][2]//div[@class='oxd-grid-item oxd-grid-item--gutters'][5]//div[@class='oxd-select-text-input']")
	WebElement work_state_dropdown;

	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][2]//div[@class='oxd-grid-item oxd-grid-item--gutters'][5]//div[@class='oxd-select-dropdown --positon-bottom']//span")
	List<WebElement> work_state_list;

	@FindBy(xpath = "//form//button[@type='submit']")
	WebElement save_button;

	public Emp_TaxExemptionsPage() {
		PageFactory.initElements(driver, this);
	}

	// Tax Exemptions updation
	public void taxExemptionUpdations(String empID, String status, String exe, String sta, String si_sta, String si_exe,
			String unEmpSta , String workState) {

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

			clickElement(taxExemptions_page, "Salary page");

			clickElement(status_dropdown, "Status dropdown");
			for (WebElement drop : status_list) {
				if (drop.getText().equals(status)) {
					clickElement(drop, status + ": value");

					typeText(exemption_field, "Exemption field", exe);

					clickElement(state_dropdown, "State dropdown");
					for (WebElement state : state_list) {
						if (state.getText().equals(sta)) {
							clickElement(state, sta + ": value");

							clickElement(si_status_dropdown, "State status dropdown");
							for (WebElement si_status : si_status_list) {
								if (si_status.getText().equals(si_sta)) {
									clickElement(si_status, si_sta + ": value");

									typeText(si_exemption_field, "State income tax exemption", si_exe);

									clickElement(unemployement_state_dropdown, "Unemployment dropdown");
									for (WebElement un_emp : unemp_state_list) {
										if (un_emp.getText().equals(unEmpSta)) {
											clickElement(un_emp, unEmpSta + ": value");

											clickElement(work_state_dropdown, "Workstate dropdown");
											for (WebElement work_sta : work_state_list) {
												if (work_sta.getText().equals(workState)) {
													clickElement(work_sta, workState + ": value");

													clickElement(save_button, "Save Button");
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}

		} catch (Exception e) {

		}

	}
}
