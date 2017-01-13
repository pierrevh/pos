package org.pvhees.pos;

import java.util.Arrays;
import java.util.List;

public class TextUtilities {
    public static List<String> lines(String text) {
        return Arrays.asList(text.split("\\R"));
    }
}
