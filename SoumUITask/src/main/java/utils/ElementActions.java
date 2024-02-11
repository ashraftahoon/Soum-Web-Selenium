package utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementActions {

	public WebDriver driver;

	public enum SelectType {
		TEXT, VALUE;
	}

	public ElementActions(WebDriver driver) {
		this.driver = driver;
	}

	public static void clickElement(WebDriver driver, By locator, Duration timeout) {
        try {
            WebElement element = new WebDriverWait(driver, timeout)
                    .until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
	
	

//	public static void type(WebDriver driver, By elementLocator, String text, boolean clearBeforeTyping) {
//		try {
//			// Clear before typing condition
//			if (!driver.findElement(elementLocator).getAttribute("value").isEmpty() && clearBeforeTyping) {
//				driver.findElement(elementLocator).clear();
//				// We type here! :D
//				driver.findElement(elementLocator).sendKeys(text);
//				// Make sure that the data is inserted correctly to the field
//				Assert.assertTrue(driver.findElement(elementLocator).getAttribute("value").contains(text),
//						"The data is not inserted successfully to the field, the inserted data should be: [" + text
//						+ "]; But the current field value is: ["
//						+ driver.findElement(elementLocator).getAttribute("value") + "]");
//			}
//		
//
//		} catch (Exception e) {
//		    Logger.logStep(e.getMessage());
//		    fail(e.getMessage());
//		}
//		
//	}
	
	
	 public static void typeText(WebDriver driver, By locator, String text, Duration timeout) {
	        try {
	            WebElement element = new WebDriverWait(driver, timeout)
	                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
	            element.clear(); // Clear existing text if any
	            element.sendKeys(text);
	        } catch (Exception e) {
	            System.out.println("Error: " + e.getMessage());
	        }
	    }

	
	    public static String getText(WebDriver driver, By elementLocator) {
		try {
			clickElement(driver, elementLocator, Duration.ofSeconds(20));
		    String text = driver.findElement(elementLocator).getText();
		    return text;
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		return null;
	    }

	

	    public static boolean clickElementByAction(WebDriver driver, WebElement element) {
	    	
	        Actions actions = new Actions(driver);
	        actions.moveToElement(element).doubleClick().build().perform();;
			return true;
	    }
}
		


		

	

