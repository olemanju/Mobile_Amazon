package com.wip.util;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.PublicKey;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.log4j.PropertyConfigurator;
import org.apache.tools.ant.util.SymbolicLinkUtils;
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

	public AndroidDriver driver;

	public static AppiumDriverLocalService service;
	Properties configFile;

	Properties configProp = new Properties();
	String OS;
	VedioRecording test = new VedioRecording();
	public static String propertyFilePath = System.getProperty("user.dir")
			+ "\\src\\test\\resources\\testdata\\data.properties";

	public static String lo4jpath = System.getProperty("user.dir")
			+ "\\src\\test\\resources\\testdata\\log4j.properties";

	public static String filePath = System.getProperty("user.dir") + "\\test-output\\screenshots\\screenshot";
	FileInputStream fileInput = null;

	// VedioRecording test = new VedioRecording();

	public Properties ObjProperty = getPropertyContents();

	public String AndrioidPath = ObjProperty.getProperty("AndroidAppPath");
	public String Nodepath = ObjProperty.getProperty("Nodepath");
	public String AppiumPath = ObjProperty.getProperty("AppiumPath");

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

		String AppPath = System.getProperty("user.dir") + "\\" + AndrioidPath;
		System.out.println(AppPath);

		// AppiumDriverLocalService
		service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
				.usingDriverExecutable(new File(Nodepath)).withAppiumJS(new File(AppiumPath)));
		// .withIPAddress("127.0.0.1").usingPort(4723));
		// ExtentTestManager.getTest().log(LogStatus.PASS, "Appium Server Is Started");
		// Server will start
		service.start();
		System.out.println("Server started");
		// Video Recording will start
		// test.startRecording();

		// Mobile Capabilities
		DesiredCapabilities dc = new DesiredCapabilities();

		dc.setCapability("deviceName", "Moto G5 Plus");
		dc.setCapability("platformName", "Android");
		dc.setCapability("platformVersion", "8.1.0");
		dc.setCapability("udid", "ZY22442929");
		dc.setCapability("app", AppPath);
		dc.setCapability("appPackage", "com.amazon.mShop.android.shopping");
		dc.setCapability("appActivity", "com.amazon.mShop.splashscreen.StartupActivity");

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), dc);

	}

	@AfterSuite(alwaysRun = true)
	public void stopAppiumServer() throws Exception {

		// To Stop the Recording
		// test.stopRecording();
		// It will stip the Server
		service.stop();

		System.out.println("Teardown");

	}

}
