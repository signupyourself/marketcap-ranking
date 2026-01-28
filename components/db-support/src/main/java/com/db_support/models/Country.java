package com.db_support.models;

public class Country {

    private Long Id;
    private String Country;
    private double MarketCap;
    private double Earning;
    private double Revenue;
    private int MarketCapRanking;


    public Country(){}

    //getters and setters
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public double getMarketCap() {
        return MarketCap;
    }

    public void setMarketCap(double marketCap) {
        MarketCap = marketCap;
    }

    public double getEarning() {
        return Earning;
    }

    public void setEarning(double earning) {
        Earning = earning;
    }

    public double getRevenue() {
        return Revenue;
    }

    public void setRevenue(double revenue) {
        Revenue = revenue;
    }

    public int getMarketCapRanking() {
        return MarketCapRanking;
    }

    public void setMarketCapRanking(int marketCapRanking) {
        MarketCapRanking = marketCapRanking;
    }

}
