package com.twu.biblioteca;

import com.twu.biblioteca.exceptions.RatingRangeException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Library {

    Book book1 = new Book("Unquiet", "Linn Ullmann", "2018");
    Book book2 = new Book("The Value of Everything", "Mariana Mazzucato", "2018");
    Movie movie1 = new Movie("Avengers: Endgame", "Anthony Russo, Joe Russo", "2019");
    Movie movie2 = new Movie("Parasite", "Bong Joon-ho", "2019");
    List<LibraryEntity> books;
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

    public List<LibraryEntity> getAvailableBooks() {
        return getAvailableResources(books);
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

    public boolean checkOutBookByName(String bookName) {
        return checkOutEntity(getEntityByName(bookName, books));
    }

    public boolean checkOutMovieByName(String movieName) {
        return checkOutEntity(getEntityByName(movieName, movies));
    }

    private boolean checkOutEntity(LibraryEntity entity) {
        if (entity != null) {
            entity.checkOut();
            return true;
        }
        return false;
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
        Book book = (Book) getEntityByName(checkOutBookName, books);
        if (book != null && !book.isAvailable()) {
            book.makeAvailable();
            return true;
        }
        return false;
    }
}
