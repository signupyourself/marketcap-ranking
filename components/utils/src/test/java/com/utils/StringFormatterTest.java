package com.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringFormatterTest {

    @Test
    public void testEscapeQuotesReturnsStringWithoutQuotesWhenQuotesPresent(){
        String output= StringEscaper.escapeQuotes("'Fun'");
        assertEquals("''Fun''", output);
    }

    @Test
    public void testEscapeQuotesReturnsTheSameStringWhenQuotesNotPresent(){
        String output= StringEscaper.escapeQuotes("Fun");
        assertEquals("Fun", output);
    }

    @Test
    public void testEscapeQuotesReturnsEmptyStringWhenInputIsEmptyString(){
        String output = StringEscaper.escapeQuotes("");
        assertEquals("", output);
    }
}
