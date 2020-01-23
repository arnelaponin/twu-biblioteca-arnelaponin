package com.twu.biblioteca;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class MenuTests {

    @Test
    public void testMenuHasOneOption() {
        Library mockedLibrary = mock(Library.class);

        List<String> options = Arrays.asList("List of books");
        Menu menu = new Menu(mockedLibrary, options);
        assertEquals("List of books", menu.getOptions().get(0));
    }

    @Test
    public void testSelectingBookOptionReturnsListOfBooks() {
        Library lib = new Library();
        List<String> options = Arrays.asList("List of books");
        Menu menu = new Menu(lib, options);
        List<Book> books = lib.getAvailableBooks();
        Book book = books.get(0);
        assertEquals("Unquiet", book.getName());
        assertEquals("Linn Ullmann", book.getAuthor());
        assertEquals("2018", book.getYear());
    }

    @Test
    public void testValidMenuOption() {
        Library mockedLibrary = mock(Library.class);
        List<String> options = Arrays.asList("List of books", "Quit");
        Menu menu = new Menu(mockedLibrary, options);
        boolean isValid = menu.isOptionSelectionValid(0);
        assertTrue(isValid);
    }

    @Test
    public void testInvalidMenuOption() {
        Library mockedLibrary = mock(Library.class);
        List<String> options = Arrays.asList("List of books", "Quit");
        Menu menu = new Menu(mockedLibrary, options);
        boolean isValid = menu.isOptionSelectionValid(2);
        assertFalse(isValid);
    }

    @Test
    public void testMenuHasQuittingOption() {
        Library mockedLibrary = mock(Library.class);
        List<String> options = Arrays.asList("List of books", "Quit");
        Menu menu = new Menu(mockedLibrary, options);
        assertEquals("Quit", menu.getOptions().get(1));
    }

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();


    @Test
    public void testQuittingTheApplication() {
        Library mockedLibrary = mock(Library.class);
        List<String> options = Arrays.asList("Quit");
        Menu menu = new Menu(mockedLibrary, options);
        exit.expectSystemExit();
        menu.quitApplication();
    }
}
