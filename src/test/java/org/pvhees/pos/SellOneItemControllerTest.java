package org.pvhees.pos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SellOneItemControllerTest {

    @Test
    public void productFound() throws Exception {
        Catalog catalog = Mockito.mock(Catalog.class);
        Display display = Mockito.mock(Display.class);
        Price irrelevantPrice = Price.cents(795);
        when(catalog.findPrice("::product found::")).thenReturn(irrelevantPrice);

        SaleController saleController = new SaleController(catalog, display);
        saleController.onBarcode("::product found::");

        verify(display).displayPrice(irrelevantPrice);
        verifyNoMoreInteractions(display);
    }

    @Test
    public void productNotFound() throws Exception {
        Catalog catalog = Mockito.mock(Catalog.class);
        Display display = Mockito.mock(Display.class);

        when(catalog.findPrice("::product not found::")).thenReturn(null);

        SaleController saleController = new SaleController(catalog, display);
        saleController.onBarcode("::product not found::");

        verify(display).displayProductNotFoundMessage(contains("::product not found::"));
        verifyNoMoreInteractions(display);
    }

    @Test
    public void emptyBarcode() throws Exception {
        Display display = Mockito.mock(Display.class);

        SaleController saleController = new SaleController(null, display);
        saleController.onBarcode("");

        verify(display).displayEmptyBarcodeMessage();
        verifyNoMoreInteractions(display);
    }
}
