package com.automation.training.pages;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import com.automation.training.pageobject.BasePage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.apache.bcel.generic.I2D;

public class SearchPage extends BasePage<AndroidDriver<AndroidElement>> {

	private List<ResultItemWidget> resultItemWidgets;

	@AndroidFindBy(id = ID_PREFIX + "search_src_text")
	private AndroidElement editTextSearch;

	@AndroidFindBy(id = ID_PREFIX + "search_close_btn")
	private AndroidElement imageClose;

	@AndroidFindBy(id = ID_PREFIX + "recent_searches_delete_button")
	private AndroidElement imageDeleteRecent;

	public SearchPage(AppiumDriver<?> driver) {
		super(driver);
	}

	/**
	 * Delete all recent searchs.
	 * 
	 * @param search
	 *            The search criteria.
	 * @return {@link SearchPage}
	 */
	public SearchPage search(final String search) {
		type(editTextSearch, search);
		hideKeyboard();
		return this;
	}

	/**
	 * Delete all recent searchs.
	 * 
	 * @return
	 */
	public SearchPage deleteRecent() {
		click(imageDeleteRecent);
		return this;
	}

	/**
	 * Close the {@link SearchPage}
	 * 
	 * @return {@link SearchPage}
	 */
	public SearchPage close() {
		click(imageClose);
		return this;
	}

	/**
	 * Returns the number of elements in the {@link ResultItemWidget} list.
	 * 
	 * @return
	 */
	public int getResultsSize() {
		return resultItemWidgets.size();
	}

	/**
	 * Select a {@link ResultItemWidget} item with a specific index.
	 * 
	 * @param index
	 *            Index on the Result items list.
	 * @return A new instance of {@link ArticlePage}.
	 * @throws IllegalArgumentException
	 *             if the given index > total of result items.
	 */
	public ArticlePage openArticleByIndex(final int index) {
		int actual = getResultsSize();
		checkArgument(index <= actual, "You requested the item [%s] but the items list has [%s] items", index, actual);
		ResultItemWidget item = resultItemWidgets.get(index);
		click(item.getTitle());
		return new ArticlePage(getDriver());
	}
}
