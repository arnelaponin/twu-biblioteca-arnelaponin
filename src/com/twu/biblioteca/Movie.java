package com.twu.biblioteca;

public class Movie extends LibraryEntity {

    private double rating;

    public Movie(String title, String director, String year, double rating) {
        super(title, director, year);
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }
}
