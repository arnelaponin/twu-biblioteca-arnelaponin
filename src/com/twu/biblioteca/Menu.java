package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Menu {

    private List<Option> options = null;

    public Menu(Library lib) {
        this.options = Arrays.asList(new Option(lib, "List of books"), new Option(lib, "Quit"));
    }


    public List<Option> getOptions() {
        return options;
    }

    public void pickOption(BufferedReader reader) {
        int optionNr = 0;
        boolean isValidResponse = false;
        System.out.println("Select an option:");
        do {
            try {
                optionNr = Integer.parseInt(reader.readLine());
                isValidResponse = isOptionSelectionValid(optionNr);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (!isValidResponse);
        System.out.println(options.get(optionNr).execute());


    }

    //No tested properly
    private boolean isOptionSelectionValid(int optionNr) {
        boolean isValidResponse = false;
        if (optionNr >= options.size()) {
            System.out.println("Please select a valid method!");
        } else {
            isValidResponse = true;

        }
        return isValidResponse;
    }
}
