package com.wip.pages;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;
import com.wip.ExtentReports.ExtentTestManager;
import com.wip.common.AmazonConstants;

import com.wip.util.BasePageObject;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class ProductSearchPage extends BasePageObject {

	public ProductSearchPage(AndroidDriver driver) {
		super(driver);
	}

	private final static Logger LOGGER = Logger.getLogger(ProductSearchPage.class.getName());

	// Page Object Model- Stored all the page related objects
	boolean flag = false;
	/* Web elements */

	By txt_bySearchBox_id = MobileBy.id("com.amazon.mShop.android.shopping:id/rs_search_src_text");
	By btn_defaultButton_id = MobileBy.id("com.amazon.mShop.android.shopping:id/left_button");
	By txt_productname_xpath = MobileBy.xpath(
			"//android.view.View[@index=0 and contains(@text,'Canon EOS 4000D DSLR Camera w/Canon EF-S 18-55mm F/3.5-5.6 III Zoom Lens')]");
	By txt_Price_xpath = MobileBy.xpath("//android.widget.TextView[@index=1 and contains(@text,'349')]");
	By txt_instock_xpath = MobileBy.xpath("//android.widget.TextView[@index=0 and contains(@text,'In Stock.')]");
	By btn_list_Proceed_xpath = By.xpath("//android.widget.Button[@index=0]");
	By btn_addtocart_xpath = MobileBy
			.xpath("//android.widget.Button[contains(@resource-id,'add-to-cart-button') and @text='Add to Cart']");

	/**
	 * @author Manjunath Ole
	 * @return
	 * @throws Exception Checks Error message is displayed
	 */
	public boolean isSearchBoxDisplayed() throws Exception {
		try {
			waitForVisibility(txt_bySearchBox_id);
			flag = isElementPresent(txt_bySearchBox_id);
		} catch (Exception e) {
			throw new Exception("Failed to Search for SearchBox" + isSearchBoxDisplayed() + e.getLocalizedMessage());
		}
		return flag;
	}

	public void enterSearchElementInTheBox(String searchItem) throws Exception {

		try {
			if (isSearchBoxDisplayed() == true) {
				waitForVisibility(txt_bySearchBox_id);
				clickButton(txt_bySearchBox_id);
				// Wait for keyboard animations to popup
				TimeUnit.SECONDS.sleep(1);
				findElement(txt_bySearchBox_id).sendKeys(searchItem);
				pressEnter();
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Entered Search element and Search Item is " + searchItem);

			} else {
				LOGGER.error("Username is not displayed on the Page");
			}

		} catch (Exception e) {
			throw new Exception("Failed to enter in the search box" + e.getLocalizedMessage());
		}

	}

	public void clickOnDontChangeButton() throws Exception {
		try {

			waitForVisibility(btn_defaultButton_id);
			WebElement dontchange = driver.findElement(btn_defaultButton_id);
			if (dontchange.isDisplayed()) {
				dontchange.click();
				LOGGER.info("Clicked on Don't chnage Button");
			}

		} catch (Exception e) {
			throw new Exception("Failed to Click on the Default address Button" + e.getLocalizedMessage());
		}
	}

	public void enterSearchableItemInTheBox(String searchItem) throws Exception {
		try {

			waitForVisibility(txt_bySearchBox_id);
			clickButton(txt_bySearchBox_id);
			// Wait for keyboard animations to popup
			TimeUnit.SECONDS.sleep(1);
			findElement(txt_bySearchBox_id).sendKeys(searchItem);
			// Wait for keyboard animations to popup
			TimeUnit.SECONDS.sleep(1);
			LOGGER.info("Search Box is displayed and Entered text " + searchItem);
			pressEnter();

		} catch (Exception e) {
			throw new Exception("Failed to Enter the Text in the Search Box" + e.getLocalizedMessage());
		}
	}

	public void clickOnSearchByItem(String text) throws InterruptedException {
		scrollTillVisibleAndClick(text);
		LOGGER.info("Scrolled the page till the elemnt is visible on the page " + text);

	}

	public String getTheNameOftheProduct(String name) throws Exception {
		try {

			String productName = null;
			scrollTillVisible(name);
			productName = getText(txt_productname_xpath);
			if (productName.equals(AmazonConstants.PRODUCTNAME)) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Product Name Assertion is Passed  " + productName);
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Product Name Assertion is Failed  " + productName);

			}
			return productName;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("Failed to get the text of the product " + e.getLocalizedMessage());
		}

	}

	public String getThePriceOftheProduct(String price) throws Exception {
		try {
			String price_name = null;
			scrollTillVisible(price);

			price_name = getText(txt_Price_xpath);
			if (price_name.trim().equals(AmazonConstants.PRICE.trim())) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Price Assertion is Passed  " + price_name);
			} else {

				ExtentTestManager.getTest().log(LogStatus.FAIL, "Price Assertion is Failed  " + price_name);
				Assert.assertEquals(price_name, AmazonConstants.PRICE.trim(), "Values are Not Matching");
			}
			return price;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("Failed to get the Price of the product " + e.getLocalizedMessage());
		}
	}

	public String checkTheProductIsInStock(String instock) throws Exception {
		String instck = null;
		try {
			scrollTillVisible(instock);
			instck = getText(txt_instock_xpath);

			return instock;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("Failed to get the Instack Text  of the product " + e.getLocalizedMessage());
		}
	}

	public void clickOnAddToCart() throws Exception {
		try {
			waitForVisibility(btn_addtocart_xpath);
			clickButton(btn_addtocart_xpath);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on Add To Card Button");
		} catch (Exception e) {
			throw new Exception("Failed to Click on Add to Cart button " + e.getLocalizedMessage());
		}

	}

	public void clickOnProceeedToCheckOut(String proceedtocheckout) throws Exception {
		try {
			TimeUnit.SECONDS.sleep(4);
			List<WebElement> ProceedButtons = driver.findElements(btn_list_Proceed_xpath);
			for (WebElement btn : ProceedButtons) {
				if (btn.getAttribute("text").contains("Proceed to checkout")) {
					btn.click();
					break;
				}
			}
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on Proceed to Check in Button");
		} catch (Exception e) {

			throw new Exception("Not able to Click on Proceed to Check out Button" + e.getLocalizedMessage());
		}
	}

}
