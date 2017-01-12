package org.pvhees.pos;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public abstract class FindPriceInCatalogContract {

    @Test
    public void productFound() {
        Catalog catalog = catalogWith("::barcode::", Price.cents(123));
        assertEquals(Price.cents(123), catalog.findPrice("::barcode::"));
    }

    @Test
    public void productNotFound() {
        Catalog catalog = catalogWithout("::barcode::");
        assertEquals(null, catalog.findPrice("::barcode::"));
    }

    protected abstract Catalog catalogWith(String barcode, Price price);
    protected abstract Catalog catalogWithout(String barcodeToAvoid);
}
