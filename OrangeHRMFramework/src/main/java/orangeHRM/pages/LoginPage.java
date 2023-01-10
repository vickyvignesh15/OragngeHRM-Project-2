package orangeHRM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import orangeHRM.utilities.Utils;

public class LoginPage extends Utils{

	// Login
	@FindBy(xpath = "//input[@name='username']")
	WebElement username;

	@FindBy(xpath = "//input[@name='password']")
	WebElement password;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement login_btn;
	
	// Keyword searching elements
	@FindBy(xpath="//input[@placeholder='Search']")
	WebElement search_field;
	
	@FindBy(xpath="//a[@class='oxd-main-menu-item']//span")
	WebElement keyResult;
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Login Action
	public void login(String uname,String psw) {
		typeText(username, "Username Field", uname);
		typeText(password, "Password Field", psw);
		clickElement(login_btn, "Login Button");
		
	}
	
	//Keyword search
	public void keywordSearch(String keyword) {
		typeText(search_field, "Search field", keyword);
		
		try {
			
			if(keyResult.isDisplayed()) {
				test.log(Status.PASS, keyword+ ": Serach result found");
				deleteText(search_field);
			}else {
				test.log(Status.FAIL, keyword+ ": Serach result is not found");
			}
			
		}catch(Exception e) {
			
		}
	}
}
