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
        assertEquals("Unquiet", lib.getBooks().get(0));
    }

    @Test
    public void testLibraryHasMultipleBooksWithName() {
        Library lib = new Library();
        List books = lib.getBooks();
        List expectedBooks = Arrays.asList("Unquiet", "The Value of Everything");
        assertEquals(books, expectedBooks);
    }
}
