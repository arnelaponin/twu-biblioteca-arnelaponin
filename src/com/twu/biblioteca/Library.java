package com.twu.biblioteca;

import java.util.Arrays;
import java.util.List;

public class Library {

    Book book1 = new Book("Unquiet", "Linn Ullmann", "2018");
    Book book2 = new Book("The Value of Everything", "Mariana Mazzucato", "2018");
    List<Book> books = Arrays.asList(book1, book2);

    public List<Book> getBooks() {
        return books;
    }

    public int getBookCount() {
        return books.size();
    }
}
