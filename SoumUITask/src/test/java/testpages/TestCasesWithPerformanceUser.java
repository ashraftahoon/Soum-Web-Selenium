package testpages;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import Pages.AddCartPage;
import Pages.CheckOutPage;
import Pages.ProductDetailsPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class TestCasesWithPerformanceUser extends TestBase{
	public AddCartPage addcartpage;
	public CheckOutPage checkoutpage;


	@Test(description = "Soum - Valid User Login" , priority =1 )
	public void testingValidUserLogin() throws ParseException, IOException    {
		getdata.dataReader("performance_glitch_user");
		loginPage.loginUser(getdata.usernames.get(0), getdata.passwords.get(0));
		validation.softAssert.assertEquals(homepage.getTitle(), getdata.expectedTitle);
		System.out.println(homepage.getTitle());
	}

	@Test(description = "sorting A to Z" , priority = 2 )
	public void testSortAToZ() {
		homepage.selectSortingOption("Name (A to Z)");
		System.out.println(homepage.isSortedDescending());
		System.out.println(homepage.isSortedAscending());
		validation.softAssert.assertTrue(homepage.isSortedDescending());

	}

	@Test(description = "sorting Z to A" , priority = 3 )
	public void testSortZToA() {
		homepage.selectSortingOption("Name (Z to A)");
		System.out.println(homepage.isSortedAscending());
		validation.softAssert.assertTrue(homepage.isSortedAscending());

	}

	@Test(description = "sorting Low to High" , priority = 4 )
	public void testSortLowToHigh() {
		homepage.selectSortingOption("Price (low to high)");
		validation.softAssert.assertTrue(homepage.isSortedHighToLow());
	}

	@Test(description = "sorting  High to Low" , priority = 5 )
	public void testSortHighToLow() {
		homepage.selectSortingOption("Price (high to low)");
		validation.softAssert.assertTrue(homepage.isSortedLowToHigh());

	}

	@Test(description = "make order" , priority = 6 )
	public void testingCheckOut()  {

		homepage.addProductToCart();
		homepage.openProductDetailsPage();
		addcartpage=homepage.openCart();
		checkoutpage = addcartpage.openCheckOutPage();
		checkoutpage.fillCheckOutFields(getdata.firstname, getdata.lastname, getdata.Zibcode);
		checkoutpage.clickOnContinueButn();
		checkoutpage.clickOnFinshButn();
		validation.softAssert.assertEquals(checkoutpage.getcheckoutStatus(),getdata.checkoutstatus);
		System.out.println(checkoutpage.getcheckoutStatus());
		System.out.println( getdata.checkoutstatus);
		checkoutpage.backToHome();

	}

	@Test(description = "open product details then return back to home " , priority = 7 )
	public void testingNavigateToProductDetails()  {
		homepage.addProductToCart();
		ProductDetailsPage productdetailspage=	homepage.openProductDetailsPage();
		productdetailspage.getbackToProducts();
		validation.softAssert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");

	}

	@Test(description = "testing open cart then return back to home" , priority = 8 )
	public void testingNavigateToCart()  {
		AddCartPage addproductpage=homepage.openCart();
		addproductpage.ReturnBackToShoping();
		validation.softAssert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");

	}

	@Test(priority = 9)
	public void TestlogOut() {
		homepage.logOut();
		validation.softAssert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/");
	}

}
