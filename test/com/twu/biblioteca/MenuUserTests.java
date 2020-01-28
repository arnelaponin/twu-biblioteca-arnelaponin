package com.twu.biblioteca;

import com.twu.biblioteca.exceptions.IncorrectCredentialsException;
import com.twu.biblioteca.exceptions.IncorrectLibraryNumberFormat;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class MenuUserTests {

    private PrintStream printStream;
    private Library library;
    private BufferedReader reader;

    @Before
    public void setUp() {
        printStream = mock(PrintStream.class);
        library = new Library();
        reader = mock(BufferedReader.class);
    }

    @Test
    public void shouldAskUserForAuthentication() throws IOException {
        Menu menu = new Menu(printStream, reader, library);
        when(reader.readLine()).thenReturn("123-4567").thenReturn("test1234");
        try {
            menu.getUserLibraryNumberAndPassword();
        } catch (IOException e) {
            e.printStackTrace();
        }
        verify(printStream).println(Prompt.PLEASE_WRITE_YOUR_LIBRARY_NUMBER);
        verify(printStream).println(Prompt.PLEASE_WRITE_YOUR_PASSSWORD);
    }

    @Test
    public void shouldFindUserWhenLibraryNumberAndPasswordInputted() throws IncorrectLibraryNumberFormat, IncorrectCredentialsException {
        AuthenticationService auth = new AuthenticationServiceImpl();
        boolean userExists = auth.userExists("123-4567", "test1234");
        assertThat(userExists, is(true));
    }

    @Test(expected = IncorrectCredentialsException.class)
    public void shouldGiveExceptionWhenIncorrectLibraryNumberAndPasswordInputted() throws IncorrectLibraryNumberFormat, IncorrectCredentialsException {
        AuthenticationService auth = new AuthenticationServiceImpl();
        boolean userExists = auth.userExists("11", "test1234");

    }
}
