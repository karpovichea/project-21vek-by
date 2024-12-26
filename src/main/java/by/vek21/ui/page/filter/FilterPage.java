package by.vek21.ui.page.filter;

import by.vek21.ui.page.BasePage;
import by.vek21.ui.wait.Wait;
import io.qameta.allure.Step;
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

    @Step("Установить в фильтре цену 'От'")
    public FilterPage setPriceFrom(String minValue) {
        priceFromField.sendKeys(minValue);
        priceFromField.sendKeys(Keys.TAB);
        return this;
    }

    @Step("Установить в фильтре цену 'До'")
    public void setPriceTo(String maxValue) {
        priceToField.sendKeys(maxValue);
        priceToField.sendKeys(Keys.TAB);
    }
}
