package com.example.BooksApp.models;

public class ImageLinks{
    public String smallThumbnail;
    public String thumbnail;

    public ImageLinks() {
    }

    public ImageLinks(String smallThumbnail, String thumbnail) {
        this.smallThumbnail = smallThumbnail;
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "ImageLinks{" +
                "smallThumbnail='" + smallThumbnail + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }

    public String getSmallThumbnail() {
        return smallThumbnail;
    }

    public void setSmallThumbnail(String smallThumbnail) {
        this.smallThumbnail = smallThumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}