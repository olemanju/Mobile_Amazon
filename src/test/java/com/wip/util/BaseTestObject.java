package com.wip.util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
//import utility.AppiumServer;
//import utility.CommonUtils;
import com.wip.util.AppiumServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.apache.tools.ant.util.SymbolicLinkUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.wip.ExtentReports.ExtentTestManager;

/**
 * contains all the methods to create a new session and destroy the session
 * after the test(s) execution is over. Each test extends this class.
 */
public class BaseTestObject {
	public static ExtentTest extest;
	public static ExtentReports report;

	public static AppiumDriver<MobileElement> driver;

	public static String loadPropertyFile = "android_appium_config.properties";

	Properties configFile;
	Properties configProp = new Properties();

	public static String propertyFilePath = System.getProperty("user.dir")
			+ "\\src\\test\\resources\\testdata\\data.properties";

	public static String lo4jpath = System.getProperty("user.dir")
			+ "\\src\\test\\resources\\testdata\\log4j.properties";

	public static String filePath = System.getProperty("user.dir") + "\\test-output\\screenshots\\screenshot";
	FileInputStream fileInput = null;

	public Properties ObjProperty = getPropertyContents();

	private static final Properties prop = new Properties();

	public AndroidDriver getDriver() {

		return (AndroidDriver) driver;
	}

	private static void loadPropertiesFile() {
		InputStream input = null;

		try {
			input = new FileInputStream(propertyFilePath);
			// load a properties file
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static Properties getPropertyContents() {
		loadPropertiesFile();
		return prop;
	}

	@BeforeSuite(alwaysRun = true)
	public void startAppiumServer() throws Exception {

		if (driver == null) {
			System.out.println("Hello");
			if (loadPropertyFile.startsWith("IOS")) {
				// IOS Piece of code
			} else if (loadPropertyFile.startsWith("android")) {
				
				CommonUtils.loadAndroidConfigProp(loadPropertyFile);
				AppiumServer.start();
				CommonUtils.setAndroidCapabilities();
				driver = CommonUtils.getAndroidDriver();
			}
		}
	}

	@AfterSuite(alwaysRun = true)
	public void stopAppiumServer() throws Exception {	
		// It will stop the Server
		driver.quit();
		System.out.println("Teardown");
		AppiumServer.stop();

	}

	public static void screenShot(WebDriver driver) throws IOException, InterruptedException {

		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File scr = screenshot.getScreenshotAs(OutputType.FILE);
		File dest = new File(filePath + timestamp() + ".png");

		try {
			FileUtils.copyFile(scr, dest);
			Thread.sleep(3000);
		} catch (IOException e) {
			System.out.println("Capture Failed " + e.getMessage());
		}
	}
	public static String timestamp() {
		return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}

}
