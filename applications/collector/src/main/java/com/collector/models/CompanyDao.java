package com.collector.models;

import com.db_support.models.Company;

public class CompanyDao {
    private String name;
    private String country;
    private double mktCap;
    private String url;
    private String imageUrl;
    private String description;
    private double earnings;
    private double revenue;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getMktCap() {
        return mktCap;
    }

    public void setMktCap(double mktCap) {
        this.mktCap = mktCap;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public double getEarnings() {
        return earnings;
    }

    public void setEarnings(double earnings) {
        this.earnings = earnings;
    }

    public Company toCompany(){
        Company company =  new Company();
        company.setName(name);
        company.setCountry(country);
        company.setMarketCap(mktCap);
        company.setImageUrl(imageUrl);
        company.setDescription(description);
        company.setEarning(earnings);
        company.setRevenue(revenue);
        return company;
    }
}