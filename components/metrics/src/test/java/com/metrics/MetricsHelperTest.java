package com.metrics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MetricsHelperTest {

    @Test
    public void testGetCountReturnsOneWhenCountIsOne(){
        MetricsHelper metricsHelper = MetricsHelper.getMetricsHelper();
        metricsHelper.mark();
        assertEquals(1, metricsHelper.getCount());
    }




}
