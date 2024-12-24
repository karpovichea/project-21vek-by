package by.vek21.ui.cart;

import by.vek21.ui.BaseUiTest;
import by.vek21.domain.Product;
import by.vek21.ui.page.CartPage;
import by.vek21.ui.step.AddProductToCartStep;
import by.vek21.ui.step.OpenProductPageStep;
import by.vek21.ui.util.PriceUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AddProductToCartUiTest extends BaseUiTest {

    private AddProductToCartStep addProductToCartStep;

    @BeforeEach
    public void setUpOrderProductTest() {
        new OpenProductPageStep(driver).openProductPageByPopularCategory();
    }

    @Test
    @DisplayName("Добавление товара в корзину")
    public void testAddProductToCart() {
        logger.info("ЗАПУСК ТЕСТА: Добавление товара в корзину");

        List<Product> expectedProducts = List.of(
                new Product("Диван угловой Mio Tesoro Атланта 110 (Savana Chocolate/Ecostile Chocolate)", "1 235,00 р.")
        );

        addProductToCartStep = new AddProductToCartStep(driver);
        addProductToCartStep.addProductsToCart(expectedProducts);

        List<Product> actualProducts = addProductToCartStep.getProductsFromCart();

        Assertions.assertEquals(expectedProducts, actualProducts, "Товар в корзине отличается");

        logger.info("ЗАВЕРШЕНИЕ ТЕСТА: Добавление товара в корзину");
    }

    @Test
    @DisplayName("Добавление нескольких товаров в корзину")
    public void testAddSeveralProductsToCart() {
        logger.info("ЗАПУСК ТЕСТА: Добавление нескольких товаров в корзину");

        List<Product> expectedProducts = List.of(
                new Product("Диван угловой Mio Tesoro Атланта 110 (Savana Chocolate/Ecostile Chocolate)", "1 235,00 р."),
                new Product("Диван Mio Tesoro Такка SB2 (Malmo 90 Grey)", "350,00 р.")
        );

        Double expectedSum = PriceUtil.calculateTotalPrice(expectedProducts);

        addProductToCartStep = new AddProductToCartStep(driver);
        addProductToCartStep.addProductsToCart(expectedProducts);

        List<Product> actualProducts = addProductToCartStep.getProductsFromCart();
        Double actualSum = PriceUtil.parsePrice(new CartPage(driver).getTotalPrice());

        Assertions.assertAll(
                () -> Assertions.assertTrue(actualProducts.containsAll(expectedProducts), "Товары в корзине отличаются"),
                () -> Assertions.assertEquals(expectedSum, actualSum, "Итоговая сумма не совпадает")
        );

        logger.info("ЗАВЕРШЕНИЕ ТЕСТА: Добавление нескольких товаров в корзину");
    }
}
