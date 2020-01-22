package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Menu {

    private List<Option> options = null;

    public Menu(Library lib) {
        this.options = Arrays.asList(new Option(lib, "List of books"));
    }


    public List<Option> getOptions() {
        return options;
    }

    public void pickOption(BufferedReader reader) {
        int optionNr = 0;
        try {
            optionNr = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(options.get(optionNr).execute());
    }
}
