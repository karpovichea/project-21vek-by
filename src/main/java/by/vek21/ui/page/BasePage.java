package by.vek21.ui.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {

    private static final int TIMEOUT = 10;
    protected WebDriverWait wait;
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        PageFactory.initElements(driver, this);
    }

    public abstract BasePage waitForLoad();

    public List<String> getTextsFromElements(List<WebElement> elements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));

        return elements.stream()
                .map(WebElement::getText)
                .toList();
    }

    public boolean isListContainText(List<String> list, String text) {
        return list.stream()
                .anyMatch(item -> item.toLowerCase().contains(text.toLowerCase()));
    }
}
