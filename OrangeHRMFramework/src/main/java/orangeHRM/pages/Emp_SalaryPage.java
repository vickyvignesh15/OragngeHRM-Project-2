package orangeHRM.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import orangeHRM.utilities.Utils;

public class Emp_SalaryPage extends Utils {

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

	@FindBy(xpath = "//a[text()='Salary']")
	WebElement salary_page;

	@FindBy(xpath = "//div[1]/div/div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']/div[1]/button[1]")
	WebElement add_btn;

	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][1]//div[@class='oxd-grid-item oxd-grid-item--gutters'][1]//input")
	WebElement salary_comp;

	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][1]//div[@class='oxd-grid-item oxd-grid-item--gutters'][2]//div[@class='oxd-select-text-input']")
	WebElement pay_grade;

	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][1]//div[@class='oxd-grid-item oxd-grid-item--gutters'][2]//div[@class='oxd-select-dropdown --positon-bottom']//span")
	List<WebElement> pay_grade_list;

	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][1]//div[@class='oxd-grid-item oxd-grid-item--gutters'][3]//div[@class='oxd-select-text oxd-select-text--active']//div[1]")
	WebElement pay_freq;

	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][1]//div[@class='oxd-grid-item oxd-grid-item--gutters'][3]//div[@class='oxd-select-dropdown --positon-bottom']//span")
	List<WebElement> pay_freq_list;

	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][1]//div[@class='oxd-grid-item oxd-grid-item--gutters'][4]//div[@class='oxd-select-text oxd-select-text--active']//div[1]")
	WebElement currency;

	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][1]//div[@class='oxd-grid-item oxd-grid-item--gutters'][4]//div[@class='oxd-select-dropdown --positon-bottom']//span")
	List<WebElement> currency_list;

	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][1]//div[@class='oxd-grid-item oxd-grid-item--gutters'][5]//input")
	WebElement amount_field;
	
	//Account details
	@FindBy(xpath="//span[@class='oxd-switch-input oxd-switch-input--active --label-right']")
	WebElement deposit_details_toggle;
	
	@FindBy(xpath="//form//div[@class='oxd-form-row'][3]//div[@class='oxd-grid-item oxd-grid-item--gutters'][1]//input")
	WebElement account_num_field;
	
	@FindBy(xpath="//form//div[@class='oxd-form-row'][3]//div[@class='oxd-grid-item oxd-grid-item--gutters'][2]//div[@class='oxd-select-text-input']")
	WebElement account_type_dropdown;
	
	@FindBy(xpath="//form//div[@class='oxd-form-row'][3]//div[@class='oxd-grid-item oxd-grid-item--gutters'][2]//div[@class='oxd-select-dropdown --positon-bottom']//span")
	List<WebElement> account_type_list;
	
	@FindBy(xpath="//form//div[@class='oxd-form-row'][3]//div[@class='oxd-grid-3 orangehrm-full-width-grid'][2]//div[@class='oxd-grid-item oxd-grid-item--gutters'][1]//div//input")
	WebElement routing_num_field;
	
	@FindBy(xpath="//form//div[@class='oxd-form-row'][3]//div[@class='oxd-grid-3 orangehrm-full-width-grid'][2]//div[@class='oxd-grid-item oxd-grid-item--gutters'][2]//input")
	WebElement amount_field2;
	
	@FindBy(xpath="//form//button[@type='submit']")
	WebElement save_btn;

	public Emp_SalaryPage() {
		PageFactory.initElements(driver, this);
	}

	// Salary updation
	public void salaryUpdation(String empID, String salaryComp, String payGrade
			,String payFreq,String Currency,String amount,String account_num,String accountType
			,String routNum) {
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

			clickElement(salary_page, "Salary page");
			clickElement(add_btn, "Add button");

			typeText(salary_comp, "Salary component field", salaryComp);

			clickElement(pay_grade, "Pay grade dropdown");
			for (WebElement pay : pay_grade_list) {
				if (pay.getText().equals(payGrade)) {
					clickElement(pay, payGrade + ": value");
					
					clickElement(pay_freq, "Pay frequency");
					for(WebElement pay_f:pay_freq_list) {
						if(pay_f.getText().equals(payFreq)) {
							clickElement(pay_f,payFreq+": value" );
							
							clickElement(currency, "Currency field");
							for(WebElement cur:currency_list) {
								if(cur.getText().equals(Currency)) {
									clickElement(cur, Currency+": value");
									
									typeText(amount_field, "Amount field", amount);
									
									try {
										Thread.sleep(3000);
										clickElement(deposit_details_toggle, "Contract details toggle");
										
										typeText(account_num_field, "Account number field", account_num);
										
										clickElement(account_type_dropdown, "Account type dropdown");
										for(WebElement acc_type:account_type_list) {
											if(acc_type.getText().equals(accountType)) {
												clickElement(acc_type, accountType+": value");
												
												typeText(routing_num_field, "Routing number field", routNum);
												typeText(amount_field2, "Amount filed", amount);
												
												clickElement(save_btn, "Save button");
											}
										}
										
									} catch (Exception e) {
										
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