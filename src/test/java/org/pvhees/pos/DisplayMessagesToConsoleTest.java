package org.pvhees.pos;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.pvhees.pos.EnglishLanguageConsoleDisplay;
import org.pvhees.pos.TextUtilities;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class DisplayMessagesToConsoleTest {
    private PrintStream productionsSystemOut;

    @Before
    public void setUp() throws Exception {
        productionsSystemOut = System.out;
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(productionsSystemOut);
    }

    @Test
    public void productNotFoundMessage() throws Exception {
        ByteArrayOutputStream canvas = new ByteArrayOutputStream();
        System.setOut(new PrintStream(canvas));

        new EnglishLanguageConsoleDisplay().displayProductNotFoundMessage("987");

        Assert.assertEquals(Arrays.asList("Product not found for 987"), TextUtilities.lines(canvas.toString("UTF-8")));
    }

    @Test
    public void emptyBarcodeMessage() throws Exception {
        ByteArrayOutputStream canvas = new ByteArrayOutputStream();
        System.setOut(new PrintStream(canvas));

        new EnglishLanguageConsoleDisplay().displayEmptyBarcodeMessage();

        assertEquals(Arrays.asList("Scanning error: empty barcode"), TextUtilities.lines(canvas.toString("UTF-8")));
    }

    @Test
    public void multipleMessages() throws Exception {
        ByteArrayOutputStream canvas = new ByteArrayOutputStream();
        System.setOut(new PrintStream(canvas));

        EnglishLanguageConsoleDisplay englishLanguageConsoleDisplay = new EnglishLanguageConsoleDisplay();
        englishLanguageConsoleDisplay.displayProductNotFoundMessage("987123");
        englishLanguageConsoleDisplay.displayEmptyBarcodeMessage();
        englishLanguageConsoleDisplay.displayProductNotFoundMessage("1234");
        englishLanguageConsoleDisplay.displayEmptyBarcodeMessage();

        assertEquals(Arrays.asList(
                "Product not found for 987123",
                "Scanning error: empty barcode",
                "Product not found for 1234",
                "Scanning error: empty barcode"),
                TextUtilities.lines(canvas.toString("UTF-8")));
    }
}
