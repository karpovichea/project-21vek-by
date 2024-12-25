package by.vek21.ui.page.search;

import by.vek21.ui.page.BasePage;
import by.vek21.ui.util.ElementUtil;
import by.vek21.ui.wait.Wait;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashSet;
import java.util.List;

public class SearchPage extends BasePage {

    @FindBy(id = "catalogSearch")
    private WebElement searchField;

    @FindBy(xpath = "//button[contains(@class, 'Search_clearBtn')]")
    private WebElement clearButton;

    @FindBy(xpath = "//span[contains(@class, 'result__name')]")
    private List<WebElement> searchResults;

    @FindBy(id = "j-suggest_items")
    private WebElement suggestResults;

    @FindBy(xpath = "//div[contains(@class, 'SearchSuggestList_searchHistory')]")
    private List<WebElement> recentQueries;

    @FindBy(id = "j-paginator")
    private WebElement resultMessage;

    public SearchPage() {
        super();
    }

    @Override
    public SearchPage waitForLoad() {
        Wait.waitForClickable(searchField);
        return this;
    }

    public SearchPage enterSearchQuery(String query) {
        searchField.sendKeys(query);
        searchField.click();
        clickSearchField();
        Wait.waitForVisibility(suggestResults);
        searchField.sendKeys(Keys.ENTER);
        return this;
    }

    public SearchPage clearSearch() {
        clearButton.click();
        return this;
    }

    public void clickSearchField() {
        searchField.click();
    }

    public List<String> getSearchResults() {
        return ElementUtil.getTextsFromElements(searchResults);
    }

    public List<String> getRecentQueries() {
        return ElementUtil.getTextsFromElements(recentQueries);
    }

    public boolean isResultsContainKeyword(String query) {
        List<String> results = getSearchResults();
        return ElementUtil.isListContainText(results, query);
    }

    public boolean isResultsContainRecentQueries(List<String> queries) {
        List<String> results = getRecentQueries();
        return new HashSet<>(results).containsAll(queries);
    }

    public String getResultMessage() {
        Wait.waitForVisibility(resultMessage);
        return resultMessage.getText();
    }
}
