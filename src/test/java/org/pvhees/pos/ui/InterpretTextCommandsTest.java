package org.pvhees.pos.ui;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.StringReader;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class InterpretTextCommandsTest {
    @Test
    public void noInput() throws Exception {
        BarcodeScannedListener barcodeScannedListener = mock(BarcodeScannedListener.class);

        new TextProcessorAndCommandInterpreter(barcodeScannedListener).process(new StringReader(""));

        verifyNoMoreInteractions(barcodeScannedListener);
    }

    @Test
    public void oneBarcode() throws Exception {
        BarcodeScannedListener barcodeScannedListener = mock(BarcodeScannedListener.class);

        new TextProcessorAndCommandInterpreter(barcodeScannedListener).process(new StringReader("::barcode::\n"));

        verify(barcodeScannedListener).onBarcode("::barcode::");
    }

    @Test
    public void severalBarcodes() throws Exception {
        BarcodeScannedListener barcodeScannedListener = mock(BarcodeScannedListener.class);

        new TextProcessorAndCommandInterpreter(barcodeScannedListener).process(new StringReader("::barcode 1::\n::barcode 2::\n::barcode 3::\n"));

        verify(barcodeScannedListener).onBarcode("::barcode 1::");
        verify(barcodeScannedListener).onBarcode("::barcode 2::");
        verify(barcodeScannedListener).onBarcode("::barcode 3::");
    }

    //SMELL runs interpreter, only to check for whitepace trimming
    @Test
    public void trimsBarcode() throws Exception {
        BarcodeScannedListener barcodeScannedListener = mock(BarcodeScannedListener.class);

        new TextProcessorAndCommandInterpreter(barcodeScannedListener).process(new StringReader("\t   \t  \t\n::barcode::    \t   \n \n"));

        verify(barcodeScannedListener).onBarcode("::barcode::");
    }

    //SMELL runs interpreter, only to check for whitepace trimming
    @Test
    public void severalBarcodesInterspersedWithEmptyBarcodes() throws Exception {
        BarcodeScannedListener barcodeScannedListener = mock(BarcodeScannedListener.class);

        new TextProcessorAndCommandInterpreter(barcodeScannedListener).process(new StringReader("\n::barcode 1::\n\n::barcode 2::\n\n\n\n::barcode 3::\n\n"));

        verify(barcodeScannedListener).onBarcode("::barcode 1::");
        verify(barcodeScannedListener).onBarcode("::barcode 2::");
        verify(barcodeScannedListener).onBarcode("::barcode 3::");
    }
}
