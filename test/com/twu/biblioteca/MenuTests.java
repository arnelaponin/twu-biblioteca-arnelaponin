package com.twu.biblioteca;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class MenuTests {

    @Test
    public void testLibraryHasOneBook() {
        Library lib = new Library();
        Menu menu = new Menu();
        List<String> options = menu.getOptions();
        assertEquals("List of books", options.get(0));

    }
}
