package utils;

import static org.testng.Assert.fail;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helper {
	
	  private static int getTimeout() {
			return Integer.parseInt(System.getProperty("webdriver.wait"));
		    }

		    public static WebDriverWait getExplicitWait(WebDriver driver) {
			return new WebDriverWait(driver, Duration.ofSeconds(getTimeout()));
		    }

		    public static void implicitWait(WebDriver driver) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(getTimeout()));
		    }

		    public static String getCurrentTime(String dateFormat) {
			String currentTime = "";
			try {
			    currentTime = new SimpleDateFormat(dateFormat).format(new Date());
			} catch (IllegalArgumentException e) {
			    
				System.out.println(e.getMessage());
			}
			return currentTime;
		    }
		    public static String getAlertText(WebDriver driver) {
		        String alertText = "";
		        try {
		            Alert alert = driver.switchTo().alert();
		            alertText = alert.getText();
		        } catch (NoAlertPresentException e) {
		            System.out.println("No alert present.");
		            System.out.println(e.getMessage());
		        }
		        return alertText;
		    }

		    public static void acceptAlert(WebDriver driver) {
		        try {
		            Alert alert = driver.switchTo().alert();
		            alert.accept();
		        } catch (NoAlertPresentException e) {
		            System.out.println("No alert present.");
		        }
		    }

		    public static void dismissAlert(WebDriver driver) {
		        try {
		            Alert alert = driver.switchTo().alert();
		            alert.dismiss();
		        } catch (NoAlertPresentException e) {
		            System.out.println("No alert present.");
		        }
		    }
		    
}
