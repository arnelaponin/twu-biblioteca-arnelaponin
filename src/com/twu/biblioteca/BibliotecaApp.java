package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class BibliotecaApp {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static PrintStream printStream = System.out;

    public static void main(String[] args) {
        printStream.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        Library library = new Library();
        Menu menu = new Menu(printStream, reader, library);
        printStream.println(menu.printAllOptions());
        menu.start();
    }
}
