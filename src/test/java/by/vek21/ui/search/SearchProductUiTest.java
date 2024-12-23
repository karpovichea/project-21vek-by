package by.vek21.ui.search;

import by.vek21.ui.BaseUiTest;
import by.vek21.ui.page.SearchPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SearchProductUiTest extends BaseUiTest {

    private static final String NO_RESULT_MESSAGE = "По данным параметрам товаров не найдено";
    private SearchPage searchPage;

    @BeforeEach
    public void setUpSearchTest() {
        searchPage = new SearchPage(driver);
    }

    @Test
    @DisplayName("Поиск товаров по валидному запросу")
    public void testSearchItems() {
        String validQuery = "телевизор";
        searchPage
                .waitForLoad()
                .enterSearchQuery(validQuery);

        Assertions.assertTrue(searchPage.isResultsContainKeyword(validQuery), "Найденные товары не соответствуют запросу");
    }

    @Test
    @DisplayName("Отсутсвие найденных товаров при невалидном запросе")
    public void testDisplayNoResults() {
        String invalidQuery = "~~~~~~~";
        searchPage
                .waitForLoad()
                .enterSearchQuery(invalidQuery);

        Assertions.assertEquals(NO_RESULT_MESSAGE, searchPage.getResultMessage(), "Некорректный текст сообщения");
    }

    @Test
    @DisplayName("Отображение результатов последних запросов")
    public void testDisplayRecentQueries() {
        String firstQuery = "утюг";
        String secondQuery = "смартфон";

        searchPage
                .waitForLoad()
                .enterSearchQuery(firstQuery)
                .clearSearch()
                .enterSearchQuery(secondQuery)
                .clearSearch();

        List<String> expectedRecentQueries = List.of(firstQuery, secondQuery);

        Assertions.assertTrue(searchPage.isResultsContainRecentQueries(expectedRecentQueries), "Некорректные результаты последних запросов");
    }
}
