package com.twu.biblioteca;

import java.util.Arrays;
import java.util.List;

public class Library {

    List books = Arrays.asList("Unquiet", "The Value of Everything");

    public List getBooks() {
        return books;
    }

    public int getBookCount() {
        return books.size();
    }
}
