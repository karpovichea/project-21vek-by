package by.vek21.ui.util;

import by.vek21.ui.model.Product;

import java.util.List;

public class PriceUtil {

    public static double calculateTotalPrice(List<Product> products) {
        return products.stream()
                .mapToDouble(product -> parsePrice(product.getPrice()))
                .sum();
    }

    public static boolean isPricesInRange(List<Product> products, double minValue, double maxValue) {
        return products.stream()
                .mapToDouble(product -> parsePrice(product.getPrice()))
                .allMatch(price -> price >= minValue && price <= maxValue);
    }

    public static double parsePrice(String price) {
        return Double.parseDouble(price.replaceAll("[^0-9,]", "").replace(",", "."));
    }
}
