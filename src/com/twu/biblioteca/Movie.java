package com.twu.biblioteca;

public class Movie implements LibraryEntity {

    private String name;
    private String director;
    private String year;
    private boolean status;
    private double rating;

    public Movie(String title, String director, String year, double rating) {
        this.name = title;
        this.director = director;
        this.year = year;
        this.status = true;
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }

    public String getDirector() {
        return director;
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public String getYear() {
        return year;
    }

    @Override
    public boolean isAvailable() {
        return status;
    }
}
