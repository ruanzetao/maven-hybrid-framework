package com.nopcommerce.common;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import pageObjects.nopCommerce.user.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Common_01_Register extends BaseTest {

	private WebDriver driver;

	public static String emailAddress;
	public static String validPassword;
	private String firstName;
	private String lastName;

	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;

	@Parameters("browser")
	@BeforeTest(description = "Create new Account for all Class Test")
	public void beforeClass(String browserName) {

		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);
		registerPage = PageGeneratorManager.getRegisterPage(driver);

		firstName = "Automation";
		lastName = "FC";
		emailAddress = "afc" + generateFakeNumber() + "@mailinator.com";
		validPassword = "123456";

		// Start to Register account
		registerPage = homePage.clickToRegisterLink();
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(validPassword);
		registerPage.inputToConfirmPasswordTextbox(validPassword);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		registerPage.clickToLogoutLink();
	}

//	@Test
//	public void TC_01_Register_Success() {
//		registerPage = homePage.clickToRegisterLink();
//		registerPage.inputToFirstnameTextbox(firstName);
//		registerPage.inputToLastnameTextbox(lastName);
//		registerPage.inputToEmailTextbox(emailAddress);
//		registerPage.inputToPasswordTextbox(validPassword);
//		registerPage.inputToConfirmPasswordTextbox(validPassword);
//		registerPage.clickToRegisterButton();
//		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
//		registerPage.clickToLogoutLink();
//	}

//	@Test
//	public void TC_02_Login_Success() {
//		homePage.clickToRegisterLink();
//		loginPage = homePage.clickToLoginLink();
//		loginPage.inputToEmailTextbox(emailAddress);
//		loginPage.inputToPasswordTextbox(validPassword);
//
//	}

	public int generateFakeNumber() {
		Random rand = new Random();
		return (int) rand.nextInt(9999);

	}

	@AfterTest
	public void afterClass() {
		driver.quit();
	}

}
