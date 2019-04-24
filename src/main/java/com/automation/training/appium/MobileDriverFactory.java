package com.automation.training.appium;

import com.automation.training.listener.ElementListener;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.events.EventFiringWebDriverFactory;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.MobileCapabilityType.NEW_COMMAND_TIMEOUT;
import static io.appium.java_client.remote.MobileCapabilityType.PLATFORM_VERSION;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM;

public class MobileDriverFactory {

	public static String userName = "agustinjoison1";
	public static String accessKey = "D7Ag1pAJYdxm6EpmdjZy";

	public AppiumDriver<?> getDriver(AppiumConfig config) {
		DesiredCapabilities cap = new DesiredCapabilities();
		AppiumServerAddress address = new AppiumServerAddress(config.appiumServerIp(), config.appiumServerPort());
		//cap.setCapability(BROWSER_NAME, config.deviceName());
		//cap.setCapability(DEVICE_NAME, config.deviceName());
		cap.setCapability(PLATFORM, config.platform().toString());
		cap.setCapability(PLATFORM_VERSION, config.platformVersion());
		cap.setCapability(NEW_COMMAND_TIMEOUT, config.newCommandTimeout());
		//cap.setCapability(APP, config.app());
		cap.setCapability("app", "bs://c65b210f473e0c63ec15116d00820c9d6164a6be");
		cap.setCapability("device", "Google Nexus 6");
		cap.setCapability("os_version", "6.0");
		cap.setCapability("browserstack.debug", true);

		AppiumDriver<?> driver = config.platform().setDriver(address, cap, config);
		driver.manage().timeouts().implicitlyWait(1, SECONDS);
		driver = EventFiringWebDriverFactory.getEventFiringWebDriver(driver, new ElementListener());
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
