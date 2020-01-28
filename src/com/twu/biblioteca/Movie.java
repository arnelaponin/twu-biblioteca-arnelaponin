package com.twu.biblioteca;

import com.twu.biblioteca.exceptions.RatingRangeException;

public class Movie implements LibraryEntity {

    private String name;
    private String director;
    private String year;
    private boolean status;
    private int rating;

    public Movie(String title, String director, String year) {
        this.name = title;
        this.director = director;
        this.year = year;
        this.status = true;
        this.rating = 0;
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

    @Override
    public void checkOut() {
        status = false;
    }

    @Override
    public String toString() {
        if (rating == 0) {
            return '\'' + name + "', by " + director + ", (" + year + "), unrated";
        } else {
            return '\'' + name + "', by " + director + ", (" + year + "), " + rating;
        }
    }

    public void setRating(int rating) throws RatingRangeException {
        if (rating > 0 && rating <= 10) {
            this.rating = rating;
        } else {
            throw new RatingRangeException("rating should be between 1 and 10");
        }

    }
}
