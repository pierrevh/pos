package org.pvhees.pos;

public class EnglishLanguageDisplay implements Display {
    public static final String PRODUCT_NOT_FOUND_MESSAGE_FORMAT = "Product not found for %s";
    public static final String SCANNING_ERROR_EMPTY_BARCODE_MESSAGE_FORMAT = "Scanning error: empty barcode";
    public static final String PRICE_IN_DOLLARS_MESSAGE_FORMAT = "$%,.2f";

    private final PostOffice postOffice;

    public EnglishLanguageDisplay(PostOffice postOffice) {
        this.postOffice = postOffice;
    }

    private void render(String text) {
        this.postOffice.sendMessage(text);
    }

    private String mergeTemplate(String messageTemplate, Object... placeholderValues) {
        return String.format(messageTemplate, placeholderValues);
    }

    private void displayMessage(String messageTemplate, Object... placeholderValues) {
        render(mergeTemplate(messageTemplate, placeholderValues));
    }

    public void displayProductNotFoundMessage(String barcodeNotFound) {
        displayMessage(PRODUCT_NOT_FOUND_MESSAGE_FORMAT, barcodeNotFound);
    }

    public void displayEmptyBarcodeMessage() {
        displayMessage(SCANNING_ERROR_EMPTY_BARCODE_MESSAGE_FORMAT);
    }

    public void displayPrice(Price price) {
        displayMessage(PRICE_IN_DOLLARS_MESSAGE_FORMAT, price.dollarValue());
    }
}
