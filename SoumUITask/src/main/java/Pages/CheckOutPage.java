package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.ElementActions;

public class CheckOutPage {


	private WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		this.driver=driver;
	}

	private static final By firstName= By.id("first-name");
	private static final By lastName= By.id("last-name");
	private static final By zibCode= By.id("postal-code");
	private static final By continueButton= By.id("continue");
	private static final By finishButton= By.id("finish");
	private static final By checkOutStatus= By.xpath("//div[@id='header_container']//child::span");
	private static final By backToHomeBtn= By.id("back-to-products");
	private static final By ErrorTextElement= By.xpath("//*[text()='Error: Last Name is required']");


  /// insert data in check out fields 
	public void fillCheckOutFields(String fname, String lname, String code)  {
		ElementActions.typeText(driver, firstName, fname,Duration.ofSeconds(10));
		ElementActions.typeText(driver, lastName, lname,Duration.ofSeconds(10));
		ElementActions.typeText(driver, zibCode, code,Duration.ofSeconds(10));
	}

	// navigation
	public void clickOnContinueButn() {

		ElementActions.clickElement(driver, continueButton, Duration.ofSeconds(10));
	}

	// navigation 
	public void clickOnFinshButn() {

		ElementActions.clickElement(driver, finishButton, Duration.ofSeconds(10));
	}

	
	// assert by status of checkout 
	public String getcheckoutStatus() {
		return driver.findElement(checkOutStatus).getText();
	}

	/// assert by get attribute to ensure that your input inserted as expected 
	public String getFirstName() {

		return driver.findElement(firstName).getAttribute("value");
	}
	
	
	/// assert by get attribute to ensure that your input inserted as expected 
	public String getLastName() {
		return driver.findElement(lastName).getAttribute("value");
	}

	/// assert by get attribute to ensure that your input inserted as expected 
	public String getCode() {
		return driver.findElement(zibCode).getAttribute("value");
	}

	/// assert also by validation error message  
	public String getTextErrorOflastName() {
		return driver.findElement(ErrorTextElement).getText();
	}

	/// asert to find finish button is clickable or not 
	public boolean getabilityFinishBution() {
		return driver.findElement(finishButton).isEnabled();
	}

	/// return back to home page 
	public  HomePage  backToHome() {
		ElementActions.clickElement(driver, backToHomeBtn, Duration.ofSeconds(10));
		return new HomePage(driver);
	}
	



}
