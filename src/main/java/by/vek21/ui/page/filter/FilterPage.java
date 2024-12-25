package by.vek21.ui.page.filter;

import by.vek21.ui.page.BasePage;
import by.vek21.ui.wait.Wait;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class FilterPage extends BasePage {

    @FindBy(id = "minPrice")
    private WebElement priceFromField;

    @FindBy(id = "maxPrice")
    private WebElement priceToField;

    public FilterPage() {
        super();
    }

    @Override
    public FilterPage waitForLoad() {
        Wait.waitForClickable(priceFromField);
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
