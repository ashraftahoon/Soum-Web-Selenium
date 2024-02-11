package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.ElementActions;

public class LoginPage  {

	private WebDriver driver;

	// constructor 
	public LoginPage(WebDriver driver) {
		this.driver=driver;
	}

	// Locators /////
	private static final By userName= By.id("user-name");
	private static final By Password= By.id("password");
	private static final By loginButton= By.id("login-button");
	//private static final By lockedInMessage=By.xpath("//button[@class='error-button']");
	private static final By lockedInMessage=By.xpath("//div[@class='error-message-container error']//child::h3");


	//// actions ////
	
	/// login fields
	public void loginUser(String username, String password) {
		
		ElementActions.typeText(driver, userName, username, Duration.ofSeconds(10));
		ElementActions.typeText(driver, Password, password, Duration.ofSeconds(10));
        ElementActions.clickElement(driver, loginButton, Duration.ofSeconds(10));

	}

 
	public String getErrorText() {
		return driver.findElement(lockedInMessage).getText();
		
		
	}

}
