package com.twu.biblioteca;

public class Book implements LibraryEntity {

    public Book(String name, String author, String year) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.status = true;
    }

    private String name;
    private String author;
    private String year;
    private boolean status;

    public String getTitle() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getYear() {
        return year;
    }

    @Override
    public String toString() {
        return '\'' + name + "', by " + author + ", (" + year + ")";
    }

    public boolean isAvailable() {
        return status;
    }

    public void checkOut() {
        status = false;
    }

    public void makeAvailable() {
        status = true;
    }
}
