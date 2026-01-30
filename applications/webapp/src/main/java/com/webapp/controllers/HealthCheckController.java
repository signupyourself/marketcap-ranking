package com.webapp.controllers;

import com.metrics.MetricsHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    private final MetricsHelper metricsHelper;

    HealthCheckController(){
        metricsHelper = MetricsHelper.getMetricsHelper();
    }

    @GetMapping("health-check")
    public String HealthCheck(){
        return "Healthy";
    }

    @GetMapping("metrics")
    public long Metrics(){
        //return new MetricsModel(metricsHelper.getCount(), metricsHelper.getMean());
        return metricsHelper.getCount();
    }
}
