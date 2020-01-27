package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Menu {

    private final PrintStream printStream;
    private final BufferedReader reader;
    private Library library;
    private List<MenuOption> options = Arrays.asList(MenuOption.LIST, MenuOption.CHECKOUT, MenuOption.RETURN, MenuOption.QUIT);

    public Menu(PrintStream printstream, BufferedReader reader, Library library) {
        this.printStream = printstream;
        this.reader = reader;
        this.library = library;
    }

    public String printAllOptions() {
        StringBuilder stringBuilder = new StringBuilder("");
        for (MenuOption option : options) {
            String optionPrintOut = "Option " + option.getCode() + " : " + option.getName() + "\n";
            stringBuilder.append(optionPrintOut);
        }
        return stringBuilder.toString();
    }

    public List<MenuOption> getOptions() {
        return options;
    }

    public void quitApplication(){
        System.exit(0);
    }

    public boolean isOptionSelectionValid(int optionNr) {
        boolean isValidResponse = false;
        Set<Integer> set = new HashSet<>();
        for (MenuOption opt: options) {
            set.add(opt.getCode());
        }
        if (!set.contains(optionNr)) {
            printStream.println("Please select a valid method!");
        } else {
            isValidResponse = true;

        }
        return isValidResponse;
    }

    public void selectOperation(int optionNr) throws IOException {
        if (optionNr == MenuOption.valueOf("LIST").getCode()) {
            presentAvailableBooks();
        } else if (optionNr == MenuOption.valueOf("CHECKOUT").getCode()) {
            checkOutProcess();
        } else if (optionNr == MenuOption.valueOf("RETURN").getCode()) {
            returnProcess();
        } else if (optionNr == MenuOption.valueOf("QUIT").getCode()) { //This option is not tested.
            quitApplication();
        }
    }

    private void returnProcess() throws IOException {
        String bookName = getBookNameFromInput();
        boolean returnStatus = library.returnByName(bookName);
        if (returnStatus) {
            printStream.println("Thank you for returning the book.");
        } else {
            printStream.println("This is not a valid book to return.");
        }
    }

    private void checkOutProcess() throws IOException {
        presentAvailableBooks();
        String bookName = getBookNameFromInput();
        boolean checkOutStatus = library.checkOutByName(bookName);
        if (checkOutStatus) {
            printStream.println("Thank you! Enjoy the book.");
        } else {
            printStream.println("Sorry, that book is not available.");
        }
    }

    private String getBookNameFromInput() throws IOException {
        printStream.println("Write the name of the book:");
        return reader.readLine();
    }

    private void presentAvailableBooks() {
        List<Book> books = library.getAvailableBooks();
        StringBuilder stringBuilder = new StringBuilder("All the available books:");
        stringBuilder.append("\n");
        for (Book book : books) {
            stringBuilder.append(book).append("\n");
        }
        printStream.println(stringBuilder.toString());
    }

    public void start() {
        int optionNr = 0;
        boolean isValidResponse = false;
        do {
            printStream.println("Select an option number:");
            try {
                optionNr = Integer.parseInt(reader.readLine());
                isValidResponse = isOptionSelectionValid(optionNr);
                if (isValidResponse) {
                    selectOperation(optionNr);
                }
                isValidResponse = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (!isValidResponse);

    }
}
