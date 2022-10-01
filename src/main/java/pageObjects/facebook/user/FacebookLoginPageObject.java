package pageObjects.facebook.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import commons.BasePage;
import pageUIs.facebook.user.FacebookLoginPageUI;

public class FacebookLoginPageObject extends BasePage {

	private WebDriver driver;
	private WebDriverWait explicitWait;

	public FacebookLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public FacebookLoginPageObject(WebDriver driver, WebDriverWait explicitWait) {
		this.driver = driver;
		this.explicitWait = explicitWait;
	}

	public void clickToCreateNewAccountButton() {
		waitForElementClickable(driver, FacebookLoginPageUI.CREATE_NEW_ACCOUNT_BUTTON);
		clickToElement(driver, FacebookLoginPageUI.CREATE_NEW_ACCOUNT_BUTTON);
	}

	public boolean isEmailAddressTextboxDisplayed() {
		waitForElementVisible(driver, FacebookLoginPageUI.EMAIL_ADDRESS_TEXTBOX);
		return isElementDisplayed(driver, FacebookLoginPageUI.EMAIL_ADDRESS_TEXTBOX);
	}

	public void enterToEmailAddressTextbox(String emailAddress) {
		waitForElementVisible(driver, FacebookLoginPageUI.EMAIL_ADDRESS_TEXTBOX);
		sendkeyToElement(driver, FacebookLoginPageUI.EMAIL_ADDRESS_TEXTBOX, emailAddress);
	}

	public boolean isConfirmEmailAddressTextboxDisplayed() {

		return isElementDisplayed(driver, FacebookLoginPageUI.CONFIRM_EMAIL_ADDRESS_TEXTBOX);
	}

	public void clickCloseIconAtRegisterForm() {
		waitForElementClickable(driver, FacebookLoginPageUI.CLOSE_ICON);
		clickToElement(driver, FacebookLoginPageUI.CLOSE_ICON);
	}

	public boolean isConfirmEmailAddressTextboxUndisplayed() {
		return isElementUndisplayed(driver, FacebookLoginPageUI.CONFIRM_EMAIL_ADDRESS_TEXTBOX);
	}

}
