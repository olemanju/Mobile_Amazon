package com.wip.pages;

import javax.swing.tree.ExpandVetoException;

import org.apache.log4j.Logger;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.wip.common.AmazonConstants;
import com.wip.util.BasePageObject;

import io.appium.java_client.android.AndroidDriver;

public class Homepage extends BasePageObject {
	public Homepage(AndroidDriver driver) {
		super(driver);
	}

	private final static Logger LOGGER = Logger.getLogger(Homepage.class.getName());

	// Page Object Model- Stored all the page related objects
	boolean flag = false;
	/* Web elements */
	By lbl_myaccount_css = By.cssSelector("span.stack span");

	// Verify element exists -> id =
	// com.amazon.mShop.android.shopping:id/signin_to_yourAccount
	By bySkipLoginButton = By.id("com.amazon.mShop.android.shopping:id/skip_sign_in_button");
	By byAmazonLogo = By.id("com.amazon.mShop.android.shopping:id/signin_to_yourAccount");

	String pagetitle = null;

	/**
	 * @author Manjunath Ole
	 * @return
	 * @throws Exception
	 */
	public boolean isAmazonLogoisDisplayed() throws Exception {
		try {
			waitForVisibility(byAmazonLogo);
			flag = isElementPresent(byAmazonLogo);

			LOGGER.info("Amazon Logo Is Displayed");

		} catch (Exception e) {
			LOGGER.error("Failed to Load the Amazon App");
			throw new Exception(
					"Failed to Load the Amazon App :" + isAmazonLogoisDisplayed() + e.getLocalizedMessage());
		}
		return flag;
	}

	/**
	 * @author Manjunath Ole
	 * @return
	 * @throws Exception
	 */
	public LoginPage clickOnSkipSignIn() throws Exception {

		try {

			isAmazonLogoisDisplayed();
			waitForVisibility(bySkipLoginButton);
			clickMe(bySkipLoginButton);
			LOGGER.info("Clicked on Skip login Button");

		} catch (Exception e) {

			throw new Exception("FAILED CLICKING ON SKIP LOGIN  " + clickOnSkipSignIn() + e.getLocalizedMessage());
		}
		return new LoginPage(driver);

	}

}
