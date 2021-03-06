package com.twu.biblioteca;

public enum MenuOption {

    LIST("List of books", 0),
    CHECKOUT("Check out a book", 1),
    RETURN("Return a book", 2),
    QUIT("Quit", 3),
    LIST_MOVIES("List of Movies", 4),
    CHECKOUT_MOVIE("Check out a movie", 5),
    LIST_RESERVATION("List reservations", 6),
    INFORMATION("User Information", 7);

    private final String name;
    private final int code;

    MenuOption(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }
}
