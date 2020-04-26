package com.wip.pages;

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

public class LoginPage extends BasePageObject {

	public LoginPage(AndroidDriver driver) {
		super(driver);
	}

	private final static Logger LOGGER = Logger.getLogger(LoginPage.class.getName());

	// Page Object Model- Stored all the page related objects
	boolean flag = false;
	/* Web elements */

	
	
	//Click Search Icon on top left - id	com.amazon.mShop.android.shopping:id/rs_search_src_text
    By bySearchBox = By.id("com.amazon.mShop.android.shopping:id/rs_search_src_text");
    
    //Click on no change address button
    By defaultButton=By.id("com.amazon.mShop.android.shopping:id/left_button");
    
	//Click on Add to cart
    By addtocart= By.xpath("//android.widget.Button[contains(@resource-id,'add-to-cart-button') and @text='Add to Cart']");
	/**
	 * @author Manjunath Ole
	 * @return
	 * @throws Exception Checks Error message is displayed
	 */
	public boolean isSearchBoxDisplayed() throws Exception {
		try {
			waitForVisibility(bySearchBox);
			
			flag = isElementPresent(bySearchBox);
			if(flag)
			{
				LOGGER.info("Search Box is displayed");
			}
			else
			{
				LOGGER.error("Search Box is displayed");
			}
			
		} catch (Exception e) {
			throw new Exception("Failed to Search for SearchBox" + isSearchBoxDisplayed() + e.getLocalizedMessage());
		}
		return flag;
	}


	public void enterSearchElementInTheBox(String searchItem) throws Exception {

		try {
			if (isSearchBoxDisplayed() == true) 
			{	
				waitForVisibility(bySearchBox);
				clickMe(bySearchBox);
				LOGGER.info("Click on Search box");
				//Wait for keyboard animations to popup
			    TimeUnit.SECONDS.sleep(1);
			  //Type Canon Camera - id	com.amazon.mShop.android.shopping:id/rs_search_src_text
			    driver.findElement(bySearchBox).sendKeys(searchItem);
			    LOGGER.info("Entered text in the search box " + searchItem);
			    //Press Enter 
			    pressEnter();
			    TimeUnit.SECONDS.sleep(2);
			    ExtentTestManager.getTest().log(LogStatus.PASS, "Entered Search element and Search Item is " + searchItem);
				
			} else {
				LOGGER.error("Username is not displayed on the Page");
			}

		} catch (Exception e) {
			throw new Exception("Failed to enter in the search box" + e.getLocalizedMessage());
		}

	}
	
	public void clickDontChangeButton() throws Exception
	{
		try {
			
			waitForVisibility(defaultButton);
			WebElement leftButton= driver.findElement(defaultButton);
			if(leftButton.isDisplayed())
			{
				leftButton.click();
				LOGGER.info("Clicked on Don't chnage Button");
				System.out.println("Clicked on Dont change  button");
				TimeUnit.SECONDS.sleep(2);
			
			}
			
		} catch (Exception e) 
		{
			throw new Exception("Failed to Click on the Default address Button" + e.getLocalizedMessage());
		}
	}
	
	public void enterSearchableItemInTheBox(String searchItem) throws Exception
	{
		try {
			
			    waitForVisibility(bySearchBox);
			    
			    clickMe(bySearchBox);
			  //Wait for keyboard animations to popup
			    TimeUnit.SECONDS.sleep(1);
			    
			    //Type Canon Camera - id	com.amazon.mShop.android.shopping:id/rs_search_src_text
			    driver.findElement(bySearchBox).sendKeys(searchItem);
			  //Wait for keyboard animations to popup
			    TimeUnit.SECONDS.sleep(1);
			    LOGGER.info("Search Box is displayed and Entered text " + searchItem);
			    pressEnter();
			    
			 
			    
			
		} catch (Exception e) 
		{
			throw new Exception("Failed to Enter the Text in the Search Box" + e.getLocalizedMessage());
		}
	}
	
