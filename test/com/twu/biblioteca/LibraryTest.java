package com.twu.biblioteca;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
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
        assertEquals("Unquiet", lib.getAvailableBooks().get(0).getTitle());
    }

    @Test
    public void testLibraryHasMultipleBooksWithName() {
        Library lib = new Library();
        List<LibraryEntity> books = lib.getAvailableBooks();
        assertEquals("Unquiet", books.get(0).getTitle());
        assertEquals("The Value of Everything", books.get(1).getTitle());
    }

    @Test
    public void testLibraryHasOneBookWithNameAuthorYear() {
        Library lib = new Library();
        Book book = (Book) lib.getAvailableBooks().get(0);
        assertEquals("Unquiet", book.getTitle());
        assertEquals("Linn Ullmann", book.getAuthor());
        assertEquals("2018", book.getYear());
    }

    @Test
    public void testBookListWithCheckOutRemoved() {
        Library lib = new Library();
        List<LibraryEntity> books = lib.getAvailableBooks();
        int originalBookCount = books.size();
        Book book = (Book) books.get(0);
        book.checkOut();
        int updatedBookCount = lib.getAvailableBooks().size();
        assertTrue(originalBookCount > updatedBookCount);
    }

    @Test
    public void testBookCheckOutByName() {
        Library library = new Library();
        String checkOutBookName = "Unquiet";
        boolean checkOutStatus = library.checkOutBookByName(checkOutBookName);
        assertTrue(checkOutStatus);
        List<LibraryEntity> books = library.getAvailableBooks();
        boolean bookInCollection = false;
        for (LibraryEntity book: books) {
            if (book.getTitle().equals(checkOutBookName)) {
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
        boolean checkOutStatus = library.checkOutBookByName(incorrectBookName);
        assertFalse(checkOutStatus);

    }

    @Test
    public void testBookReturnByName() {
        Library library = new Library();
        String checkOutBookName = "Unquiet";
        boolean checkOutStatus = library.checkOutBookByName(checkOutBookName);
        boolean returnStatus = library.returnByName(checkOutBookName);
        assertTrue(returnStatus);
        List<LibraryEntity> books = library.getAvailableBooks();
        boolean bookInCollection = false;
        for (LibraryEntity book: books) {
            if (book.getTitle().equals(checkOutBookName)) {
                bookInCollection = true;
                break;
            }
        }
        assertTrue(bookInCollection);
    }

    @Test
    public void testBookReturnByNameThatWasNotBurrowed() {
        Library library = new Library();
        String checkOutBookName = "Unquiet";
        boolean returnStatus = library.returnByName(checkOutBookName);
        assertFalse(returnStatus);
    }

    @Test
    public void testBookReturnByIncorrectName() {
        Library library = new Library();
        String checkOutBookName = "Un";
        boolean returnStatus = library.returnByName(checkOutBookName);
        assertFalse(returnStatus);
    }

    @Test
    public void shouldRetrieveAvailableMovies() {
        Library library = new Library();
        List<LibraryEntity> movies = library.getAvailableMovies();
        assertTrue(movies.size() > 0);
    }

    @Test
    public void shouldSeeNoReservationWhenNoneHaveBeenMade() {
        Library library = new Library();
        List<Reservation> reservations = library.getReservations();
        assertTrue(reservations.isEmpty());
    }

    @Test
    public void shouldSeeReservationWhenBookIsCheckedOut() {
        Library library = new Library();
        String checkOutBookName = "Unquiet";
        boolean checkOutStatus = library.checkOutBookByName(checkOutBookName);
        assertTrue(checkOutStatus);
        List<Reservation> reservations = library.getReservations();
        assertThat(reservations.size(), is(1));
        assertThat(reservations.get(0).getLibraryEntity().getTitle(), is("Unquiet"));
    }
}
