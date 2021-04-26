package com.example.BooksApp.models;

public class SaleInfo{
    private String country;
    private String saleability;
    private boolean isEbook;

    public SaleInfo() {
    }

    public SaleInfo(String country, String saleability, boolean isEbook) {
        this.country = country;
        this.saleability = saleability;
        this.isEbook = isEbook;
    }

    @Override
    public String toString() {
        return "SaleInfo{" +
                "country='" + country + '\'' +
                ", saleability='" + saleability + '\'' +
                ", isEbook=" + isEbook +
                '}';
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSaleability() {
        return saleability;
    }

    public void setSaleability(String saleability) {
        this.saleability = saleability;
    }

    public boolean isEbook() {
        return isEbook;
    }

    public void setEbook(boolean ebook) {
        isEbook = ebook;
    }
}