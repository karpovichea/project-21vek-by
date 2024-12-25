package by.vek21.ui.wait;

import by.vek21.ui.driver.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Wait {

    private static final int TIMEOUT = 10;

    public static WebDriverWait getWait() {
        return new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(TIMEOUT));
    }

    public static void waitForAllElementsVisibility(List<WebElement> elements) {
        getWait().until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public static void waitForVisibility(WebElement element) {
        getWait().until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForClickable(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForNonEmptyList(List<WebElement> elements) {
        getWait().until(element -> !elements.isEmpty());
    }

    public static void waitForStalenessOfLastElement(List<WebElement> elements) {
            getWait().until(ExpectedConditions.stalenessOf(elements.getLast()));
        }
}
