package com.twu.biblioteca;

import java.util.List;

public class Menu {

    private List<String> options;

    public Menu(List<String> options) {
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
