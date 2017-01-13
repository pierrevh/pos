package org.pvhees.pos;

import org.pvhees.pos.ui.BarcodeScannedListener;

public class SaleController implements BarcodeScannedListener {
    private Catalog catalog;
    private Display display;

    public SaleController(Catalog catalog, Display display) {
        this.catalog = catalog;
        this.display = display;
    }

    public void onBarcode(String barcode) {
        // TODO: SMELL should I get an empty barcode at all ?!
        if ("".equals(barcode)) {
            display.displayEmptyBarcodeMessage();
            return ;
        }

        Price price = catalog.findPrice(barcode);
        if (price != null) {
            display.displayPrice(catalog.findPrice(barcode));
        } else {
            display.displayProductNotFoundMessage(barcode);
        }
    }
}
