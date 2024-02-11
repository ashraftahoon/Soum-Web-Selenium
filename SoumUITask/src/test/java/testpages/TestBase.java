package testpages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

import Pages.HomePage;
import Pages.LoginPage;
import utils.JsonDataReader;
import utils.Validations;

public class TestBase {

	public WebDriver driver;
	public LoginPage loginPage;
	public static JsonDataReader getdata;
	public HomePage homepage;
	public Validations validation;
	public SoftAssert softAssert;

	@BeforeClass
	public void methodSetup()  {
		
		driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
	    driver.manage().window().maximize();
		getdata= new JsonDataReader();
		loginPage = new LoginPage(driver);
		homepage=new HomePage(driver);
		validation=new Validations();
		softAssert = new SoftAssert();
		
		
	}
	

	
//
	@AfterClass
	public void methodTearDown() {
		
		driver.quit();

	}

}
