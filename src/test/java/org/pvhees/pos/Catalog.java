package org.pvhees.pos;

public interface Catalog {
    Price findPrice(String barcode);
}
