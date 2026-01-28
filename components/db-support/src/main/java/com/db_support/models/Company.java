package com.db_support.models;

import com.utils.StringEscaper;

public class Company{

    private long Id;
    private String Name;
    private String Country;
    private double MarketCap;
    private String ImageUrl;
    private String Description;
    private double Revenue;
    private double Earning;
    private int MarketCapRanking;

    public Company(){}

    public String getName() {
        return Name;
    }
    public void setName(String name){
        Name = name;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }


    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getDescription() {
        return Description;
    }


    public void setDescription(String description) {
        Description = description;
    }

    public double getRevenue() {
        return Revenue;
    }

    public void setRevenue(double revenue) {
        Revenue = revenue;
    }

    public double getEarning() {
        return Earning;
    }

    public void setEarning(double earning) {
        Earning = earning;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public int getMarketCapRanking() {
        return MarketCapRanking;
    }

    public void setMarketCapRanking(int marketCapRanking) {
        MarketCapRanking = marketCapRanking;
    }

    public double getMarketCap() {
        return MarketCap;
    }

    public void setMarketCap(double marketCap) {
        MarketCap=marketCap;
    }

}