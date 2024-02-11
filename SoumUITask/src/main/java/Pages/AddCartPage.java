package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import utils.ElementActions;

public class AddCartPage {

	private WebDriver driver;
	public AddCartPage(WebDriver driver) {
		this.driver=driver;
	}

	private static final By productName= By.xpath("//a[@id='item_4_title_link']//child::div");
	private static final By checkOutButton= By.id("checkout");
	private static final By continueShpoingBtun= By.id("continue-shopping");
    
	// get name product
	public String getNameProduct() {
		return driver.findElement(productName).getText();
	}

   // open check out page by click on button check out from cart page
	public CheckOutPage openCheckOutPage() {

		ElementActions.clickElement(driver, checkOutButton, Duration.ofSeconds(20));
		return new CheckOutPage(driver);
	}


	///  assert that my cart is empty
	public boolean CheckOutEmptyCart() {
		
		return driver.findElement(checkOutButton).isEnabled();
	}

	/// get back to home by clicking on continue button in cart page 
	public HomePage ReturnBackToShoping() {
		
		ElementActions.clickElement(driver, continueShpoingBtun, Duration.ofSeconds(20));
		return new HomePage(driver);
	}












}
