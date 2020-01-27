package com.twu.biblioteca;

public class Movie {

    private String title;
    private String director;
    private String year;
    private double rating;

    public Movie(String title, String director, String year, double rating) {
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public String getYear() {
        return year;
    }

    public double getRating() {
        return rating;
    }

    public boolean isAvailable() {
        return true;
    }
}
