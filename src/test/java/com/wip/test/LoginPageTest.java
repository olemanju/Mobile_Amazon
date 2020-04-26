package com.wip.test;

import java.lang.reflect.Method;
import java.util.logging.LogManager;

//import org.apache.logging.log4j.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import com.wip.common.AmazonConstants;
import com.wip.pages.Homepage;
import com.wip.pages.LoginPage;

import com.wip.util.BaseTestObject;
import com.wip.util.CreateSession;
import com.wip.ExtentReports.ExtentTestManager;

//@Listeners(com.wip.util.ListenerTest.class)
public class LoginPageTest extends BaseTestObject {

	// Fetching the values from the property file
	public String strSearchText = ObjProperty.getProperty("strSearchText");
	public String searchtext = ObjProperty.getProperty("searchtext");
	public String searchItem = ObjProperty.getProperty("searchItem");
	public String price = ObjProperty.getProperty("price");
	public String instock = ObjProperty.getProperty("instock");
	public String AddedToCart = ObjProperty.getProperty("AddedToCart");
	public String proceedtoCheckout = ObjProperty.getProperty("proceedtoCheckout");

	// Creating the objects for the Classes
	Homepage objhomepage;
	LoginPage objloginpage;

	// Extent manager Obj
	ExtentTestManager extentlog;

	boolean flag = false;

	String home_page_title = null;

	/**
	 * @author Manjunath Below Test case coveres End to end flow Testcase
	 * 
	 * @throws Exception
	 */

	@Test(priority = 0, enabled = true, description = "User is able to Search for the Product and Add to The Cart")
	public void verifyUserIsAbleToSearchAndToTheCart(Method method) throws Exception {
		try {

			ExtentTestManager.startTest(method.getName(),
					"User is able to Search for the Product and Add to The Cart.");

			objhomepage = new Homepage(driver);
			// String title= objhomepage.verifyHomePageTitle();
			objloginpage = objhomepage.clickOnSkipSignIn();
			objloginpage.isSearchBoxDisplayed();
			objloginpage.enterSearchElementInTheBox(strSearchText);
			objloginpage.clickDontChangeButton();
			objloginpage.enterSearchableItemInTheBox(strSearchText);
			objloginpage.clickOnSearchByItem(searchtext);
			objloginpage.getTheNameOftheProduct(searchtext);
			objloginpage.getThePriceOftheProduct(price);
			objloginpage.checkTheProductIsInStock(instock);
			objloginpage.clickOnAddToCart();
			objloginpage.clickOnProceeedToCheckOut(proceedtoCheckout);
			ExtentTestManager.getTest().log(LogStatus.PASS, "user has logout successfully");

		} catch (Exception e) {
			throw new Exception("FAILED COMPELTE THE END TO FLOW " + e.getLocalizedMessage());
		}

	}

}
