package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Register;
import com.nopcommerce.common.Common_01_Register_Cookie;

import commons.BaseTest;
import pageObjects.nopCommerce.user.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_16_ShareData_Cookie extends BaseTest {

	private WebDriver driver;

	private String emailAddress;
	private String validPassword;

	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;

	@Parameters("browser")
	@BeforeClass(description = "Get data from Common_01_Register and Login to the System")
	public void beforeClass(String browserName) {

		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);
		registerPage = PageGeneratorManager.getRegisterPage(driver);

		emailAddress = Common_01_Register.emailAddress;
		validPassword = Common_01_Register.validPassword;

		// Login to System
		homePage.clickToRegisterLink();
		loginPage = homePage.clickToLoginLink();
		// Set Cookie to Login page
		loginPage.setCookie(driver, Common_01_Register_Cookie.loggedCookies);
		loginPage.sleepInSecond(3);
		loginPage.refreshCurrentPage(driver);

		// Verify Login successful
		verifyTrue(homePage.isMyAccountLinkDisplayed());
		// System.out.println("Cookie at Test: " + Common_01_Register_Cookie.loggedCookies);
	}

	@Test
	public void Search_01_Empty_Data() {

	}

	@Test
	public void Search_02_Relative_Product_Name() {

	}

	@Test
	public void Search_03_Absolute_Product_Name() {

	}

	@Test
	public void Search_04_Parent_Category() {

	}

	@Test
	public void Search_05_Incorrect_Manufacturer() {

	}

	@Test
	public void Search_06_Correct_Manufacturer() {

	}

	public int generateFakeNumber() {
		Random rand = new Random();
		return (int) rand.nextInt(9999);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
