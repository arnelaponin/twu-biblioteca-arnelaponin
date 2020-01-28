package com.twu.biblioteca;

import com.twu.biblioteca.exceptions.IncorrectCredentialsException;
import com.twu.biblioteca.exceptions.IncorrectLibraryNumberFormat;

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
    private List<MenuOption> options = Arrays.asList(
            MenuOption.LIST,
            MenuOption.CHECKOUT,
            MenuOption.RETURN,
            MenuOption.QUIT,
            MenuOption.LIST_MOVIES,
            MenuOption.CHECKOUT_MOVIE);
    AuthenticationService auth;

    public Menu(PrintStream printstream, BufferedReader reader, Library library) {
        this.printStream = printstream;
        this.reader = reader;
        this.library = library;
        try {
            auth = new AuthenticationServiceImpl();
        } catch (IncorrectLibraryNumberFormat incorrectLibraryNumberFormat) {
            incorrectLibraryNumberFormat.printStackTrace();
        }
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
            bookCheckOutProcess();
        } else if (optionNr == MenuOption.valueOf("RETURN").getCode()) {
            bookReturnProcess();
        } else if (optionNr == MenuOption.valueOf("QUIT").getCode()) { //This option is not tested.
            quitApplication();
        } else if (optionNr == MenuOption.valueOf("LIST_MOVIES").getCode()) {
            presentAvailableMovies();
        } else if (optionNr == MenuOption.valueOf("CHECKOUT_MOVIE").getCode()) {
            movieCheckOutProcess();
        }
    }

    private void movieCheckOutProcess() throws IOException {
        presentAvailableMovies();
        String movieName = getMovieNameFromInput();
        boolean checkOutStatus = library.checkOutMovieByName(movieName);
        if (checkOutStatus) {
            printStream.println("Thank you! Enjoy the movie.");
        } else {
            printStream.println("Sorry, that movie is not available.");
        }
    }

    private void bookReturnProcess() throws IOException {
        String bookName = getBookNameFromInput();
        boolean returnStatus = library.returnByName(bookName);
        if (returnStatus) {
            printStream.println("Thank you for returning the book.");
        } else {
            printStream.println("This is not a valid book to return.");
        }
    }

    private void bookCheckOutProcess() throws IOException {
        presentAvailableBooks();
        String bookName = getBookNameFromInput();
        boolean checkOutStatus = library.checkOutBookByName(bookName);
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

    private String getMovieNameFromInput() throws IOException {
        printStream.println("Write the name of the movie:");
        return reader.readLine();
    }

    private void presentAvailableBooks() {
        List<LibraryEntity> resources = library.getAvailableBooks();
        StringBuilder sb = buildResourceRepresentation(resources, "All the available books:");
        printStream.println(sb.toString());
    }

    public void presentAvailableMovies() {
        List<LibraryEntity> resources = library.getAvailableMovies();
        StringBuilder sb = buildResourceRepresentation(resources, "All the available movies:");
        printStream.println(sb.toString());
    }

    private StringBuilder buildResourceRepresentation(List<LibraryEntity> resources, String introduction) {
        StringBuilder sb = new StringBuilder(introduction);
        sb.append("\n");
        for (LibraryEntity resource : resources) {
            sb.append(resource.toString()).append("\n");
        }
        return sb;
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

    public void getUserLibraryNumberAndPassword() throws IOException {
        boolean userSet = false;
        do {
            String libraryNumber = getCredentialInput(Prompt.PLEASE_WRITE_YOUR_LIBRARY_NUMBER);
            String password = getCredentialInput(Prompt.PLEASE_WRITE_YOUR_PASSSWORD);
            try {
                userSet = auth.userExists(libraryNumber, password);
            } catch (IncorrectCredentialsException e) {
                printStream.println(e.getMessage());
            }
        } while (!userSet);

    }

    private String getCredentialInput(String pleaseWriteYourLibraryNumber) throws IOException {
        printStream.println(pleaseWriteYourLibraryNumber);
        return reader.readLine();
    }
}
