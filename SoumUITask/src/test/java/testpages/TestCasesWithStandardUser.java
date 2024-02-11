package testpages;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import Pages.AddCartPage;
import Pages.CheckOutPage;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;

public class TestCasesWithStandardUser extends TestBase{

	//////////////////////////////////////////////////////
	/////////////////// Test Cases //////////////////////
	public AddCartPage addcartpage;
	public CheckOutPage checkoutpage;


	@Test(description = "Soum - Valid User Login" , priority =1 )
	@Description("login with valid user")
	@Owner("Ashraf")
	public void testingValidUserLogin() throws ParseException, IOException    {
		getdata.dataReader("standard0_user");
		loginPage.loginUser(getdata.usernames.get(0), getdata.passwords.get(0));
		validation.softAssert.assertEquals(homepage.getTitle(), getdata.expectedTitle);
	}



	@Test(description = "add product to cart" , priority = 2 )
	@Description("add product to cart")
	@Owner("Ashraf")
	public void testingAddProductToCart()  {
		homepage.addProductToCart();
		homepage.openProductDetailsPage();
		addcartpage= homepage.openCart();
		validation.softAssert.assertEquals(addcartpage.getNameProduct(), getdata.productname);

	}

	@Test(description = "make Order" , priority = 3 )
	@Description("make Order")
	@Owner("Ashraf")
	public void testingCheckOut()  {
		checkoutpage = addcartpage.openCheckOutPage();
		checkoutpage.fillCheckOutFields(getdata.firstname, getdata.lastname, getdata.Zibcode);
		checkoutpage.clickOnContinueButn();
		checkoutpage.clickOnFinshButn();
		System.out.println(checkoutpage.getcheckoutStatus());
		System.out.println( getdata.checkoutstatus);
		validation.softAssert.assertEquals(checkoutpage.getcheckoutStatus(),getdata.checkoutstatus);
		checkoutpage.backToHome();

	}


	@Test(description = "sorting" , priority = 4 )
	@Description("sorting Name (A to Z")
	@Owner("Ashraf")
	public void testSortAToZ() {
		homepage.selectSortingOption("Name (A to Z)");
		System.out.println(homepage.isSortedDescending());
		System.out.println(homepage.isSortedAscending());
		validation.softAssert.assertTrue(homepage.isSortedDescending());

	}
	//
	@Test(description = "sorting" , priority = 5 )
	@Description("sorting Name (Z to A)")
	@Owner("Ashraf")
	public void testSortZToA() {
		homepage.selectSortingOption("Name (Z to A)");
		System.out.println(homepage.isSortedAscending());
		validation.softAssert.assertTrue(homepage.isSortedAscending());

	}

	@Test(description = "sorting" , priority = 6 )
	@Description(" sorting Price (low to high)")
	@Owner("Ashraf")
	public void testSortLowToHigh() {
		homepage.selectSortingOption("Price (low to high)");
		validation.softAssert.assertTrue(homepage.isSortedHighToLow());

	}

	@Test(description = "sorting" , priority = 7 )
	@Description("sorting Price (high to low)")
	@Owner("Ashraf")
	public void testSortHighToLow() {
		homepage.selectSortingOption("Price (high to low)");
		validation.softAssert.assertTrue(homepage.isSortedLowToHigh());
	}

	@Test(description = "remove product from home" , priority = 8 )
	@Description("remove product from home")
	@Owner("Ashraf")
	public void testingRemoveProduct() {
		homepage.removeProductFromHome();
		validation.softAssert.assertTrue(homepage.isCartEmpty(), "Cart is empty");

	}

	@Test(description = "remove product from cart" , priority = 9 )
	@Description("remove product from cart")
	@Owner("Ashraf")
	public void testingRemoveProductFromCart() {
		addcartpage.ReturnBackToShoping();
		homepage.removeProductFromCart();
		validation.softAssert.assertTrue(homepage.isCartEmpty(), "Cart is  empty");

	}


	@Test(description = "check cart is empty or not ?" , priority = 10 )
	@Description("check cart is empty or not ?")
	@Owner("Ashraf")
	public void testEmptyCartCheckout() throws Exception {
		// Verify that the cart is empty
		validation.softAssert.assertTrue(homepage.isCartEmpty(), "Cart is  empty");
		validation.softAssert.assertTrue(addcartpage.CheckOutEmptyCart(),"Your cart is empty. Please add items before proceeding to checkout.");

	}


	@Test(description = "TestLogOut" , priority = 11 )
	@Description("login out successfully")
	@Owner("Ashraf")
	public void TestLogOut() throws Exception {

		addcartpage.ReturnBackToShoping();
		homepage.logOut();
		validation.softAssert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/");
	}


}


