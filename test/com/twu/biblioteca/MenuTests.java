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
    public void setUp() {
        printStream = mock(PrintStream.class);
        library = new Library();
        reader = mock(BufferedReader.class);
        Book book1 = new Book("Unquiet", "Linn Ullmann", "2018");
        Book book2 = new Book("The Value of Everything", "Mariana Mazzucato", "2018");
        books = Arrays.asList(book1, book2);
    }

    @Test
    public void testMenuHasOneOption() {
        Menu menu = new Menu(printStream, reader, library);
        assertEquals("List of books", menu.getOptions().get(0).getName());
    }

    @Test
    public void testSelectingBookOptionReturnsListOfBooks() {
        Library lib = new Library();
        Menu menu = new Menu(printStream, reader, library);
        List<LibraryEntity> books = lib.getAvailableBooks();
        Book book = (Book) books.get(0);
        assertEquals("Unquiet", book.getTitle());
        assertEquals("Linn Ullmann", book.getAuthor());
        assertEquals("2018", book.getYear());
    }

    @Test
    public void testValidMenuOption() {
        Menu menu = new Menu(printStream, reader, library);
        boolean isValid = menu.isOptionSelectionValid(0);
        assertTrue(isValid);
    }

    @Test
    public void testInvalidMenuOption() {
        Menu menu = new Menu(printStream, reader, library);
        boolean isValid = menu.isOptionSelectionValid(5);
        assertFalse(isValid);
    }

    @Test
    public void shouldBeInvalidWhenOptionIsLessThan0() {
        Menu menu = new Menu(printStream, reader, library);
        boolean isValid = menu.isOptionSelectionValid(-1);
        assertFalse(isValid);
    }

    @Test
    public void testMenuHasQuittingOption() {
        Menu menu = new Menu(printStream, reader, library);
        MenuOption quitOption = MenuOption.QUIT;
        assertEquals(quitOption.getName(), menu.getOptions().get(quitOption.getCode()).getName());
    }

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();


    @Test
    public void testQuittingTheApplication() {
        Menu menu = new Menu(printStream, reader, library);
        exit.expectSystemExit();
        menu.quitApplication();
    }

    @Test
    public void shouldPrintAllBooksWhenPrintBooksOption0Selected() throws IOException {
        Library mockedLibrary = mock(Library.class);
        Menu menu = new Menu(printStream, reader, mockedLibrary);
        int optionNr = 0;
        menu.selectOperation(optionNr);
        verify(mockedLibrary).getAvailableBooks();
    }

    @Test
    public void shouldPrintAllMoviesWhenPrintMoviesOption4Selected() throws IOException {
        Library mockedLibrary = mock(Library.class);
        Menu menu = new Menu(printStream, reader, mockedLibrary);
        int optionNr = 4;
        menu.selectOperation(optionNr);
        verify(mockedLibrary).getAvailableMovies();
    }

    @Test
    public void shouldPrintAllMoviesInUserFriendlyManner() throws RatingRangeException {
        Movie movie1 = new Movie("Avengers: Endgame", "Anthony Russo, Joe Russo", "2019");
        movie1.setRating(9);
        Movie movie2 = new Movie("Parasite", "Bong Joon-ho", "2019");
        Menu menu = new Menu(printStream, reader, library);

        menu.presentAvailableMovies();

        String movies = "All the available movies:\n" + movie1 + "\n" +
                movie2 + "\n";
        verify(printStream).println(movies);
    }

    @Test
    public void shouldStartCheckOutProcessWhenOption1Selected() throws IOException {
        Library mockedLibrary = mock(Library.class);
        Menu menu = new Menu(printStream, reader, mockedLibrary);
        when(reader.readLine()).thenReturn("Unquiet");
        int optionNr = 1;
        menu.selectOperation(optionNr);
        verify(mockedLibrary).checkOutBookByName("Unquiet");
    }

    @Test
    public void shouldNotifyOfSuccessWhenBookCheckedOutSuccessfully() throws IOException {
        Menu menu = new Menu(printStream, reader, library);
        when(reader.readLine()).thenReturn("Unquiet");
        int optionNr = 1;
        menu.selectOperation(optionNr);
        verify(printStream).println("Thank you! Enjoy the book.");
    }

    @Test
    public void shouldNotifyOfFailureWhenBookNotCheckedOut() throws IOException {
        Menu menu = new Menu(printStream, reader, library);
        when(reader.readLine()).thenReturn("Book X");
        int optionNr = 1;
        menu.selectOperation(optionNr);
        verify(printStream).println("Sorry, that book is not available.");
    }

    @Test
    public void shouldStartReturnProcessWhenOption2Selected() throws IOException {
        Library mockedLibrary = mock(Library.class);
        Menu menu = new Menu(printStream, reader, mockedLibrary);
        when(reader.readLine()).thenReturn("Unquiet");
        int optionNr = 2;
        menu.selectOperation(optionNr);
        verify(mockedLibrary).returnByName("Unquiet");
    }

    @Test
    public void shouldNotifyOfSuccessWhenBookReturnedSuccessfully() throws IOException {
        Menu menu = new Menu(printStream, reader, library);
        library.checkOutBookByName("Unquiet");
        when(reader.readLine()).thenReturn("Unquiet");
        int optionNr = 2;
        menu.selectOperation(optionNr);
        verify(printStream).println("Thank you for returning the book.");
    }

    @Test
    public void shouldNotifyOfFailureWhenBookNotReturned() throws IOException {
        Menu menu = new Menu(printStream, reader, library);
        library.checkOutBookByName("Unquiet");
        when(reader.readLine()).thenReturn("Unqu");
        int optionNr = 2;
        menu.selectOperation(optionNr);
        verify(printStream).println("This is not a valid book to return.");
    }

    @Test
    public void shouldStartMovieCheckOutProcessWhenOption5Selected() throws IOException {
        Library mockedLibrary = mock(Library.class);
        Menu menu = new Menu(printStream, reader, mockedLibrary);
        when(reader.readLine()).thenReturn("Parasite");
        int optionNr = 5;
        menu.selectOperation(optionNr);
        verify(mockedLibrary).checkOutMovieByName("Parasite");
    }

    @Test
    public void shouldNotifyOfSuccessWhenMovieCheckedOutSuccessfully() throws IOException {
        Menu menu = new Menu(printStream, reader, library);
        when(reader.readLine()).thenReturn("Parasite");
        int optionNr = 5;
        menu.selectOperation(optionNr);
        verify(printStream).println("Thank you! Enjoy the movie.");
    }

    @Test
    public void shouldNotifyOfFailureWhenMovieNotCheckedOut() throws IOException {
        Menu menu = new Menu(printStream, reader, library);
        when(reader.readLine()).thenReturn("Movie X");
        int optionNr = 5;
        menu.selectOperation(optionNr);
        verify(printStream).println("Sorry, that movie is not available.");
    }
}
