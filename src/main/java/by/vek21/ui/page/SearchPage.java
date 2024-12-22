package by.vek21.ui.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashSet;
import java.util.List;

public class SearchPage extends BasePage {

    @FindBy(id = "catalogSearch")
    private WebElement searchField;

    @FindBy(xpath = "//button[contains(@class, 'Search_clearBtn')]")
    private WebElement clearButton;

    @FindBy(xpath = "//span[contains(@class, 'result__name')]")
    private List<WebElement> searchResults;

    @FindBy(xpath = "//div[contains(@class, 'SearchSuggestList_searchHistory')]")
    private List<WebElement> recentQueries;

    @FindBy(id = "j-paginator")
    private WebElement resultBlock;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public SearchPage waitForLoad() {
        wait.until(ExpectedConditions.elementToBeClickable(searchField));
        return this;
    }

    public SearchPage enterSearchQuery(String query) {
        searchField.sendKeys(query);
        searchField.sendKeys(Keys.ENTER);
        return this;
    }

    public SearchPage clearSearch() {
        clearButton.click();
        return this;
    }

    public List<String> getSearchResults() {
        return getTextsFromElements(searchResults);
    }

    public List<String> getRecentQueries() {
        return getTextsFromElements(recentQueries);
    }

    public boolean isResultsContainKeyword(String query) {
        List<String> results = getSearchResults();
        return isListContainText(results, query);
    }

    public boolean isResultsContainRecentQueries(List<String> queries) {
        List<String> results = getRecentQueries();
        return new HashSet<>(results).containsAll(queries);
    }

    public String getResultMessage() {
        return resultBlock.getText();
    }
}
