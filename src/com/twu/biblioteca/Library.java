package com.twu.biblioteca;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Library {

    Book book1 = new Book("Unquiet", "Linn Ullmann", "2018");
    Book book2 = new Book("The Value of Everything", "Mariana Mazzucato", "2018");
    List<Book> books = Arrays.asList(book1, book2);

    public List<Book> getAvailableBooks() {
        return books.stream()
                .filter(Book::isAvailable)
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
}
