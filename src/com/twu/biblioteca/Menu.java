package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

public class Menu {

    private final PrintStream printStream;
    private final BufferedReader reader;
    private List<String> options;
    private Library library;

    public Menu(PrintStream printstream, BufferedReader reader, Library library, List<String> options) {
        this.printStream = printstream;
        this.reader = reader;
        this.options = options;
        this.library = library;
    }


    public List<String> getOptions() {
        return options;
    }

    public void quitApplication(){
        System.exit(0);
    }

    //No tested properly
    public boolean isOptionSelectionValid(int optionNr) {
        boolean isValidResponse = false;
        if (optionNr >= options.size()) {
            printStream.println("Please select a valid method!");
        } else {
            isValidResponse = true;

        }
        return isValidResponse;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "options=" + options +
                '}';
    }

    public void selectOperation(int optionNr) throws IOException {
        if (optionNr == 0) {
            presentAvailableBooks();
        } else if (optionNr == 1) {
            presentAvailableBooks();
            String bookName = getBookNameFromInput();
            boolean checkOutStatus = library.checkOutByName(bookName);
            if (checkOutStatus) {
                printStream.println("Thank you! Enjoy the book.");
            } else {
                printStream.println("Sorry, that book is not available.");
            }
        } else if (optionNr == 2) {
            String bookName = getBookNameFromInput();
            boolean returnStatus = library.returnByName(bookName);
            if (returnStatus) {
                printStream.println("Thank you for returning the book.");
            }
        }
    }

    private String getBookNameFromInput() throws IOException {
        printStream.println("Write the name of the book:");
        return reader.readLine();
    }

    private void presentAvailableBooks() {
        List<Book> books = library.getAvailableBooks();
        printStream.println(books);
    }
}
