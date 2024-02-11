package testpages;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import io.qameta.allure.Description;

public class LoginTestWithLockedUser extends TestBase {

	// test login with locked out user 
	@Test(description = "Soum - Valid User Login" )
	@Description("When I Signin with an already signed up user, Then I should Login successfully")
	public void testingValidUserLogin() throws ParseException, IOException    {
		getdata.dataReader("locked_out_user");
		loginPage.loginUser(getdata.usernames.get(0), getdata.passwords.get(0));
		validation.softAssert.assertEquals(loginPage.getErrorText(), getdata.expectedErrorMessage);
		System.out.println(loginPage.getErrorText());
	}

}
