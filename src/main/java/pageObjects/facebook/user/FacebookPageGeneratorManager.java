package pageObjects.facebook.user;

import org.openqa.selenium.WebDriver;

public class FacebookPageGeneratorManager {
	public static FacebookLoginPageObject getLoginPage(WebDriver driver) {
		return new FacebookLoginPageObject(driver);
	}

}
