package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.ElementActions;

public class ProductDetailsPage {

	private WebDriver driver;
	public ProductDetailsPage(WebDriver driver) {
		this.driver=driver;
	}

	private static final By backToProducts= By.id("back-to-products");
	private static final By price= By.xpath("//div[@id='root']//child::div[@class='inventory_details_price']");


    public String getText() {
    	return driver.findElement(HomePage.productName).getText();
    }
	
    public HomePage getbackToProducts() {
    	ElementActions.clickElement(driver, backToProducts, Duration.ofSeconds(10));
    	return new HomePage(driver);
    }
    
    public String getPrice() {
		return driver.findElement(price).getText();
	}
    
}




