package by.vek21.ui.page.product;

import by.vek21.domain.Product;
import by.vek21.ui.page.BasePage;
import by.vek21.ui.util.UserActionUtil;
import by.vek21.ui.wait.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class ProductPage extends BasePage {

    private static final String PRODUCT_NAME_LOCATOR = ".//a[@data-testid='card-info']";
    private static final String PRODUCT_PRICE_LOCATOR = ".//span[@data-testid='card-current-price']";
    private static final String ADD_TO_CART_BUTTON_LOCATOR = ".//button[@data-testid='card-basket-action']";

    @FindBy(className = "ListingProductV2_product__kGFbz")
    private List<WebElement> productCards;

    public ProductPage() {
        super();
    }

    @Override
    public ProductPage waitForLoad() {
        Wait.waitForNonEmptyList(productCards);
        return this;
    }

    public void waitForLoadAfterFilter() {
        Wait.waitForStalenessOfLastElement(productCards);
    }

    public List<Product> getAllProducts() {
        return productCards.stream()
                .map(this::mapToProduct)
                .collect(Collectors.toList());
    }

    public void addProductToCartByName(String productName) {
        WebElement productElement = findProductByName(productName);
        WebElement addButton = productElement.findElement(By.xpath(ADD_TO_CART_BUTTON_LOCATOR));
        addButton.click();
    }

    private WebElement findProductByName(String productName) {
        WebElement productElement = productCards.stream()
                .filter(product -> mapToProduct(product).getName().equalsIgnoreCase(productName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Продукт с именем '" + productName + "' не найден."));

        UserActionUtil.scrollToElement(driver, productElement);
        return productElement;
    }

    private Product mapToProduct(WebElement productElement) {
        String name = productElement.findElement(By.xpath(PRODUCT_NAME_LOCATOR)).getText();
        String price = productElement.findElement(By.xpath(PRODUCT_PRICE_LOCATOR)).getText();
        return new Product(name, price);
    }
}
