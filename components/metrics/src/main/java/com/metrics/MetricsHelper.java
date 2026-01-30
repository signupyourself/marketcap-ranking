package com.metrics;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.dropwizard.DropwizardExports;
import io.prometheus.client.exporter.common.TextFormat;

import java.io.*;

public class MetricsHelper{

    private final Meter webMeter;
    private final Meter apiMeter;
    private final CollectorRegistry collectorRegistry;
    private static volatile MetricsHelper metricsHelperInstance = null;

    //returns singleton
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
        MetricRegistry metricRegistry = new MetricRegistry();
        webMeter = metricRegistry.meter("web-meter");
        apiMeter = metricRegistry.meter("api-meter");
        collectorRegistry = CollectorRegistry.defaultRegistry;
        collectorRegistry.register(new DropwizardExports(metricRegistry));
    }

    public void markWebMeter(){
        webMeter.mark();
    }
    public void markApiMeter(){
        apiMeter.mark();
    }

    public String getMetrics(){
        String output="";
        StringWriter stringWriter = new StringWriter();
        BufferedWriter bufferedWriter = new BufferedWriter(stringWriter);
        try {
            TextFormat.write004(bufferedWriter, collectorRegistry.metricFamilySamples());
            bufferedWriter.flush();
            output =  stringWriter.getBuffer().toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return output;
    }
}