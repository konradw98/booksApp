package com.example.BooksApp.models;

public class Epub{
    private boolean isAvailable;
    private String acsTokenLink;

    public Epub() {
    }

    public Epub(boolean isAvailable, String acsTokenLink) {
        this.isAvailable = isAvailable;
        this.acsTokenLink = acsTokenLink;
    }
    @Override
    public String toString() {
        return "Epub{" +
                "isAvailable=" + isAvailable +
                ", acsTokenLink='" + acsTokenLink + '\'' +
                '}';
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