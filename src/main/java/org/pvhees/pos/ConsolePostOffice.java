package org.pvhees.pos;

public class ConsolePostOffice implements PostOffice {
    @Override
    public void sendMessage(String text) {
        System.out.println(text);
    }
}
