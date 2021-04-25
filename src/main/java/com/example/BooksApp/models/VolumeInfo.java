package com.example.BooksApp.models;

import java.util.List;

public class VolumeInfo{
    public String title;
    public List<String> authors;
    public String publisher;
    public List<IndustryIdentifier> industryIdentifiers;
    public ReadingModes readingModes;
    public int pageCount;
    public String printType;
    public List<String> categories;
    public double averageRating;
    public String maturityRating;
    public boolean allowAnonLogging;
    public String contentVersion;
    public ImageLinks imageLinks;
    public String language;
    public String previewLink;
    public String infoLink;
    public String canonicalVolumeLink;

    public VolumeInfo() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public List<IndustryIdentifier> getIndustryIdentifiers() {
        return industryIdentifiers;
    }

    public void setIndustryIdentifiers(List<IndustryIdentifier> industryIdentifiers) {
        this.industryIdentifiers = industryIdentifiers;
    }

    public ReadingModes getReadingModes() {
        return readingModes;
    }

    public void setReadingModes(ReadingModes readingModes) {
        this.readingModes = readingModes;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getPrintType() {
        return printType;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public String getMaturityRating() {
        return maturityRating;
    }

    public void setMaturityRating(String maturityRating) {
        this.maturityRating = maturityRating;
    }

    public boolean isAllowAnonLogging() {
        return allowAnonLogging;
    }

    public void setAllowAnonLogging(boolean allowAnonLogging) {
        this.allowAnonLogging = allowAnonLogging;
    }

    public String getContentVersion() {
        return contentVersion;
    }

    public void setContentVersion(String contentVersion) {
        this.contentVersion = contentVersion;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }

    public String getInfoLink() {
        return infoLink;
    }

    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }

    public String getCanonicalVolumeLink() {
        return canonicalVolumeLink;
    }

    public void setCanonicalVolumeLink(String canonicalVolumeLink) {
        this.canonicalVolumeLink = canonicalVolumeLink;
    }

    public VolumeInfo(String title, List<String> authors, String publisher, List<IndustryIdentifier> industryIdentifiers, ReadingModes readingModes, int pageCount, String printType, List<String> categories, double averageRating, String maturityRating, boolean allowAnonLogging, String contentVersion, ImageLinks imageLinks, String language, String previewLink, String infoLink, String canonicalVolumeLink) {
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.industryIdentifiers = industryIdentifiers;
        this.readingModes = readingModes;
        this.pageCount = pageCount;
        this.printType = printType;
        this.categories = categories;
        this.averageRating = averageRating;
        this.maturityRating = maturityRating;
        this.allowAnonLogging = allowAnonLogging;
        this.contentVersion = contentVersion;
        this.imageLinks = imageLinks;
        this.language = language;
        this.previewLink = previewLink;
        this.infoLink = infoLink;
        this.canonicalVolumeLink = canonicalVolumeLink;
    }

    @Override
    public String toString() {
        return "VolumeInfo{" +
                "title='" + title + '\'' +
                ", authors=" + authors +
                ", publisher='" + publisher + '\'' +
                ", industryIdentifiers=" + industryIdentifiers +
                ", readingModes=" + readingModes +
                ", pageCount=" + pageCount +
                ", printType='" + printType + '\'' +
                ", categories=" + categories +
                ", averageRating=" + averageRating +
                ", maturityRating='" + maturityRating + '\'' +
                ", allowAnonLogging=" + allowAnonLogging +
                ", contentVersion='" + contentVersion + '\'' +
                ", imageLinks=" + imageLinks +
                ", language='" + language + '\'' +
                ", previewLink='" + previewLink + '\'' +
                ", infoLink='" + infoLink + '\'' +
                ", canonicalVolumeLink='" + canonicalVolumeLink + '\'' +
                '}';
    }
}