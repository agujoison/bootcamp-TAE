package com.automation.training.tests;

import com.automation.training.pages.WikiWelcomePage;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.automation.training.appium.AppiumConfig;
import com.automation.training.appium.MobileDriverFactory;
import com.automation.training.pages.WikiHomePage;

import io.appium.java_client.AppiumDriver;

public class BaseTests {
	
	private WikiHomePage wikiHome;
	private WikiWelcomePage wikiWelcomePage;
	
	@BeforeSuite(alwaysRun=true)
	public void beforeSuite() {
		AppiumConfig config = ConfigFactory.create(AppiumConfig.class);
		MobileDriverFactory factory = new MobileDriverFactory();
		AppiumDriver<?> driver = factory.getDriver(config);
		wikiHome = new WikiHomePage(driver);
		wikiWelcomePage = new WikiWelcomePage(driver);
	}
	
	@AfterSuite(alwaysRun=true)
	public void afterSuite() {
		wikiHome.dispose();
		wikiWelcomePage.dispose();
	}

	public WikiHomePage getWikiHomePage() {
		return wikiHome;
	}

	public WikiWelcomePage getWikiWelcomePage() {
		return wikiWelcomePage;
	}
}
