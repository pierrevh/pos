package org.pvhees.pos;

public interface Display {
    void displayPrice(Price price);

    void displayProductNotFoundMessage(String barcodeNotFound);

    void displayEmptyBarcodeMessage();
}
