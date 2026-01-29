package com.metrics;

public class MetricsModel {

    public MetricsModel(long count, long meanRate) {
        this.count = count;
        this.meanRate = meanRate;
    }

    public long getMeanRate() {
        return meanRate;
    }

    public void setMeanRate(long meanRate) {
        this.meanRate = meanRate;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    long meanRate;
    long count;
}
