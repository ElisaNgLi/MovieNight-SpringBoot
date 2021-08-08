package com.example.restapimongodb.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("tv")
public class Tv {

    @Id
    private int id;
    private String backdrop_path;
    private String genre;
    private String title;
    private String overview;
    private String poster_path;
    private String release_date;
    private double rating;
    private double rent;
    private double buy;
    private String type;
    private Boolean feature;

    public Tv(){}

    public Tv(int id, String backdrop_path, String genre, String title, String overview, String poster_path, String release_date, double rating, double rent, double buy, String type, Boolean feature) {
        this.id = id;
        this.backdrop_path = backdrop_path;
        this.genre = genre;
        this.title = title;
        this.overview = overview;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.rating = rating;
        this.rent = rent;
        this.buy = buy;
        this.type = type;
        this.feature = feature;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
        this.rent = rent;
    }

    public double getBuy() {
        return buy;
    }

    public void setBuy(double buy) {
        this.buy = buy;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getFeature() {
        return feature;
    }

    public void setFeature(Boolean feature) {
        this.feature = feature;
    }

    @Override
    public String toString() {
        return "Tv{" +
                "id=" + id +
                ", backdrop_path='" + backdrop_path + '\'' +
                ", genre='" + genre + '\'' +
                ", title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", release_date='" + release_date + '\'' +
                ", rating=" + rating +
                ", rent=" + rent +
                ", buy=" + buy +
                ", type='" + type + '\'' +
                ", feature=" + feature +
                '}';
    }
}
