package com.twu.biblioteca;

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
}
