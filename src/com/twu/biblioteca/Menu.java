package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.List;

public class Menu {

    private final PrintStream printStream;
    private List<String> options;

    public Menu(PrintStream printstream, List<String> options) {
        this.printStream = printstream;
        this.options = options;
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
}
