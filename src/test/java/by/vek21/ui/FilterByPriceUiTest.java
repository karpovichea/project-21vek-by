package by.vek21.ui;

import by.vek21.domain.Product;
import by.vek21.ui.page.product.ProductPage;
import by.vek21.ui.page.filter.FilterPage;
import by.vek21.ui.step.OpenProductPageStep;
import by.vek21.ui.util.PriceUtil;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@Epic("UI тесты")
@Feature("Фильтрация товаров по цене")
public class FilterByPriceUiTest extends BaseUiTest {

    private FilterPage filterPage;
    private ProductPage productPage;

    @BeforeEach
    public void setUpFilterTest() {
        new OpenProductPageStep().openProductPageByPopularCategory();
        filterPage = new FilterPage();
    }

    @Test
    @DisplayName("Фильтрация товаров по заданной наименьшей цене")
    public void testFilterItemsByLowestPrice() {
        logger.info("ЗАПУСК ТЕСТА: Фильтр товаров по заданной наименьшей цене");

        double minValue = 100.5;
        filterPage
                .waitForLoad()
                .setPriceFrom(String.valueOf(minValue));

        productPage = new ProductPage();
        productPage.waitForLoadAfterFilter();

        List<Product> actualProducts = productPage.getAllProducts();

        Assertions.assertTrue(PriceUtil.isPricesInRange(actualProducts, minValue, Double.MAX_VALUE), "Есть товары с ценой ниже, чем " + minValue);

        logger.info("ЗАВЕРШЕНИЕ ТЕСТА: Фильтр товаров по заданной наименьшей цене");
    }

    @Test
    @DisplayName("Фильтрация товаров по заданной наибольшей цене")
    public void testFilterItemsByHighestPrice() {
        logger.info("ЗАПУСК ТЕСТА: Фильтр товаров по заданной наибольшей цене");

        double maxValue = 1000;

        filterPage
                .waitForLoad()
                .setPriceTo(String.valueOf(maxValue));

        productPage = new ProductPage();
        productPage.waitForLoadAfterFilter();

        List<Product> actualProducts = productPage.getAllProducts();

        Assertions.assertTrue(PriceUtil.isPricesInRange(actualProducts, Double.MIN_VALUE, maxValue), "Есть товары с ценой выше, чем " + maxValue);

        logger.info("ЗАВЕРШЕНИЕ ТЕСТА: Фильтр товаров по заданной наибольшей цене");
    }

    @Test
    @DisplayName("Фильтрация товаров по заданному диапазону цен")
    public void testFilterItemsBetweenPrices() {
        logger.info("ЗАПУСК ТЕСТА: Фильтр товаров по заданному диапазону цен");

        double minValue = 30;
        double maxValue = 100.55;

        filterPage
                .waitForLoad()
                .setPriceFrom(String.valueOf(minValue))
                .setPriceTo(String.valueOf(maxValue));

        productPage = new ProductPage();
        productPage.waitForLoadAfterFilter();

        List<Product> actualProducts = productPage.getAllProducts();

        Assertions.assertTrue(PriceUtil.isPricesInRange(actualProducts, minValue, maxValue),
                "Есть товары выходящие за указанный диапазон цен");

        logger.info("ЗАВЕРШЕНИЕ ТЕСТА: Фильтр товаров по заданному диапазону цен");
    }
}
