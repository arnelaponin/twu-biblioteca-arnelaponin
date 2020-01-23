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
        List<String> options = Arrays.asList("List of books");
        Menu menu = new Menu(options);
        assertEquals("List of books", menu.getOptions().get(0));
    }

    @Test
    public void testSelectingBookOptionReturnsListOfBooks() {
        Library lib = new Library();
        List<String> options = Arrays.asList("List of books");
        Menu menu = new Menu(options);
        List<Book> books = lib.getAvailableBooks();
        Book book = books.get(0);
        assertEquals("Unquiet", book.getName());
        assertEquals("Linn Ullmann", book.getAuthor());
        assertEquals("2018", book.getYear());
    }

    @Test
    public void testValidMenuOption() {
        List<String> options = Arrays.asList("List of books", "Quit");
        Menu menu = new Menu(options);
        boolean isValid = menu.isOptionSelectionValid(0);
        assertTrue(isValid);
    }

    @Test
    public void testInvalidMenuOption() {
        List<String> options = Arrays.asList("List of books", "Quit");
        Menu menu = new Menu(options);
        boolean isValid = menu.isOptionSelectionValid(2);
        assertFalse(isValid);
    }

    @Test
    public void testMenuHasQuittingOption() {
        List<String> options = Arrays.asList("List of books", "Quit");
        Menu menu = new Menu(options);
        assertEquals("Quit", menu.getOptions().get(1));
    }

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();


    @Test
    public void testQuittingTheApplication() {
        List<String> options = Arrays.asList("Quit");
        Menu menu = new Menu(options);
        exit.expectSystemExit();
        menu.quitApplication();
    }
}
