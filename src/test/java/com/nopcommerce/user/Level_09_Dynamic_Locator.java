package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageObjects.nopCommerce.user.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;

public class Level_09_Dynamic_Locator extends BaseTest {

	private WebDriver driver;
	BasePage basePage;

	String projectPath = System.getProperty("user.dir");
	String emailAddress;

	String firstName;
	String lastName;
	String password;

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		// Init basePage
		// getBrowserDriver();
		basePage = new BasePage();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
		driver.manage().window().maximize();

		homePage = new UserHomePageObject(driver);
		registerPage = new UserRegisterPageObject(driver);
		customerInforPage = new UserCustomerInforPageObject(driver);

		firstName = "Automation";
		lastName = "FC";
		emailAddress = "afc" + generateFakeNumber() + "@mailinator.com";
		password = "123456";
	}

	@Test
	public void User_01_Register() {
		homePage.clickToRegisterLink();
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		registerPage.clickToLogoutLink();
	}

	@Test
	public void User_02_Login() {
		homePage.clickToRegisterLink();
		loginPage = homePage.clickToLoginLink();
		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();

		// Assert something
		// Assert.assertTrue(customerInforPage.isCustomerInforPageDisplayed());
	}

	@Test
	public void User_03_Customer_Infor() {
		customerInforPage = homePage.clickToMyAccountLink();
		Assert.assertTrue(customerInforPage.isCustomerInforPageDisplayed());
	}

	@Test
	public void User_04_Switch_Page() {
		// Customer Infor => Address
		addressPage = customerInforPage.openAddressPage(driver);
		// Address => My product review
		myProductReviewPage = addressPage.openMyProductReviewPage(driver);
		// My prod review => reward point
		rewardPointPage = myProductReviewPage.openRewardPointPage(driver);
		// Reward point => Address
		addressPage = rewardPointPage.openAddressPage(driver);
		// My product review => Address
		addressPage = myProductReviewPage.openAddressPage(driver);
		// Address => Reward
		rewardPointPage = addressPage.openRewardPointPage(driver);
		// Reward => My product review
		myProductReviewPage = rewardPointPage.openMyProductReviewPage(driver);
	}

	@Test
	public void User_04_Dynamic_Page_01() {
		// My prod review => reward point update
		rewardPointPage = (UserRewardPointPageObject) myProductReviewPage.openMyAccountPagebyName(driver, "Reward points");
		// Reward point => Address
		addressPage = (UserAddressPageObject) rewardPointPage.openMyAccountPagebyName(driver, "Addresses");
		// Address => Reward
		rewardPointPage = (UserRewardPointPageObject) addressPage.openMyAccountPagebyName(driver, "Reward points");
		// Reward => My product review
		myProductReviewPage = (UserMyProductReviewPageObject) rewardPointPage.openMyAccountPagebyName(driver, "My product reviews");
	}

	@Test
	public void User_04_Dynamic_Page_02() {
		// Customer infor => My product review:
		customerInforPage.openMyAccountPagebyName(driver, "My product reviews");
		myProductReviewPage = PageGeneratorManager.getMyProductReviewPage(driver);

		// My prod review => reward point
		myProductReviewPage.openMyAccountPagebyName(driver, "Reward points");
		rewardPointPage = PageGeneratorManager.getRewardPointPage(driver);

		// Reward point => Address
		rewardPointPage.openMyAccountPagebyName(driver, "Addresses");
		addressPage = PageGeneratorManager.getAddressPage(driver);

		// Address => Reward
		addressPage.openMyAccountPagebyName(driver, "Reward points");
		rewardPointPage = PageGeneratorManager.getRewardPointPage(driver);

		// Reward => My product review
		rewardPointPage.openMyAccountPagebyName(driver, "My product reviews");
		myProductReviewPage = PageGeneratorManager.getMyProductReviewPage(driver);
	}

	public int generateFakeNumber() {
		Random rand = new Random();
		return (int) rand.nextInt(9999);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInforPageObject customerInforPage;
	private UserAddressPageObject addressPage;
	private UserMyProductReviewPageObject myProductReviewPage;
	private UserRewardPointPageObject rewardPointPage;
}
