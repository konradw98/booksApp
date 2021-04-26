package com.example.BooksApp.models;

public class ReadingModes{
    private boolean text;
    private boolean image;

    public ReadingModes() {
    }

    public ReadingModes(boolean text, boolean image) {
        this.text = text;
        this.image = image;
    }

    @Override
    public String toString() {
        return "ReadingModes{" +
                "text=" + text +
                ", image=" + image +
                '}';
    }

    public boolean isText() {
        return text;
    }

    public void setText(boolean text) {
        this.text = text;
    }

    public boolean isImage() {
        return image;
    }

    public void setImage(boolean image) {
        this.image = image;
    }
}