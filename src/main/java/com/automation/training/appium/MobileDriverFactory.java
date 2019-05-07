package com.automation.training.appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.MobileCapabilityType.NEW_COMMAND_TIMEOUT;
import static io.appium.java_client.remote.MobileCapabilityType.PLATFORM_VERSION;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM;

public class MobileDriverFactory {

	public static String userName = "<userName>";
	public static String accessKey = "<accessKey>";

	public AppiumDriver<?> getDriver(AppiumConfig config) {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(PLATFORM, config.platform().toString());
		cap.setCapability(PLATFORM_VERSION, config.platformVersion());
		cap.setCapability(NEW_COMMAND_TIMEOUT, config.newCommandTimeout());
		cap.setCapability("app", "<app_url>");
		cap.setCapability("device", "Google Pixel");
		cap.setCapability("os_version", "7.1");
		cap.setCapability("browserstack.debug", true);

		AppiumDriver<?> driver = MobileDriver.ANDROID.setDriver(null, cap, config);

		return driver;
	}

	public enum MobileDriver {

		ANDROID {
			@Override
			public AndroidDriver<AndroidElement> setDriver(AppiumServerAddress address, DesiredCapabilities cap,
					AppiumConfig config) {
				cap.setCapability("appPackage", config.appPackage());
				cap.setCapability("appActivity", config.appActivity());
				try {
					return new AndroidDriver<AndroidElement>(new URL("https://"+userName+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub"), cap);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				return null;
			}
		},
		
		IOS {
			@Override
			public IOSDriver<IOSElement> setDriver(AppiumServerAddress address, DesiredCapabilities cap,
					AppiumConfig config) {
				return new IOSDriver<IOSElement>(address.getRemoteAddress(), cap);
			}
		};

		public abstract AppiumDriver<?> setDriver(AppiumServerAddress address, DesiredCapabilities cap,
				AppiumConfig config);
	}

}
