package orangeHRM.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import orangeHRM.utilities.Utils;

public class Emp_JobPage extends Utils {

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
	
	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][1]//div[@class='oxd-grid-item oxd-grid-item--gutters'][1]//input")
	WebElement joined_date_field;

	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][1]//div[@class='oxd-grid-item oxd-grid-item--gutters'][2]//div[@class='oxd-select-text-input']")
	WebElement job_titile_dropdown;

	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][1]//div[@class='oxd-grid-item oxd-grid-item--gutters'][2]//div[@class='oxd-select-dropdown --positon-bottom']//span")
	List<WebElement> job_title_list;

	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][1]//div[@class='oxd-grid-item oxd-grid-item--gutters'][4]//div[@class='oxd-select-text oxd-select-text--active']//div[1]")
	WebElement job_category_dropdown;

	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][1]//div[@class='oxd-grid-item oxd-grid-item--gutters'][4]//div[@class='oxd-select-dropdown --positon-bottom']//span")
	List<WebElement> job_category_list;

	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][1]//div[@class='oxd-grid-item oxd-grid-item--gutters'][5]//div[@class='oxd-select-text oxd-select-text--active']//div[1]")
	WebElement sub_unit_dropdown;

	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][1]//div[@class='oxd-grid-item oxd-grid-item--gutters'][5]//div[@class='oxd-select-dropdown --positon-bottom']//span")
	List<WebElement> sub_unit_list;

	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][1]//div[@class='oxd-grid-item oxd-grid-item--gutters'][6]//div[@class='oxd-select-text oxd-select-text--active']//div[1]")
	WebElement location_dropdown;

	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][1]//div[@class='oxd-grid-item oxd-grid-item--gutters'][6]//div[@class='oxd-select-dropdown --positon-bottom']//span")
	List<WebElement> location_list;

	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][1]//div[@class='oxd-grid-item oxd-grid-item--gutters'][7]//div[@class='oxd-select-text oxd-select-text--active']//div[1]")
	WebElement emp_status;

	@FindBy(xpath = "//form[@class='oxd-form']//div[@class='oxd-form-row'][1]//div[@class='oxd-grid-item oxd-grid-item--gutters'][7]//div[@class='oxd-select-dropdown --positon-bottom']//span")
	List<WebElement> emp_status_list;

	// Contract locators
	@FindBy(xpath = "//span[@class='oxd-switch-input oxd-switch-input--active --label-right']")
	WebElement contract_details_toggle;
	
	@FindBy(xpath="//form//div[@class='oxd-form-row'][2]//div[@class='oxd-grid-item oxd-grid-item--gutters'][1]//input")
	WebElement contract_start_field;
	
	@FindBy(xpath="//form//div[@class='oxd-form-row'][2]//div[@class='oxd-grid-item oxd-grid-item--gutters'][2]//input")
	WebElement contract_end_field;
	
	@FindBy(xpath="//form//button[@type='submit']")
	WebElement save_btn;
	
	

	public Emp_JobPage() {
		PageFactory.initElements(driver, this);
	}

	public void jobDetailsUpdation(String empID, String doj, String jobTile,String jobcat, String subUnit
			,String location, String empStatus, String conSrart, String  conEnd) {

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
			
			deleteText(joined_date_field);
			Thread.sleep(4000);
			typeText(joined_date_field, "Joined date field", doj);
			
			clickElement(job_titile_dropdown, "Job catagory dropdown");
			for(WebElement job: job_title_list) {
				if(job.getText().equals(jobTile)) {
					clickElement(job, jobTile+": value");
					
					clickElement(job_category_dropdown, "Job catagory dropdown");
					for(WebElement jobCat:job_category_list) {
						if(jobCat.getText().equals(jobcat)) {
							clickElement(jobCat, jobcat+": value");
							
							clickElement(sub_unit_dropdown, "Sub unit dropdown");
							for(WebElement sub_unit: sub_unit_list) {
								if(sub_unit.getText().equals(subUnit)) {
									clickElement(sub_unit, subUnit+": value");
									
									clickElement(location_dropdown, "Location dropdown");
									for(WebElement loc: location_list) {
										if(loc.getText().equals(location)) {
											clickElement(loc, location+": value");
											
											clickElement(emp_status, "Employee status dropdown");
											for(WebElement emp_sts: emp_status_list) {
												if(emp_sts.getText().equals(empStatus)) {
													clickElement(emp_sts, empStatus+": value");
													
													try {
														Thread.sleep(3000);
														clickElement(contract_details_toggle, "Contract details toggle");
														
														typeText(contract_start_field, "Contract start date", conSrart);
														typeText(contract_end_field, "Contract end date", conEnd);
														clickElement(save_btn, "Save button");
														
													} catch (Exception e) {
														
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
			}
			
			
		} catch (Exception e) {
			
		}

	}
}
