package org.pvhees.pos.ui.lcd;

import org.pvhees.pos.PostOffice;
import org.pvhees.pos.UdpPostOffice;

public class LearnUdpClientTest {

    public static void main(String[] args) throws Exception {
        new UdpPostOffice("localhost.anwb.localhost", 5358, "UTF-8")
                .sendMessage("Hello at " + System.currentTimeMillis());

    }
}
