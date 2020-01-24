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
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        Library library = new Library();
        List<String> options = Arrays.asList("List of books", "Check out a book", "Return a book", "Quit");
        Menu menu = new Menu(printStream, options);
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
                    }else if (optionNr == 1) {
                        checkOutProcess(library);
                    } else if (optionNr == 2) {
                        returnProcess(library);
                    } else if (optionNr == 3) {
                        menu.quitApplication();
                    }
                }
                isValidResponse = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (!isValidResponse);

    }

    private static void returnProcess(Library library) throws IOException {
        System.out.println("Write the name of the book to return:");
        String bookName = reader.readLine();
        boolean checkOutStatus = library.returnByName(bookName);
        if (checkOutStatus) {
            System.out.println("Thank you for returning the book.");
        } else {
            System.out.println("This is not a valid book to return.");
        }
        System.out.println("Select an option:");
    }

    private static void checkOutProcess(Library library) throws IOException {
        System.out.println(library.getAvailableBooks());
        System.out.println("Write the name of the book:");
        String bookName = reader.readLine();
        boolean checkOutStatus = library.checkOutByName(bookName);
        if (checkOutStatus) {
            System.out.println("Thank you! Enjoy the book.");
        } else {
            System.out.println("Sorry, that book is not available.");
        }
        System.out.println("Select an option:");
    }
}
