package org.pvhees.pos;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public abstract class FindPriceInCatalogContract {
    @Test
    public void productFound() throws Exception {
        Price foundPrice = Price.cents(1250);

        Catalog catalog = catalogWith("::barcode::", foundPrice);
        assertEquals(foundPrice, catalog.findPrice("::barcode::"));
    }

    @Test
    public void productNotFound() throws Exception {
        Catalog catalog = catalogWithout("::barcode::");
        assertEquals(null, catalog.findPrice("::barcode::"));
    }

    protected abstract Catalog catalogWith(String barcode, Price price);
    protected abstract Catalog catalogWithout(String barcodeToAvoid);
}
