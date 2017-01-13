package org.pvhees.pos;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(Parameterized.class)
public class DisplayPricesToConsoleTest {
    private PrintStream productionsSystemOut;

    @Before
    public void setUp() throws Exception {
        productionsSystemOut = System.out;
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(productionsSystemOut);
    }

    private final int priceInCents;
    private final String expectedFormattedPrice;

    public DisplayPricesToConsoleTest(int priceInCents, String expectedFormattedPrice) {
        this.priceInCents = priceInCents;
        this.expectedFormattedPrice = expectedFormattedPrice;
    }

    @Parameterized.Parameters(name=" Monetary amount {0} displays as {1}")
    public static Collection<Object[]> data() {
        //REFACTOR use Price instead of cents-as-int
        return Arrays.asList(new Object[][]{
                {789, "$7,89"},
                {520, "$5,20"},
                {400, "$4,00"},
                {0, "$0,00"},
                {2, "$0,02"},
                {37, "$0,37"},
                {418976, "$4.189,76"},
                {210832281, "$2.108.322,81"},
        });
    }

    @Test
    public void test() throws Exception {
        PostOffice postOffice = mock(PostOffice.class);

        new EnglishLanguageDisplay(postOffice).displayPrice(Price.cents(priceInCents));

        verify(postOffice).sendMessage(expectedFormattedPrice);
    }
}
