package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MenuTests {

    private PrintStream printStream;
    private Library library;
    private BufferedReader reader;
    List<Book> books;

    @Before
    public void setUp() throws Exception {
        printStream = mock(PrintStream.class);
        library = new Library();
        reader = mock(BufferedReader.class);
        Book book1 = new Book("Unquiet", "Linn Ullmann", "2018");
        Book book2 = new Book("The Value of Everything", "Mariana Mazzucato", "2018");
        books = Arrays.asList(book1, book2);
    }

    @Test
    public void testMenuHasOneOption() {
        List<String> options = Arrays.asList("List of books");
        Menu menu = new Menu(printStream, reader, library, options);
        assertEquals("List of books", menu.getOptions().get(0));
    }

    @Test
    public void testSelectingBookOptionReturnsListOfBooks() {
        Library lib = new Library();
        List<String> options = Arrays.asList("List of books");
        Menu menu = new Menu(printStream, reader, library, options);
        List<Book> books = lib.getAvailableBooks();
        Book book = books.get(0);
        assertEquals("Unquiet", book.getName());
        assertEquals("Linn Ullmann", book.getAuthor());
        assertEquals("2018", book.getYear());
    }

    @Test
    public void testValidMenuOption() {
        List<String> options = Arrays.asList("List of books", "Quit");
        Menu menu = new Menu(printStream, reader, library, options);
        boolean isValid = menu.isOptionSelectionValid(0);
        assertTrue(isValid);
    }

    @Test
    public void testInvalidMenuOption() {
        List<String> options = Arrays.asList("List of books", "Quit");
        Menu menu = new Menu(printStream, reader, library, options);
        boolean isValid = menu.isOptionSelectionValid(2);
        assertFalse(isValid);
    }

    @Test
    public void testMenuHasQuittingOption() {
        List<String> options = Arrays.asList("List of books", "Quit");
        Menu menu = new Menu(printStream, reader, library, options);
        assertEquals("Quit", menu.getOptions().get(1));
    }

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();


    @Test
    public void testQuittingTheApplication() {
        List<String> options = Arrays.asList("Quit");
        Menu menu = new Menu(printStream, reader, library, options);
        exit.expectSystemExit();
        menu.quitApplication();
    }

    @Test
    public void shouldPrintAllBooksWhenPrintBooksOption0Selected() throws IOException {
        Library mockedLibrary = mock(Library.class);
        List<String> options = Arrays.asList("List of books", "Check out a book");
        Menu menu = new Menu(printStream, reader, mockedLibrary,  options);
        int optionNr = 0;
        menu.selectOperation(optionNr);
        verify(mockedLibrary).getAvailableBooks();
    }

    @Test
    public void shouldStartCheckOutProcessWhenOption1Selected() throws IOException {
        Library mockedLibrary = mock(Library.class);
        List<String> options = Arrays.asList("List of books", "Check out a book");
        Menu menu = new Menu(printStream, reader, mockedLibrary,  options);
        when(reader.readLine()).thenReturn("Unquiet");
        int optionNr = 1;
        menu.selectOperation(optionNr);
        verify(mockedLibrary).checkOutByName("Unquiet");
    }

    @Test
    public void shouldNotifyOfSuccessWhenBookCheckedOutSuccessfully() throws IOException {
        List<String> options = Arrays.asList("List of books", "Check out a book");
        Menu menu = new Menu(printStream, reader, library,  options);
        when(reader.readLine()).thenReturn("Unquiet");
        int optionNr = 1;
        menu.selectOperation(optionNr);
        verify(printStream).println("Thank you! Enjoy the book.");
    }

    @Test
    public void shouldNotifyOfFailureWhenBookNotCheckedOut() throws IOException {
        List<String> options = Arrays.asList("List of books", "Check out a book");
        Menu menu = new Menu(printStream, reader, library,  options);
        when(reader.readLine()).thenReturn("Book X");
        int optionNr = 1;
        menu.selectOperation(optionNr);
        verify(printStream).println("Sorry, that book is not available.");
    }

    @Test
    public void shouldStartReturnProcessWhenOption2Selected() throws IOException {
        Library mockedLibrary = mock(Library.class);
        List<String> options = Arrays.asList("List of books", "Check out a book", "Return a book");
        Menu menu = new Menu(printStream, reader, mockedLibrary,  options);
        when(reader.readLine()).thenReturn("Unquiet");
        int optionNr = 2;
        menu.selectOperation(optionNr);
        verify(mockedLibrary).returnByName("Unquiet");
    }

    @Test
    public void shouldNotifyOfSuccessWhenBookReturnedSuccessfully() throws IOException {
        List<String> options = Arrays.asList("List of books", "Check out a book", "Return a book");
        Menu menu = new Menu(printStream, reader, library,  options);
        library.checkOutByName("Unquiet");
        when(reader.readLine()).thenReturn("Unquiet");
        int optionNr = 2;
        menu.selectOperation(optionNr);
        verify(printStream).println("Thank you for returning the book.");
    }

    @Test
    public void shouldNotifyOfFailureWhenBookNotReturned() throws IOException {
        List<String> options = Arrays.asList("List of books", "Check out a book", "Return a book");
        Menu menu = new Menu(printStream, reader, library,  options);
        library.checkOutByName("Unquiet");
        when(reader.readLine()).thenReturn("Unqu");
        int optionNr = 2;
        menu.selectOperation(optionNr);
        verify(printStream).println("This is not a valid book to return.");
    }
}
