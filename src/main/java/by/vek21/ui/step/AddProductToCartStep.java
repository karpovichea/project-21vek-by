package by.vek21.ui.step;

import by.vek21.domain.Product;
import by.vek21.ui.page.cart.CartPage;
import by.vek21.ui.page.home.HomePage;
import by.vek21.ui.page.product.ProductPage;
import io.qameta.allure.Step;

import java.util.List;

public class AddProductToCartStep {

    private final ProductPage productPage;
    private final HomePage homePage;
    private final CartPage cartPage;

    public AddProductToCartStep() {
        productPage = new ProductPage();
        homePage = new HomePage();
        cartPage = new CartPage();
    }

    @Step("Добавить товар в корзину")
    public void addProductsToCart(List<Product> products) {
        productPage.waitForLoad();
        products.forEach(product -> productPage.addProductToCartByName(product.getName()));
    }

    public List<Product> getProductsFromCart() {
        homePage.clickCartButton();
        return cartPage.waitForLoad().getAllItems();
    }
}
