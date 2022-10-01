package com.facebook.register;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.facebook.user.FacebookLoginPageObject;
import pageObjects.facebook.user.FacebookPageGeneratorManager;

public class Level_13_Element_Undisplayed extends BaseTest {

	private WebDriver driver;
	private FacebookLoginPageObject loginPage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = FacebookPageGeneratorManager.getLoginPage(driver);
	}

	@Test
	public void TC_01_Verify_Element_Displayed() {
		loginPage.clickToCreateNewAccountButton();
		verifyTrue(loginPage.isEmailAddressTextboxDisplayed());
	}

	@Test
	public void TC_01_Verify_Element_UnDisplayed_In_DOM() {
		// Verify true
		loginPage.enterToEmailAddressTextbox("automationfc@mailinator.com");
		loginPage.sleepInSecond(3);
		verifyTrue(loginPage.isConfirmEmailAddressTextboxDisplayed());

		// C1: Verify true - cho hàm trả về là Undisplayed => Tự làm
		// C2: Verify false - cho hàm trả về là Displayed
		loginPage.enterToEmailAddressTextbox("");
		loginPage.sleepInSecond(3);
		verifyFalse(loginPage.isConfirmEmailAddressTextboxDisplayed());

	}

	@Test
	public void TC_03_Verify_Element_UnDisplayed_Not_In_DOM() {
		loginPage.clickCloseIconAtRegisterForm();
		loginPage.sleepInSecond(3);
		// C2: Verify false - cho hàm trả về là Displayed
		// verifyFalse(loginPage.isConfirmEmailAddressTextboxDisplayed());

		verifyTrue(loginPage.isConfirmEmailAddressTextboxUndisplayed());
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
