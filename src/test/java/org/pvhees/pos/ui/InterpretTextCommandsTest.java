package org.pvhees.pos.ui;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.StringReader;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InterpretTextCommandsTest {
    @Test
    public void zero() throws Exception {
        BarcodeScannedListener barcodeScannedListener = mock(BarcodeScannedListener.class);

        new TextCommandInterpreter(barcodeScannedListener).process(new StringReader(""));

        verifyNoMoreInteractions(barcodeScannedListener);
    }

    @Test
    public void oneBarcode() throws Exception {
        BarcodeScannedListener barcodeScannedListener = mock(BarcodeScannedListener.class);

        new TextCommandInterpreter(barcodeScannedListener).process(new StringReader("::barcode::\n"));

        verify(barcodeScannedListener).onBarcode("::barcode::");
    }
}
