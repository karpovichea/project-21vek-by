package by.vek21.ui.filter;

import by.vek21.ui.BaseUiTest;
import by.vek21.ui.model.Product;
import by.vek21.ui.page.ProductPage;
import by.vek21.ui.page.FilterPage;
import by.vek21.ui.step.OpenProductPageStep;
import by.vek21.ui.util.PriceUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FilterByPriceUiTest extends BaseUiTest {

    private FilterPage filterPage;
    private ProductPage productPage;

    @BeforeEach
    public void setUpFilterTest() {
        new OpenProductPageStep(driver).openProductPageByPopularCategory();
        filterPage = new FilterPage(driver);
    }

    @Test
    @DisplayName("Фильтр товаров по наименьшей цене")
    public void testFilterItemsByLowestPrice() {
        double minValue = 100.5;
        filterPage
                .waitForLoad()
                .setPriceFrom(String.valueOf(minValue));

        productPage = new ProductPage(driver);
        productPage.waitForLoadAfterFilter();

        List<Product> actualProducts = productPage.getAllProducts();

        Assertions.assertTrue(PriceUtil.isPricesInRange(actualProducts, minValue, Double.MAX_VALUE), "Есть товары с ценой ниже, чем " + minValue);
    }

    @Test
    @DisplayName("Фильтр товаров по наибольшей цене")
    public void testFilterItemsByHighestPrice() {
        double maxValue = 1000;

        filterPage
                .waitForLoad()
                .setPriceTo(String.valueOf(maxValue));

        productPage = new ProductPage(driver);
        productPage.waitForLoadAfterFilter();

        List<Product> actualProducts = productPage.getAllProducts();

        Assertions.assertTrue(PriceUtil.isPricesInRange(actualProducts, Double.MIN_VALUE, maxValue), "Есть товары с ценой выше, чем " + maxValue);
    }

    @Test
    @DisplayName("Фильтр товаров по заданному диапазону цен")
    public void testFilterItemsBetweenPrices() {
        double minValue = 30;
        double maxValue = 100.55;

        filterPage
                .waitForLoad()
                .setPriceFrom(String.valueOf(minValue))
                .setPriceTo(String.valueOf(maxValue));

        productPage = new ProductPage(driver);
        productPage.waitForLoadAfterFilter();

        List<Product> actualProducts = productPage.getAllProducts();

        Assertions.assertTrue(PriceUtil.isPricesInRange(actualProducts, minValue, maxValue), "Есть товары выходящие за указанный диапазон цен");
    }
}
