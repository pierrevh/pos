package org.pvhees.pos.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.stream.Stream;

public class TextProcessorAndCommandInterpreter {
    private BarcodeScannedListener barcodeScannedListener;

    public TextProcessorAndCommandInterpreter(BarcodeScannedListener barcodeScannedListener) {
        this.barcodeScannedListener = barcodeScannedListener;
    }

    public void process(Reader reader) throws IOException {
        try (BufferedReader commandReader = new BufferedReader(reader)) {
            processLines(commandReader.lines());
        }
    }

    //REFACTOR to their own class(both sanitise)
    private void processLines(Stream<String> lines) {
        interpretLines(sanitiseLines(lines));
    }

    private Stream<String> sanitiseLines(Stream<String> lines) {
        return lines.map(line -> line.trim());
    }

    //REFACTOR to their own class (both interpret)
    private void interpretLines(Stream<String> sanitisedLines) {
        sanitisedLines
                .filter(line -> !line.isEmpty())
                .forEach(line -> interpretLine(line));
    }

    private void interpretLine(String line) {
        this.barcodeScannedListener.onBarcode(line);
    }
}
