package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BookTests {

    @Test
    public void testBookAvailability() {
        Book book = new Book("Unquiet", "Linn Ullmann", "2018");
        assertTrue(book.isAvailable());
    }

    @Test
    public void testBookCheckOut() {
        Book book = new Book("Unquiet", "Linn Ullmann", "2018");
        book.checkOut();
        assertFalse(book.isAvailable());
    }

    @Test
    public void testMakeReturn() {
        Book book = new Book("Unquiet", "Linn Ullmann", "2018");
        book.checkOut();
        book.makeAvailable();
        assertTrue(book.isAvailable());
    }
}
