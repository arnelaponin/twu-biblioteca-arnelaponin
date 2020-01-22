package com.twu.biblioteca;

public class Book {

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

    public String getName() {
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
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", year='" + year + '\'' +
                '}';
    }

    public boolean isAvailable() {
        return status;
    }

    public void checkOut() {
        status = false;
    }
}
