package by.vek21.ui.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class FilterPage extends BasePage {

    @FindBy(id = "minPrice")
    private WebElement priceFromField;

    @FindBy(id = "maxPrice")
    private WebElement priceToField;

    public FilterPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public FilterPage waitForLoad() {
        wait.until(ExpectedConditions.elementToBeClickable(priceFromField));
        return this;
    }

    public FilterPage setPriceFrom(String minValue) {
        priceFromField.sendKeys(minValue);
        priceFromField.sendKeys(Keys.TAB);
        return this;
    }

    public void setPriceTo(String maxValue) {
        priceToField.sendKeys(maxValue);
        priceToField.sendKeys(Keys.TAB);
    }
}
