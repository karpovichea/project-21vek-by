package by.vek21.ui.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserActionUtil {

    public static void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void simulateUserDelay() {
        try {
            Thread.sleep(3000);
        } catch (
                InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
