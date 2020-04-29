package com.wip.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;


public class AppiumServer extends CommonUtils
{	
	public static AppiumDriverLocalService service;	

	public static void start() {
		
		System.out.println(NODEPATH);
		System.out.println(APPIUMPATH);
		 service = AppiumDriverLocalService
		            .buildService(new AppiumServiceBuilder()
		                    .usingDriverExecutable(new File(NODEPATH))
		                    .withAppiumJS(new File(APPIUMPATH)));
		    service.start();

	}
	
	//Stopping the Appium Server
	public static void stop() throws IOException {
		service.stop();
	}


}