	public void clickOnSearchByItem(String text) throws InterruptedException
	{
				scrollTillVisibleAndClick(text);		
				LOGGER.info("Scrolled the page till the elemnt is visible on the page " +text );
		
	}
	
	
	public String getTheNameOftheProduct(String name) throws Exception
	{
		try {
			MobileElement el;
			String Value=null;
			
			scrollTillVisible(name);
			el=findElementByText(name);
			Value=el.getText();
			System.out.println(el.getText());
			LOGGER.info("Name of the product is  " + Value);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Name o fthe Product is  " + Value);
			if(Value.equalsIgnoreCase(AmazonConstants.PRODUCTNAME))
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Product Name Assertion is Passed  " + Value);
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Product Name Assertion is Failed  " + Value);
			}
			
			return Value;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("Failed to get the text of the product " + e.getLocalizedMessage());
		}
		
	}
	
	public String getThePriceOftheProduct(String price) throws Exception
	{
		try {
			MobileElement el;
			String Value=null;
			
			scrollTillVisible(price);
			el=findElementByText(price);
			Value=el.getText();
			System.out.println(el.getText());
			/*System.out.println(Value);
			System.out.println(AmazonConstants.PRICE);*/
			LOGGER.info("Price  of the product is  " + Value);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Price  of the product is  " + Value);
			
			if(Value.trim().equalsIgnoreCase(AmazonConstants.PRICE.trim()))
			{
				ExtentTestManager.getTest().log(LogStatus.PASS, "Price Assertion is Passed  " + Value);
			}
			else
			{
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Price Assertion is Failed  " + Value);
			}
			return price;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("Failed to get the Price of the product " + e.getLocalizedMessage());
		}
	}
	
	public String checkTheProductIsInStock(String instock) throws Exception
	{
		try {
			MobileElement el;
			String Value=null;
			
			scrollTillVisible(instock);
			el=findElementByText(instock);
			Value=el.getText();
			System.out.println(el.getText());
			LOGGER.info("product is in Stock" + Value);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Check Item is instock or not and the value is " + Value);
			return instock;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("Failed to get the Instack Text  of the product " + e.getLocalizedMessage());
		}
	}
	
	
	
	public  Boolean isAddtoCartIsDisplayed()
	{
		try {
			
			flag=waitForVisibility(addtocart);
			LOGGER.info("Add to cart Element is displaye don the page  ");
			
		} catch (Exception e) 
		{
			// TODO: handle exception
		}
		return flag;
	}
	public Boolean isAddedTocartTextdisplayed(String addedtoCart)
	{
		findElementByText(addedtoCart);
		
		return flag;
	}
	public void clickOnAddToCart() throws Exception
	{
		try {
			isAddtoCartIsDisplayed();
			clickMe(addtocart);
			TimeUnit.SECONDS.sleep(4);
			LOGGER.info("Clicked on Add to The Cart Button ");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on Add To Card Button");
		} catch (Exception e) {
			throw new Exception("Failed to Click on Add to Cart button " + e.getLocalizedMessage());
		}
	
	}
	
	
	public Boolean isClickToProceed(String proceedtocheckout)
	{
		
		
		findElementByText(proceedtocheckout);
		
		return flag;
	}
	
	
	
	public void clickOnProceeedToCheckOut(String proceedtocheckout ) throws Exception
	{
		try {
			isClickToProceed(proceedtocheckout);
			System.out.println("Is displayed");
			TimeUnit.SECONDS.sleep(3);
			MobileElement el;
			el=findElementByText(proceedtocheckout);
			el.click();
			LOGGER.info("Clicked on Proceed to Check in Button ");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on Proceed to Check in Button");
		} catch (Exception e) {
			// TODO Auto-generated catch block
		throw new Exception("Not able to Click on Proceed to Check out Button" + e.getLocalizedMessage());
		}
	}
	
	
	
	
	
	
	
	

	

}
