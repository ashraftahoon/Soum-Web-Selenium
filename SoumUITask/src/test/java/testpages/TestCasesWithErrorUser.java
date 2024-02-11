package testpages;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import Pages.AddCartPage;
import Pages.CheckOutPage;
import io.qameta.allure.Description;
import utils.Helper;

public class TestCasesWithErrorUser extends TestBase{

	public 	AddCartPage addCartPage;
	public 	CheckOutPage checkoutpage;


	@Test(description = "Soum - Valid User Login" , priority =1 )
	public void testingValidUserLogin() throws org.json.simple.parser.ParseException, IOException     {
		getdata.dataReader("error_user");
		loginPage.loginUser(getdata.usernames.get(0), getdata.passwords.get(0));
		validation.softAssert.assertEquals(homepage.getTitle(), getdata.expectedTitle);

	}

	@Test(description = "testing sorting functionality" , priority =2 )
	@Description("filter is not working")
	public void testingBrokenSorting() throws ParseException, IOException    {
		homepage.selectSortingOption("Name (Z to A)");
		validation.softAssert.assertEquals(Helper.getAlertText(driver), "Sorting is broken! This error has been reported to Backtrace.");
		System.out.println(Helper.getAlertText(driver));
		Helper.acceptAlert(driver);
		validation.softAssert.assertAll();
		System.out.println("filter is not working as expected");
		
	}


	@Test(description = "testing check out" , priority = 3)
	public void TestingCheckOut()   {

		homepage.addProductToCart();
		homepage.openProductDetailsPage();
		addCartPage= homepage.openCart();
		checkoutpage =addCartPage.openCheckOutPage();
		
		checkoutpage.fillCheckOutFields(getdata.firstname, getdata.lastname, getdata.Zibcode);

		validation.softAssert.assertEquals(checkoutpage.getFirstName(),getdata.firstname,"First name field accepts input");
		System.out.println(checkoutpage.getFirstName());

		validation.softAssert.assertEquals(checkoutpage.getLastName(),getdata.lastname,"Last name field does not accept input");
		System.out.println(checkoutpage.getLastName());

		validation.softAssert.assertEquals(checkoutpage.getCode(),getdata.Zibcode,"Postal code field accepts input");
		System.out.println(checkoutpage.getCode());

		checkoutpage.clickOnContinueButn();

		validation.softAssert.assertTrue(checkoutpage.getabilityFinishBution(),"finish button is not clickable");

		validation.softAssert.assertAll();		


	}
	
	@Test(priority = 4)
	public void TestlogOut() {
		homepage.logOut();
		validation.softAssert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/");
		
	}





}
