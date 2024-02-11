package Pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import utils.ElementActions;

public class HomePage {

	private WebDriver driver;
	private Select select;
	public HomePage(WebDriver driver) {
		this.driver=driver;
	}

	public static  final By productName= By.xpath("//div[text()='Sauce Labs Backpack']");
	private static final By addProductBtn1= By.id("add-to-cart-sauce-labs-backpack");
	private static final By addProductBtn2= By.id("add-to-cart-sauce-labs-fleece-jacket");
	private static final By openCart= By.id("shopping_cart_container");
	private static final By sortDropdown= By.xpath("//select[@class='product_sort_container']");
	private static final By removeButton= By.id("remove-sauce-labs-backpack");
	private static final By backToProductBtn= By.id("back-to-products");
	private static final By menuIcon=By.id("react-burger-menu-btn");
	private static final By loginOutButtn=By.id("logout_sidebar_link");


	// function to open cart 
	public AddCartPage openCart() {
		ElementActions.clickElement(driver, openCart, Duration.ofSeconds(10));
		return new AddCartPage(driver);
	}


	// click on  add product button  from home page to add it to cart 
	public AddCartPage addProductToCart(){
		ElementActions.clickElement(driver, addProductBtn1, Duration.ofSeconds(10));
		return new AddCartPage(driver);
	}



	// click on link or product name from home page to open product details
	public  ProductDetailsPage openProductDetailsPage() {
		ElementActions.clickElement(driver, productName, Duration.ofSeconds(10));
		return new ProductDetailsPage(driver);
	}

	/// select drop down sorting
	public void selectSortingOption(String option) {

		select = new Select(driver.findElement(sortDropdown));
		select.selectByVisibleText(option);
	}




	public List<String> getProductNames() {
		List<String> productNames = new ArrayList<>();


		List<WebElement> nameElements = driver.findElements(sortDropdown);
		for (WebElement element : nameElements) {
			productNames.add(element.getText());
		}
		return productNames;
	}


	public boolean isSortedAscending() {
		List<WebElement> itemList = driver.findElements(sortDropdown);
		for (int i = 0; i < itemList.size() - 1; i++) {
			String currentItem = itemList.get(i).getText();
			String nextItem = itemList.get(i + 1).getText();
			if (currentItem.compareTo(nextItem) > 0) {
				return false;
			}
		}
		return true;
	}


	public boolean isSortedDescending() {
		List<WebElement> itemList = driver.findElements(sortDropdown);
		for (int i = 0; i < itemList.size() - 1; i++) {
			String currentItem = itemList.get(i).getText();
			String nextItem = itemList.get(i + 1).getText();
			if (currentItem.compareTo(nextItem) < 0) {
				return false;
			}
		}
		return true;
	}

	public boolean isSortedHighToLow() {
		List<WebElement> itemList = driver.findElements(By.className("item"));
		for (int i = 0; i < itemList.size() - 1; i++) {
			String currentItem = itemList.get(i).getText();
			String nextItem = itemList.get(i + 1).getText();
			double currentPrice = Double.parseDouble(currentItem.replaceAll("[^0-9.]", ""));
			double nextPrice = Double.parseDouble(nextItem.replaceAll("[^0-9.]", ""));
			if (currentPrice < nextPrice) {
				return false;
			}
		}
		return true;
	}


	public boolean isSortedLowToHigh() {
		List<WebElement> itemList = driver.findElements(By.className("item"));
		for (int i = 0; i < itemList.size() - 1; i++) {
			String currentItem = itemList.get(i).getText();
			String nextItem = itemList.get(i + 1).getText();
			double currentPrice = Double.parseDouble(currentItem.replaceAll("[^0-9.]", ""));
			double nextPrice = Double.parseDouble(nextItem.replaceAll("[^0-9.]", ""));
			if (currentPrice > nextPrice) {
				return false;
			}
		}
		return true;
	}

	// remove product from home page  // 
	public void removeProductFromHome() {

		ElementActions.clickElement(driver, addProductBtn1, Duration.ofSeconds(10));
		ElementActions.clickElement(driver, removeButton, Duration.ofSeconds(10));
	}

	//  // Method to check if the cart is empty or not
	public boolean isCartEmpty() {
		driver.findElement(openCart).click();
		List<WebElement> products = driver.findElements(By.xpath("//div[@class='cart_item']"));
		return products.isEmpty();
	}

	public void removeProductFromCart() {
		addProductToCart();
		ElementActions.clickElement(driver, removeButton, Duration.ofSeconds(10));
	}



	/// check that add product button can change it from remove to add product or not in the home page/////

	public  void  CheckElementClickable() {
		ElementActions.clickElement(driver, addProductBtn1, Duration.ofSeconds(10));//  add product to cart 
		ElementActions.clickElement(driver, removeButton, Duration.ofSeconds(10));//  // remove product from cart by clicking double click on add button from home page then check that cart is empty 
	}


	/// check that add product button can change it from remove to add product or not in the product details page/////
	public boolean isElementClickable() {

		try {
			// Check if the element is displayed and enabled
			if (driver.findElement(addProductBtn2).isDisplayed() && driver.findElement(addProductBtn2).isEnabled()) {	
				return true; // If element is displayed, enabled, and clickable, it's clickable
			} else {
				return false; // If element is not displayed or enabled, it's not clickable
			}
		} catch (Exception e) {
			return false; // If any exception occurs, the element is not clickable
		}
	}

	

	//// logout from the system ////
	public LoginPage logOut() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		ElementActions.clickElement(driver, menuIcon, Duration.ofSeconds(10));
		ElementActions.clickElement(driver, loginOutButtn, Duration.ofSeconds(10));
		return new LoginPage(driver);
	}


	/// get title of the page 
	public  String  getTitle() {   
		return   driver.getTitle();    

	}


}












