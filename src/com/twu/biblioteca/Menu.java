package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Menu {

    private List<String> options;
    private Library library;

    public Menu(Library library, List<String> options) {
        this.options = options;
        this.library = library;
    //Arrays.asList(new Option(lib, "List of books"), new Option(lib, "Quit"));
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
            System.out.println("Please select a valid method!");
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
}
