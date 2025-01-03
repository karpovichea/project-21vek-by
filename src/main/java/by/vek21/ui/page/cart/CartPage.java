package by.vek21.ui.page.cart;

import by.vek21.domain.Product;
import by.vek21.ui.page.BasePage;
import by.vek21.ui.wait.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends BasePage {

    private static final String PRODUCT_NAME_LOCATOR = ".//a[contains(@class, 'BasketItem_title')]";
    private static final String PRODUCT_PRICE_LOCATOR = ".//div[contains(@class, 'PriceBlock_priceBlock')]";

    @FindBy(xpath = "//div[@data-testid='basket-item']")
    private List<WebElement> cardProducts;

    @FindBy(xpath = "//div[@data-testid='total-price']")
    private WebElement totalPrice;

    public CartPage() {
        super();
    }

    @Override
    public CartPage waitForLoad() {
        Wait.waitForVisibility(totalPrice);
        return this;
    }

    public List<Product> getAllItems() {
        return cardProducts.stream()
                .map(this::mapToProduct)
                .collect(Collectors.toList());
    }

    public String getTotalPrice() {
       return totalPrice.getText();
    }

    private Product mapToProduct(WebElement productElement) {
        String name = productElement.findElement(By.xpath(PRODUCT_NAME_LOCATOR)).getText();
        String price = productElement.findElement(By.xpath(PRODUCT_PRICE_LOCATOR)).getText();
        return new Product(name, price);
    }
}
