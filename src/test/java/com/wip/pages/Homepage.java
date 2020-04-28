package com.wip.pages;

import javax.swing.tree.ExpandVetoException;

import org.apache.log4j.Logger;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.relevantcodes.extentreports.LogStatus;
import com.wip.ExtentReports.ExtentTestManager;
import com.wip.common.AmazonConstants;
import com.wip.util.BasePageObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Homepage extends BasePageObject {
	public Homepage(AppiumDriver<MobileElement> driver) {
		super((AndroidDriver) driver);
	}

	private final static Logger LOGGER = Logger.getLogger(Homepage.class.getName());

	boolean flag = false;
	/* Web elements */
	By bySkipLoginButton = MobileBy.id("com.amazon.mShop.android.shopping:id/skip_sign_in_button");

	/**
	 * @author Manjunath Ole
	 * @return
	 * @throws Exception
	 */
	public ProductSearchPage clickOnSkipSignIn() throws Exception {

		try {
			waitForVisibility(bySkipLoginButton);
			clickButton(bySkipLoginButton);

		} catch (Exception e) {

			throw new Exception("FAILED CLICKING ON SKIP LOGIN  " + clickOnSkipSignIn() + e.getLocalizedMessage());
		}
		return new ProductSearchPage(driver);

	}

}
