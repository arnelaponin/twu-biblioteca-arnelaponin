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
            List<Book> books = library.getAvailableBooks();
            printStream.println(books);
        }else if (optionNr == 1) {
            //checkOutProcess(library);
            printStream.println(library.getAvailableBooks());
            printStream.println("Write the name of the book:");
            String bookName = reader.readLine();
            boolean checkOutStatus = library.checkOutByName(bookName);
        }
    }
}
