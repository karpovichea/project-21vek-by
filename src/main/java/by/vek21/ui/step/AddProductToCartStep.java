package by.vek21.ui.step;

import by.vek21.domain.Product;
import by.vek21.ui.page.CartPage;
import by.vek21.ui.page.HomePage;
import by.vek21.ui.page.ProductPage;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class AddProductToCartStep {

    private final ProductPage productPage;
    private final HomePage homePage;
    private final CartPage cartPage;

    public AddProductToCartStep(WebDriver driver) {
        productPage = new ProductPage(driver);
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
    }

    public void addProductsToCart(List<Product> products) {
        productPage.waitForLoad();
        products.forEach(product -> productPage.addProductToCartByName(product.getName()));
    }

    public List<Product> getProductsFromCart() {
        homePage.clickCartButton();
        return cartPage.waitForLoad().getAllItems();
    }
}
