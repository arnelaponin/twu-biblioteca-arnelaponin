package com.twu.biblioteca;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Library {

    Book book1 = new Book("Unquiet", "Linn Ullmann", "2018");
    Book book2 = new Book("The Value of Everything", "Mariana Mazzucato", "2018");
    Movie movie1 = new Movie("Avengers: Endgame", "Anthony Russo, Joe Russo", "2019");
    Movie movie2 = new Movie("Parasite", "Bong Joon-ho", "2019");
    List<Book> books;
    List<LibraryEntity> movies;

    public Library() {
        try {
            movie1.setRating(9);
        } catch (RatingRangeException e) {
            e.getMessage();
        }
        books = Arrays.asList(book1, book2);
        movies = Arrays.asList(movie1, movie2);
    }

    public List<Book> getAvailableBooks() {
        return books.stream()
                .filter(Book::isAvailable)
                .collect(Collectors.toList());
    }

    public List<LibraryEntity> getAvailableMovies() {
        return getAvailableResources(movies);
    }

    public List<LibraryEntity> getAvailableResources(List<LibraryEntity> resources){
        return resources.stream()
                .filter(LibraryEntity::isAvailable)
                .collect(Collectors.toList());
    }

    public int getBookCount() {
        return books.size();
    }

    public boolean checkOutByName(String bookName) {
        Book book = getBookByName(bookName);
        if (book != null) {
            book.checkOut();
            return true;
        }
        return false;
    }

    private Book getBookByName(String bookName) {
        for (Book book: books) {
            if (book.getName().equals(bookName)) {
                return book;
            }
        }
        return null;
    }

    private LibraryEntity getEntityByName(String name, List<LibraryEntity> entities) {
        for (LibraryEntity entity: entities) {
            if (entity.getTitle().equals(name)) {
                return entity;
            }
        }
        return null;
    }

    public boolean returnByName(String checkOutBookName) {
        Book book = getBookByName(checkOutBookName);
        if (book != null && !book.isAvailable()) {
            book.makeAvailable();
            return true;
        }
        return false;
    }

    public boolean checkOutMovieByName(String name) {
        LibraryEntity entity = getEntityByName(name, movies);
        if (entity != null) {
            entity.checkOut();
            return true;
        }
        return false;
    }
}
