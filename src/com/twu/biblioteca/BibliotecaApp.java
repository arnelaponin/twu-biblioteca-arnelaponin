package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

public class BibliotecaApp {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static PrintStream printStream = System.out;

    public static void main(String[] args) {
        printStream.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        Library library = new Library();
        Menu menu = new Menu(printStream, reader, library);
        printStream.println(menu.printAllOptions());
        int optionNr = 0;
        boolean isValidResponse = false;
        printStream.println("Select an option number:");
        do {
            try {
                optionNr = Integer.parseInt(reader.readLine());
                isValidResponse = menu.isOptionSelectionValid(optionNr);
                if (isValidResponse) {
                    menu.selectOperation(optionNr);
                }
                isValidResponse = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (!isValidResponse);

    }
}
