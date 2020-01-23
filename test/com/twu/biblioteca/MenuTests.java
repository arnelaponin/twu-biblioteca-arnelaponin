package com.twu.biblioteca;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class MenuTests {

    @Test
    public void testMenuHasOneOption() {
        Library lib = new Library();
        Menu menu = new Menu(lib);
        List<Option> options = menu.getOptions();
        assertEquals("List of books", options.get(0).getName());
    }

    @Test
    public void testSelectingBookOptionReturnsListOfBooks() {
        Library lib = new Library();
        Menu menu = new Menu(lib);
        List<Option> options = menu.getOptions();
        List<Book> books = options.get(0).execute();
        Book book = (Book) books.get(0);
        assertEquals("Unquiet", book.getName());
        assertEquals("Linn Ullmann", book.getAuthor());
        assertEquals("2018", book.getYear());
    }

    @Test
    public void testMenuHasQuittingOption() {
        Library lib = new Library();
        Menu menu = new Menu(lib);
        List<Option> options = menu.getOptions();
        assertEquals("Quit", options.get(1).getName());
    }
}
