package testpages;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;

import Pages.AddCartPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class TestCasesWithVisualUser extends TestBase{
	public AddCartPage addcartpage;
	public Eyes eye ;
	public  BatchInfo myBatch;
	public  Configuration suiteConfig;
	@BeforeMethod
	public void beforeEach(ITestResult result){
		eye = new Eyes(); // create object of eye 
		suiteConfig=new Configuration(); // object config
		myBatch= new BatchInfo("my first batch"); // object of new batch 
		suiteConfig.setApiKey(("CBYmfm3IqViN109110CjOvmWEDEfBckxEBW8tj4esx105duDA110")); // set config
		eye.setConfiguration(suiteConfig);
		eye.open(driver, "test",result.getMethod().getMethodName() );
		suiteConfig.setBatch(myBatch);
	}

	@Test(description = "Soum - Valid User Login" , priority =1 )
	@Description("When I Signin with an already signed up user, Then I should Login successfully")
	@Severity(SeverityLevel.CRITICAL)
	public void testingValidUserLogin() throws ParseException, IOException    {
		getdata.dataReader("standard0_user");
		loginPage.loginUser(getdata.usernames.get(0), getdata.passwords.get(0));
		eye.check(Target.window());
		validation.softAssert.assertEquals(homepage.getTitle(), getdata.expectedTitle);
		System.out.println(homepage.getTitle());
	}

	@Test(description = "add product to cart" , priority = 2 )
	@Description("When I Signin successfully , Then I can add product to cart successfully")
	@Severity(SeverityLevel.CRITICAL)
	public void testingAddProductToCart()  {
		homepage.addProductToCart();
		homepage.openProductDetailsPage();
		addcartpage= homepage.openCart();
		eye.check(Target.window());
		validation.softAssert.assertEquals(addcartpage.getNameProduct(), getdata.productname);

	}


	@Test(priority = 3)
	public void TestlogOut() {
		homepage.logOut();
		validation.softAssert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/");
	}
	
	@AfterMethod
	public void afterEach() {
		try {
			eye.closeAsync();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


}
