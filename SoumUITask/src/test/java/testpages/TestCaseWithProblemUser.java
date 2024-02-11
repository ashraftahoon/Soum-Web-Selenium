package testpages;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import Pages.AddCartPage;
import Pages.CheckOutPage;
import Pages.ProductDetailsPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class TestCaseWithProblemUser extends TestBase {

	public AddCartPage addcartpage;
	public CheckOutPage checkoutpage;

	// test login
	@Test(description = "Soum - Valid User Login" , priority =1 )
	@Description("When I Signin with an already signed up user, Then I should Login successfully")
	public void testingValidUserLogin() throws ParseException, IOException    {
		getdata.dataReader("problem_user");
		loginPage.loginUser(getdata.usernames.get(0), getdata.passwords.get(0));
		validation.softAssert.assertEquals(homepage.getTitle(), getdata.expectedTitle);
		validation.softAssert.assertAll();

	}

	/// Test add product is  clickable or not in the home  page
	@Test(description = "can assertthat add product is  clickable in home page " , priority = 2 )
	@Description("When I Signin successfully , Then I can assertthat add product is  clickable successfully")
	public void TestingAddProductClickableInHomePage ()  {
		addcartpage= new AddCartPage(driver);
		homepage.CheckElementClickable();
		System.out.println(homepage.isCartEmpty());
		validation.softAssert.assertFalse(homepage.isCartEmpty(), "cart is not empty cause remove product is not clickable");
		addcartpage.ReturnBackToShoping(); 
		validation.softAssert.assertFalse(homepage.isElementClickable(), "add product is not clickable");
		validation.softAssert.assertAll();

	}

	// testing sort from a to z 
	@Test(description = "sorting" , priority = 3 )
	@Description("When I Signin successfully , Then I can sort A to Z successfully")
	public void testSortAToZ() {
		homepage.selectSortingOption("Name (A to Z)");
		System.out.println(homepage.isSortedDescending());
		System.out.println(homepage.isSortedAscending());
		validation.softAssert.assertFalse(homepage.isSortedDescending());
		validation.softAssert.assertAll();


	}


	// testing sort from z to a
	@Test(description = "sorting" , priority = 4)
	@Description("When I Signin successfully , Then I can sort Z to A successfully")
	public void testSortZToA() {
		homepage.selectSortingOption("Name (Z to A)");
		System.out.println(homepage.isSortedAscending());
		validation.softAssert.assertFalse(homepage.isSortedAscending());
		validation.softAssert.assertAll();

	}

	@Test(description = "sorting" , priority = 5 )
	@Description("When I Signin successfully , Then I can sort Low to High successfully")
	public void testSortLowToHigh() {
		homepage.selectSortingOption("Price (low to high)");
		validation.softAssert.assertFalse(homepage.isSortedHighToLow());
		validation.softAssert.assertAll();

	}

	@Test(description = "sorting" , priority = 6 )
	@Description("When I Signin successfully , Then I can sort High to Low successfully")
	@Severity(SeverityLevel.CRITICAL)
	public void testSortHighToLow() {
		homepage.selectSortingOption("Price (high to low)");
		validation.softAssert.assertFalse(homepage.isSortedLowToHigh());
		validation.softAssert.assertAll();

	}

	@Test(description = "check product details" , priority = 7 )
	@Description("When I Signin successfully and add product to cart, Then I can order this product successfully")
	@Severity(SeverityLevel.CRITICAL)
	public void testingProductDetails()  {
		ProductDetailsPage productdetailsobj= homepage.openProductDetailsPage();
		validation.softAssert.assertEquals(productdetailsobj.getPrice(),"$29.99");
		//productdetailsobj.getbackToProducts();
		System.out.println(productdetailsobj.getPrice());
		validation.softAssert.assertAll();

	}

	@Test(description = "check out" , priority = 8 )
	@Description("When I Signin successfully and add product to cart, Then I can order this product successfully")
	public void testingCheckOut()  {
		homepage.openCart();
		checkoutpage =addcartpage.openCheckOutPage();
		checkoutpage.fillCheckOutFields(getdata.firstname, getdata.lastname, getdata.Zibcode);
		validation.softAssert.assertEquals(checkoutpage.getLastName(),getdata.lastname);
		checkoutpage.clickOnContinueButn();
		System.out.println(checkoutpage.getLastName());
	}



	@Test(priority = 9)
	public void TestlogOut() {
		homepage.logOut();
		validation.softAssert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/");

	}







}
