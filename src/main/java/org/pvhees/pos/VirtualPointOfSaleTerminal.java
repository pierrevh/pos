package org.pvhees.pos;

import java.util.HashMap;

public class VirtualPointOfSaleTerminal {
    public static void main(String[] args) {
        SaleController saleController = new SaleController(
                new InMemoryCatalog(
                        new HashMap<String, Price>() {{
                            put("123", Price.cents(123));
                            put("3444544", Price.cents(3455));
                            put("34545", Price.cents(1200));
                        }}
                ),
                new EnglishLanguageConsoleDisplay()
        );
        saleController.onBarcode("123");
        saleController.onBarcode("34545");
        saleController.onBarcode("999999");
        saleController.onBarcode("");
    }
}
