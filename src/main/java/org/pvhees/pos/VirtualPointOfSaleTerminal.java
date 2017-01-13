package org.pvhees.pos;

import org.pvhees.pos.ui.TextProcessorAndCommandInterpreter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class VirtualPointOfSaleTerminal {
    public static void main(String[] args) throws IOException {
        new TextProcessorAndCommandInterpreter(
                new SaleController(
                        new InMemoryCatalog(
                                new HashMap<String, Price>() {{
                                    put("123", Price.cents(123));
                                    put("34545", Price.cents(1200));
                                }}
                        ),
                        new EnglishLanguageDisplay(
                                new ConsolePostOffice()
                                // new UdpPostOffice(host, port, "UTF-8") for end-to-end in case we would have the LCD that JB has
                        )
                )
        ).process(
                // Smoke test that simulates receiving a few commands from stdin
//                new StringReader("123\n34545\n999999\n\n")
                new InputStreamReader(System.in)
        );
    }
}
