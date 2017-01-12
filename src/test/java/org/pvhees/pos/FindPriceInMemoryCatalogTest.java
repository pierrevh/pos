package org.pvhees.pos;

import java.util.Collections;

public class FindPriceInMemoryCatalogTest extends FindPriceInCatalogContract {

    @Override
    protected Catalog catalogWith(String barcode, Price price) {
        return new InMemoryCatalog(Collections.singletonMap(barcode, price));
    }

    @Override
    protected Catalog catalogWithout(String barcode) {
        return new InMemoryCatalog(Collections.singletonMap("anything but " + barcode, Price.cents(0)));
    }
}
