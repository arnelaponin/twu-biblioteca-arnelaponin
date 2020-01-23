package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class BibliotecaApp {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        Library library = new Library();
        List<String> options = Arrays.asList("List of books", "Quit");
        Menu menu = new Menu(library, options);
        System.out.println(menu);
        int optionNr = 0;
        boolean isValidResponse = false;
        System.out.println("Select an option:");
        do {
            try {
                optionNr = Integer.parseInt(reader.readLine());
                isValidResponse = menu.isOptionSelectionValid(optionNr);
                if (isValidResponse) {
                    if (optionNr == 0) {
                        System.out.println(library.getAvailableBooks());
                    } else if (optionNr == 1) {
                        menu.quitApplication();
                    }
                }
                isValidResponse = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (!isValidResponse);

    }
}
