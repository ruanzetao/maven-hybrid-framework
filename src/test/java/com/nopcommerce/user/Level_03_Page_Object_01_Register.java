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
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_03_Page_Object_01_Register {

	private WebDriver driver;
	BasePage basePage;

	String projectPath = System.getProperty("user.dir");
	String emailAddress;

	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;

	String firstName;
	String lastName;
	String password;

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.gecko.driver",
				projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		// Init basePage
		basePage = new BasePage();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
		driver.manage().window().maximize();

		homePage = new UserHomePageObject(driver);
		registerPage = new UserRegisterPageObject(driver);


		firstName = "Automation";
		lastName = "FC";
		emailAddress = "afc" + generateFakeNumber() + "@mailinator.com";
		password = "123456";
	}

	@Test
	public void TC_01_Register_Empty_Data() {

		homePage.clickToRegisterLink();
		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getErrorMessageAtFirstnameTextbox(),
				"First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastnameTextbox(),
				"Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(),
				"Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(),
				"Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(),
				"Password is required.");
	}

	@Test
	public void TC_02_Invalid_Email() {
		homePage.clickToRegisterLink();
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox("afc123@!mailinator.com");
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}

	@Test
	public void TC_03_Register_Success() {
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

//	@Test
//	public void TC_04() {
//		driver.findElement(By.cssSelector("a.ico-register")).click();
//		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation");
//		driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
//		driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
//		driver.findElement(By.cssSelector("input#Password")).sendKeys("abc@!123");
//		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("abc@!123");
//		driver.findElement(By.cssSelector("button#register-button")).click();
//		Assert.assertEquals(driver.findElement(By.cssSelector("div.message-error li")).getText(),
//				"The specified email already exists");
//
//	}

//	@Test
//	public void TC_05_Register_Password_Less_Than_6_Chars() {
//		driver.findElement(By.cssSelector("a.ico-register")).click();
//		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation");
//		driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
//		driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
//		driver.findElement(By.cssSelector("input#Password")).sendKeys("abc12");
//		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("abc12");
//		driver.findElement(By.cssSelector("button#register-button")).click();
//		Assert.assertEquals(driver.findElement(By.cssSelector("#Password-error")).getText(),
//				"Password must meet the following rules: \nmust have at least 6 characters");
//	}

//	@Test
//	public void TC_06_Invalid_Comfirm_Password() {
//		driver.findElement(By.cssSelector("a.ico-register")).click();
//		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation");
//		driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
//		driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
//		driver.findElement(By.ccssSelector("input#Password")).sendKeys("abc@!123");
//		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("abc123");
//		driver.findElement(By.cssSelector("button#register-button")).click();
//		Assert.assertEquals(driver.findElement(By.cssSelector("#ConfirmPassword-error")).getText(),
//				"The password and confirmation password do not match.");
//	}

	public int generateFakeNumber() {
		Random rand = new Random();
		return (int) rand.nextInt(9999);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
