package by.vek21.ui.util;

import by.vek21.ui.wait.Wait;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ElementUtil {

    public static List<String> getTextsFromElements(List<WebElement> elements) {
        Wait.waitForAllElementsVisibility(elements);
        return elements.stream()
                .map(WebElement::getText)
                .toList();
    }

    public static boolean isListContainText(List<String> list, String text) {
        return list.stream()
                .anyMatch(item -> item.toLowerCase().contains(text.toLowerCase()));
    }
}
