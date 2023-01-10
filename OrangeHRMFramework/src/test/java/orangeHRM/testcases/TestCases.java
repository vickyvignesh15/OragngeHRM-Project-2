package orangeHRM.testcases;

import org.testng.annotations.Test;

import orangeHRM.base.BaseClass;
import orangeHRM.pages.AdminPage;
import orangeHRM.pages.Emp_ActivationPage;
import orangeHRM.pages.Emp_ContactDetailsPage;
import orangeHRM.pages.Emp_DependentsPage;
import orangeHRM.pages.Emp_DetailsPage;
import orangeHRM.pages.Emp_EmergencyContactPage;
import orangeHRM.pages.Emp_JobPage;
import orangeHRM.pages.Emp_PersonalDetailsPage;
import orangeHRM.pages.Emp_SalaryPage;
import orangeHRM.pages.Emp_TaxExemptionsPage;
import orangeHRM.pages.Emp_TerminatePage;
import orangeHRM.pages.LoginPage;
import orangeHRM.pages.UserCreationPage;
import orangeHRM.utilities.ExcelReader;

public class TestCases extends BaseClass {

	@Test
	public void loginTest() {
		LoginPage lp = new LoginPage();

		try {
			lp.login(getPropValue("username"), getPropValue("password"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(dataProviderClass = ExcelReader.class, dataProvider = "GetData")
	public void TC_PIM_01(String keyword) {

		LoginPage lp = new LoginPage();
		lp.login(getPropValue("username"), getPropValue("password"));

		try {
			test = test.createNode(keyword);
			lp.keywordSearch(keyword);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void DropDown_TC_PIM_02() {
		LoginPage lp = new LoginPage();
		AdminPage ap = new AdminPage();

		try {
			lp.login(getPropValue("username"), getPropValue("password"));
			ap.dropdownValidation();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(dataProviderClass = ExcelReader.class, dataProvider = "GetData")
	public void NewUserCreation_TC_PIM_03(String fname, String mname, String lname, String empID, String uname,
			String psw) {
		LoginPage lp = new LoginPage();
		UserCreationPage up = new UserCreationPage();

		try {
			lp.login(getPropValue("username"), getPropValue("password"));
			up.userCreation(fname, mname, lname, empID, uname, psw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void empDeatilsTab_TC_PIM_04() {
		LoginPage lp = new LoginPage();
		Emp_DetailsPage emp = new Emp_DetailsPage();

		try {
			lp.login(getPropValue("username"), getPropValue("password"));
			emp.empDetailsTab("1523");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(dataProviderClass = ExcelReader.class, dataProvider = "GetData")
	public void EmpDetailsUpdation_TC_PIM_05(String empID, String nickName, String otherID, String lic_Number,
			String lic_ex_date, String ssn_num, String sin_num, String nat, String mar_status, String dob,
			String gender, String miltSer, String blood_grp) {
		LoginPage lp = new LoginPage();
		Emp_PersonalDetailsPage epd = new Emp_PersonalDetailsPage();

		try {
			lp.login(getPropValue("username"), getPropValue("password"));
			epd.empDetailsUpdation(empID, nickName, otherID, lic_Number, lic_ex_date, ssn_num, sin_num, nat, mar_status,
					dob, gender, miltSer, blood_grp);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(dataProviderClass = ExcelReader.class, dataProvider = "GetData")
	public void EmpContactDetails_TC_PIM_06(String empID, String street1, String street2, String city, String State,
			String postalcode, String country, String homeNum, String mobNum, String workNum, String email,
			String workMail) {
		LoginPage lp = new LoginPage();
		Emp_ContactDetailsPage ecp = new Emp_ContactDetailsPage();
		try {
			lp.login(getPropValue("username"), getPropValue("password"));
			ecp.empContactDetailsUpdation(empID, street1, street2, city, State, postalcode, country, homeNum, mobNum,
					workNum, email, workMail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(dataProviderClass = ExcelReader.class, dataProvider = "GetData")
	public void EmergencyConcactUpda_TC_PIM_07(String empID, String name, String relationship, String homeNum,
			String mobNum, String workTele) {
		LoginPage lp = new LoginPage();
		Emp_EmergencyContactPage eec = new Emp_EmergencyContactPage();
		try {
			lp.login(getPropValue("username"), getPropValue("password"));
			eec.empEmergencyContactsUpdation(empID, name, relationship, homeNum, mobNum, workTele);
		} catch (Exception e) {

		}
	}

	@Test(dataProviderClass = ExcelReader.class, dataProvider = "GetData")
	public void DependentsUpdation_TC_PIM_08(String empID, String name, String relationship, String dob) {
		LoginPage lp = new LoginPage();
		Emp_DependentsPage edp = new Emp_DependentsPage();
		try {
			lp.login(getPropValue("username"), getPropValue("password"));
			edp.empDependententsUpdation(empID, name, relationship, "1997-01-15");

		} catch (Exception e) {

		}

	}

	@Test(dataProviderClass = ExcelReader.class, dataProvider = "GetData")
	public void EmpJobDetailsUpdation_TC_PIM_09(String empID, String joinDate, String jobTile, String jobSpe,
			String jobCat, String subUnit, String location, String empSta, String conStaDate, String conEndDate) {
		LoginPage lp = new LoginPage();
		Emp_JobPage ejp = new Emp_JobPage();
		try {
			lp.login(getPropValue("username"), getPropValue("password"));
			ejp.jobDetailsUpdation(empID, "2023-01-09", jobTile, jobCat, subUnit, location, empSta, "2023-01-09",
					"2025-01-09");
		} catch (Exception e) {

		}
	}

	@Test(dataProviderClass = ExcelReader.class, dataProvider = "GetData")
	public void EmpTermination_TC_PIM_10(String empID, String resignDate, String reason) {
		LoginPage lp = new LoginPage();
		Emp_TerminatePage et = new Emp_TerminatePage();
		try {
			lp.login(getPropValue("username"), getPropValue("password"));
			et.empTermination(empID, "2023-01-10", reason);
		} catch (Exception e) {

		}
	}

	@Test
	public void empActivation_TC_PIM_11() {
		LoginPage lp = new LoginPage();
		Emp_ActivationPage eap = new Emp_ActivationPage();
		try {
			lp.login(getPropValue("username"), getPropValue("password"));
			eap.activateEmp("1523");
		} catch (Exception e) {

		}
	}

	@Test(dataProviderClass = ExcelReader.class, dataProvider = "GetData")
	public void EmpSalaryUpdation_TC_PIM_12(String empID, String component, String payGrade, String payFreq,
			String currency, String amount, String accountNum, String accountType, String routingNum) {
		LoginPage lp = new LoginPage();
		Emp_SalaryPage esp = new Emp_SalaryPage();
		try {
			lp.login(getPropValue("username"), getPropValue("password"));
			esp.salaryUpdation(empID, component, payGrade, payFreq, currency, amount, accountNum, accountType,
					routingNum);
		} catch (Exception e) {

		}

	}

	@Test
	public void Emp_TaxExemptions_TC_PIM_13() {
		LoginPage lp = new LoginPage();
		Emp_TaxExemptionsPage etp = new Emp_TaxExemptionsPage();
		try {
			lp.login(getPropValue("username"), getPropValue("password"));
			etp.taxExemptionUpdations("1523", "Single", "10", "Alaska", "Single", "10", "Alaska", "Alaska");
		} catch (Exception e) {

		}

	}
}
