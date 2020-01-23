package com.twu.biblioteca;


import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

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
        assertEquals("Unquiet", lib.getAvailableBooks().get(0).getName());
    }

    @Test
    public void testLibraryHasMultipleBooksWithName() {
        Library lib = new Library();
        List<Book> books = lib.getAvailableBooks();
        assertEquals("Unquiet", books.get(0).getName());
        assertEquals("The Value of Everything", books.get(1).getName());
    }

    @Test
    public void testLibraryHasOneBookWithNameAuthorYear() {
        Library lib = new Library();
        Book book = lib.getAvailableBooks().get(0);
        assertEquals("Unquiet", book.getName());
        assertEquals("Linn Ullmann", book.getAuthor());
        assertEquals("2018", book.getYear());
    }

    @Test
    public void testBookListWithCheckOutRemoved() {
        Library lib = new Library();
        List<Book> books = lib.getAvailableBooks();
        int originalBookCount = books.size();
        Book book = books.get(0);
        book.checkOut();
        int updatedBookCount = lib.getAvailableBooks().size();
        assertTrue(originalBookCount > updatedBookCount);
    }

    @Test
    public void testBookCheckOutByName() {
        Library library = new Library();
        String checkOutBookName = "Unquiet";
        boolean checkOutStatus = library.checkOutByName(checkOutBookName);
        assertTrue(checkOutStatus);
        List<Book> books = library.getAvailableBooks();
        boolean bookInCollection = false;
        for (Book book: books) {
            if (book.getName().equals(checkOutBookName)) {
                bookInCollection = true;
                break;
            }
        }
        assertFalse(bookInCollection);
    }

    @Test
    public void testBookCheckOutByIncorrectName() {
        Library library = new Library();
        String incorrectBookName = "Unquie";
        boolean checkOutStatus = library.checkOutByName(incorrectBookName);
        assertFalse(checkOutStatus);

    }

}
