package utils;

import org.testng.asserts.SoftAssert;

public class Validations {
	public SoftAssert softAssert;

	public Validations() {
		softAssert = new SoftAssert();
	}

	public SoftAssert getSoftAssert() {
		return softAssert;
	}



}
