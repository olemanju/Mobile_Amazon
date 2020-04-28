package com.wip.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class CommonUtils {

	private static Properties prop = new Properties();

	public static int EXPLICIT_WAIT_TIME;
	public static int IMPLICIT_WAIT_TIME;
	public static String BASE_PKG;
	public static String APP_ACTIVITY;
	public static String BROWSER_NAME;
	public static String PLATFORM_NAME;
	public static String PLATFORM_VERSION;
	public static String DEVICE_NAME;
	public static String AUTOMATION_INSTRUMENTATION;
	public static String APPIUM_PORT;
	public static String APP_NAME;

	private static DesiredCapabilities capabilities = new DesiredCapabilities();
	private static URL serverUrl;
	private static AppiumDriver<MobileElement> driver;

	public static void loadAndroidConfigProp(String propertyFileName) throws IOException {
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/test/resources/testdata/" + propertyFileName);

		prop.load(fis);

		EXPLICIT_WAIT_TIME = Integer.parseInt(prop.getProperty("explicit.wait"));
		IMPLICIT_WAIT_TIME = Integer.parseInt(prop.getProperty("implicit.wait"));
		BASE_PKG = prop.getProperty("base.pkg");
		APP_ACTIVITY = prop.getProperty("application.activity");
		APPIUM_PORT = prop.getProperty("appium.server.port");
		AUTOMATION_INSTRUMENTATION = prop.getProperty("automation.instrumentation");
		DEVICE_NAME = prop.getProperty("device.name");
		BROWSER_NAME = prop.getProperty("browser.name");
		PLATFORM_NAME = prop.getProperty("platform.name");
		PLATFORM_VERSION = prop.getProperty("platform.version");
		APP_NAME = prop.getProperty("application.path");

	}

	public static void setAndroidCapabilities() {

		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, PLATFORM_VERSION);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AUTOMATION_INSTRUMENTATION);
		capabilities.setCapability(MobileCapabilityType.APP, APP_NAME);
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, BASE_PKG);
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, APP_ACTIVITY);
	}

	public static AppiumDriver<MobileElement> getAndroidDriver() throws MalformedURLException {
		serverUrl = new URL("http://127.0.0.1:" + APPIUM_PORT + "/wd/hub");
		driver = new AndroidDriver<MobileElement>(serverUrl, capabilities);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return driver;
	}

}
