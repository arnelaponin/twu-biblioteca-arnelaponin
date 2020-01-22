package com.twu.biblioteca;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Library {

    Book book1 = new Book("Unquiet", "Linn Ullmann", "2018");
    Book book2 = new Book("The Value of Everything", "Mariana Mazzucato", "2018");
    List<Book> books = Arrays.asList(book1, book2);

    public List<Book> getBooks() {
        return books.stream()
                .filter(Book::isAvailable)
                .collect(Collectors.toList());
    }

    public int getBookCount() {
        return books.size();
    }
}
