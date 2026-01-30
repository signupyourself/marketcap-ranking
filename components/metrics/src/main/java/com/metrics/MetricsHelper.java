package com.metrics;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.dropwizard.DropwizardExports;
import io.prometheus.client.hotspot.DefaultExports;
import io.micrometer.prometheusmetrics.PrometheusMeterRegistry;

public class MetricsHelper{

    MetricRegistry metricRegistry;
    Meter meter;
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
    private MetricsHelper(){
        metricRegistry = new MetricRegistry();
        meter = metricRegistry.meter("requests");
        DefaultExports.initialize();
    }

    public void mark(){
        meter.mark();
    }

    public long getCount(){
        return meter.getCount();
    }

    public long getMean(){
        return meter.getCount();
    }

//    public String prometheusData() {
//        return
//    }
}