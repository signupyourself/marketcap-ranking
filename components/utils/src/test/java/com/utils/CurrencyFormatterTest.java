package com.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CurrencyFormatterTest {

    private static CurrencyFormatter formatter;

    @BeforeAll
    public static void setup(){
        formatter = new CurrencyFormatter();
    }

    @Test
    public void testNegativeValueForLessThanK(){
        assertEquals("-999.999", formatter.format(-999.999));
    }
    @Test
    public void testPositiveValueForLessThanK(){
        assertEquals("999.999", formatter.format(999.999));
    }

    @Test
    public void testNegativeValueForK(){
        assertEquals("-9.999 K", formatter.format(-9999.35));

    }

    @Test
    public void testPositiveValueForK(){
        assertEquals("9.999 K", formatter.format(9999.35));
    }

    @Test
    public void testNegativeValueForMillion(){
        assertEquals("-999.950 Mn", formatter.format(-999_950_000.95));
    }

    @Test
    public void  testPositiveValueForMillion(){
        assertEquals("999.950 Mn", formatter.format(999_950_000.95));

    }

    @Test
    public void testNegativeValueForBillion(){
        assertEquals("-999.999 Bn", formatter.format(-9_99_999_000_000.0));

    }

    @Test
    public void testPositiveValueForBillion(){
        assertEquals("999.999 Bn", formatter.format(9_99_999_000_000.0));
    }

    @Test
    public void testNegativeValueForTrillion(){
        assertEquals("-999.999 Tn", formatter.format(-999_999_400_000_000.0));

    }

    @Test
    public void testPositiveValueForTrillion(){
        assertEquals("999.999 Tn", formatter.format(999_999_400_000_000.0));

    }


}
