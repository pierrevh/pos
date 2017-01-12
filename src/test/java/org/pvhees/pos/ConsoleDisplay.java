package org.pvhees.pos;

public class ConsoleDisplay {

    public void displayProductNotFoundMessage(String barcodeNotFound) {
        System.out.println(String.format("Product not found for %s", barcodeNotFound));
    }

    public void displayEmptyBarcodeMessage() {
        System.out.println(String.format("Scanning error: empty barcode"));
    }
}