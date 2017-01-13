package org.pvhees.pos;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DisplayMessagesToConsoleTest {
    @Test
    public void productNotFoundMessage() throws Exception {
        PostOffice postOffice = mock(PostOffice.class);

        new EnglishLanguageDisplay(postOffice).displayProductNotFoundMessage("987");

        verify(postOffice).sendMessage("Product not found for 987");
    }

    @Test
    public void emptyBarcodeMessage() throws Exception {
        PostOffice postOffice = mock(PostOffice.class);

        new EnglishLanguageDisplay(postOffice).displayEmptyBarcodeMessage();

        verify(postOffice).sendMessage("Scanning error: empty barcode");
    }

    @Test
    public void multipleMessages() throws Exception {
        PostOffice postOffice = mock(PostOffice.class);

        EnglishLanguageDisplay englishLanguageDisplay = new EnglishLanguageDisplay(postOffice);
        englishLanguageDisplay.displayProductNotFoundMessage("987123");
        englishLanguageDisplay.displayEmptyBarcodeMessage();
        englishLanguageDisplay.displayProductNotFoundMessage("1234");
        englishLanguageDisplay.displayEmptyBarcodeMessage();

        verify(postOffice).sendMessage("Product not found for 987123");
        verify(postOffice).sendMessage("Product not found for 1234");
    }
}
