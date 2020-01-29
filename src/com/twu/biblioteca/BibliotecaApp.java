package com.twu.biblioteca;

import com.twu.biblioteca.exceptions.IncorrectLibraryNumberFormat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class BibliotecaApp {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static PrintStream printStream = System.out;

    public static void main(String[] args) throws IncorrectLibraryNumberFormat {
        printStream.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        Library library = new Library();
        AuthenticationService auth = new AuthenticationServiceImpl();
        Menu menu = new Menu(printStream, reader, library, auth);
        try {
            menu.getUserLibraryNumberAndPassword();
        } catch (IOException e) {
            e.printStackTrace();
        }
        printStream.println(menu.printAllOptions());
        menu.start();
    }
}
