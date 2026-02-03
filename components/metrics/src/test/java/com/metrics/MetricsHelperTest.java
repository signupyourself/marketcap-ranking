package com.metrics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MetricsHelperTest {

    @Test
    public void testGetMetricsReturnsThreeWhenWebCounterIsThree(){
        MetricsHelper metricsHelper = MetricsHelper.getMetricsHelper();
        metricsHelper.markWebMeter();
        metricsHelper.markWebMeter();
        metricsHelper.markWebMeter();
        assert(metricsHelper.getMetrics().contains("web_meter_total 3.0"));
    }

    @Test
    public void testGetMetricsReturnsThreeWhenApiCounterIsThree(){
        MetricsHelper metricsHelper = MetricsHelper.getMetricsHelper();
        metricsHelper.markApiMeter();
        metricsHelper.markApiMeter();
        metricsHelper.markApiMeter();
        assert(metricsHelper.getMetrics().contains("api_meter_total 3.0"));
    }




}
