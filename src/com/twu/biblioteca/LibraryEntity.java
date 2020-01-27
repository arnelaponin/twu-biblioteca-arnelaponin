package com.twu.biblioteca;

public abstract class LibraryEntity {

    private String title;
    private String director;
    private String year;
    private boolean available;

    public LibraryEntity(String title, String director, String year) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.available = true;
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

    public boolean isAvailable() {
        return available;
    }
}
