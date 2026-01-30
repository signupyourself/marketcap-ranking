//package com.db_support.models;
//
//public class CompanyDao {
//
//    private long id;
//    private String name;
//    private String country;
//    private double marketCap;
//    private String url;
//    private String imageUrl;
//    private String description;
//    private double earning;
//    private double revenue;
//
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getCountry() {
//        return country;
//    }
//
//    public void setCountry(String country) {
//        this.country = country;
//    }
//
//    public double getMarketCap() {
//        return marketCap;
//    }
//
//    public void setMarketCap(double marketCap) {
//        this.marketCap = marketCap;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public String getImageUrl() {
//        return imageUrl;
//    }
//
//    public void setImageUrl(String imageUrl) {
//        this.imageUrl = imageUrl;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public double getRevenue() {
//        return revenue;
//    }
//
//    public void setRevenue(double revenue) {
//        this.revenue = revenue;
//    }
//
//    public double getEarning() {
//        return earning;
//    }
//
//    public void setEarning(double earning) {
//        this.earning = earning;
//    }
//
//    public Company toCompany(){
//        Company company =  new Company();
//        company.setName(name);
//        company.setCountry(country);
//        company.setMarketCap(marketCap);
//        company.setImageUrl(imageUrl);
//        company.setDescription(description);
//        company.setEarning(earning);
//        company.setRevenue(revenue);
//        return company;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//}