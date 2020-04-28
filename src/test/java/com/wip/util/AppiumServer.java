package com.wip.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.wip.common.AmazonConstants;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;


public class AppiumServer {
	
	//Starting the Appium Server
	
	public static AppiumDriverLocalService service;


	public static void start() {
		
		
	/*	String AppPath = System.getProperty("user.dir") + "\\" + AndrioidPath;
		System.out.println(AppPath);*/
		
		 service = AppiumDriverLocalService
		            .buildService(new AppiumServiceBuilder()
		                    .usingDriverExecutable(new File(AmazonConstants.NODEPATH))
		                    .withAppiumJS(new File(AmazonConstants.APPIUMPATH)));
		    service.start();
	  


	}

	
	//Stopping the Appium Server
	
	public static void stop() throws IOException {

		service.stop();
	}


}
