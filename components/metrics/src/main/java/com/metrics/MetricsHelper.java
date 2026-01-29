package com.metrics;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

public class MetricsHelper{

    MetricRegistry metricsRegistry = new MetricRegistry();
    Meter meter = metricsRegistry.meter("requests");
    private static volatile MetricsHelper metricsHelperInstance = null;

    public static MetricsHelper getMetricsHelper(){
        if(metricsHelperInstance == null){
            synchronized (MetricsHelper.class){
                if(metricsHelperInstance == null){
                    metricsHelperInstance = new MetricsHelper();
                }
            }
        }
        return metricsHelperInstance;
    }
    private MetricsHelper(){}

    public void mark(){
        meter.mark();
    }

    public long getCount(){
        return meter.getCount();
    }

    public long getMean(){
        return meter.getCount();
    }
}