package com.automation.training.tests;

import com.automation.training.pages.SearchPage;
import com.automation.training.pages.WikiHomePage;
import com.automation.training.pages.WikiWelcomePage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class WikiTests extends BaseTests {

	@Test(description = "")
	@Parameters({ "searchCriteria" })
	public void testWikiSearch(String searchCriteria) {
		WikiWelcomePage welcomePage = getWikiWelcomePage();
		welcomePage.getSkipButton().click();
		WikiHomePage homePage = getWikiHomePage();
		SearchPage searchPage = homePage.openSearch().search(searchCriteria);
		Assert.assertTrue(searchPage.getResultsSize() > 0, "Expected that the search returns some articles.");
	}

}
