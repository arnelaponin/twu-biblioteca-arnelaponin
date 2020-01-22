package com.twu.biblioteca;


import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LibraryTest {

    @Test
    public void testLibraryHasOneBook() {
        Library lib = new Library();
        int bookCount = lib.getBookCount();
        assertTrue(bookCount > 0);
    }

    @Test
    public void testLibraryHasOneBookWithName() {
        Library lib = new Library();
        assertEquals("Unquiet", lib.getBooks().get(0).getName());
    }

    @Test
    public void testLibraryHasMultipleBooksWithName() {
        Library lib = new Library();
        List<Book> books = lib.getBooks();
        assertEquals("Unquiet", books.get(0).getName());
        assertEquals("The Value of Everything", books.get(1).getName());
    }

    @Test
    public void testLibraryHasOneBookWithNameAuthorYear() {
        Library lib = new Library();
        Book book = (Book) lib.getBooks().get(0);
        assertEquals("Unquiet", book.getName());
        assertEquals("Linn Ullmann", book.getAuthor());
        assertEquals("2018", book.getYear());
    }
}
