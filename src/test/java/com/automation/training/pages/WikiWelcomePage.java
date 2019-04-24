package com.automation.training.pages;

import com.automation.training.pageobject.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class WikiWelcomePage extends BasePage<AndroidDriver<AndroidElement>> {

    @AndroidFindBy(id = ID_PREFIX +  "fragment_onboarding_skip_button")
    private AndroidElement skipButton;

    public WikiWelcomePage(AppiumDriver<?> driver) {
        super(driver);
    }

    public AndroidElement getSkipButton() {
        return skipButton;
    }
}
