package org.pvhees.pos.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class TextCommandInterpreter {
    private BarcodeScannedListener barcodeScannedListener;

    public TextCommandInterpreter(BarcodeScannedListener barcodeScannedListener) {
        this.barcodeScannedListener = barcodeScannedListener;
    }

    public void process(Reader reader) throws IOException {
        BufferedReader commandReader = new BufferedReader(reader);
        String line = commandReader.readLine();
        if (line == null) {
            ;
        } else {
            this.barcodeScannedListener.onBarcode(line);
        }
    }
}
