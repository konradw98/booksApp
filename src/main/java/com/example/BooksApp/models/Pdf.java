package com.example.BooksApp.models;

public class Pdf {
    private boolean isAvailable;
    private String acsTokenLink;

    public Pdf() {
    }

    public Pdf(boolean isAvailable, String acsTokenLink) {
        this.isAvailable = isAvailable;
        this.acsTokenLink = acsTokenLink;
    }


    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getAcsTokenLink() {
        return acsTokenLink;
    }

    public void setAcsTokenLink(String acsTokenLink) {
        this.acsTokenLink = acsTokenLink;
    }
}