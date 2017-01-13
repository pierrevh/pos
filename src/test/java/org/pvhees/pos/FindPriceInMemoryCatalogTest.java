package org.pvhees.pos;

import java.util.Collections;
import java.util.HashMap;

public class FindPriceInMemoryCatalogTest extends FindPriceInCatalogContract {

    @Override
    protected Catalog catalogWith(String barcode, Price price) {
        return new InMemoryCatalog(new HashMap<String, Price>(){{
            put("definitely not " + barcode, Price.cents(0));
            put(barcode, price);
            put("another, definitely not " + barcode, Price.cents(1000000));
        }});
    }

    @Override
    protected  Catalog catalogWithout(String barcodeToAvoid) {
        return new InMemoryCatalog(Collections.singletonMap("anything but " + barcodeToAvoid, Price.cents(0)));
    }

}
