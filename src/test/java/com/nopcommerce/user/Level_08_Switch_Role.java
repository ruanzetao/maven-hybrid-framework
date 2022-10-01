package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import commons.GlobalConstants;
import pageObjects.nopCommerce.admin.AdminDashboardPageObject;
import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.user.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_08_Switch_Role extends BaseTest {

	private WebDriver driver;
	BasePage basePage;

	String projectPath = System.getProperty("user.dir");
	String userEmailAddress;

	String firstName;
	String lastName;
	String userPassword;

	private String adminEmailAddress, adminPassword;

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		// Init basePage
		// getBrowserDriver();
		basePage = new BasePage();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(GlobalConstants.PORTAL_TESTING_URL);
		driver.manage().window().maximize();

		userHomePage = new UserHomePageObject(driver);
		registerPage = new UserRegisterPageObject(driver);

		firstName = "Automation";
		lastName = "FC";
		userEmailAddress = "afc" + generateFakeNumber() + "@mailinator.com";
		userPassword = "123456";
		// Generate account

		adminEmailAddress = "admin@yourstore.com";
		adminPassword = "admin";
		// Update
	}

	@Test
	public void Role_01_User() {
		userLoginPage = userHomePage.openLoginPage();
		userHomePage = userLoginPage.loginAsUser(userEmailAddress, userPassword);

		// Assert something
		// Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
	}

	@Test
	public void Role_02_Admin() {
		// Role User => role Admin
		// Role Admin => role User

		userHomePage.openPageUrl(driver, GlobalConstants.ADMIN_TESTING_URL);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		adminLoginPage.loginAsAdmin(userEmailAddress, userPassword);
		// Assert something
		// Assert.assertTrue(adminLoginPage.isDashboardHeaderDisplayed());
	}

	public int generateFakeNumber() {
		Random rand = new Random();
		return (int) rand.nextInt(9999);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private UserHomePageObject userHomePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject userLoginPage;
	private AdminDashboardPageObject adminDashboardPage;
	private AdminLoginPageObject adminLoginPage;
}
