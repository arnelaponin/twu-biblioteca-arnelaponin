package com.twu.biblioteca;


import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LibraryTest {

    @Test
    public void testLibraryHasOneBook() {
        Library lib = new Library();
        int bookCount = lib.getBooks();
        assertEquals(1, bookCount);
    }
}
