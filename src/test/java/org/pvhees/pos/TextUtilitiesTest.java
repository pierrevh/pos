package org.pvhees.pos;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TextUtilitiesTest {
    @Test
    public void testCrLf() throws Exception {
        List<String> lines = TextUtilities.lines("123\r\nabc");
        assertEquals(Arrays.asList("123", "abc"), lines);
    }
    @Test
    public void testLf() throws Exception {
        List<String> lines = TextUtilities.lines("123\nabc");
        assertEquals(Arrays.asList("123", "abc"), lines);
    }
}
