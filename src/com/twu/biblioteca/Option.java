package com.twu.biblioteca;

import java.util.List;

public class Option {

    private Library library = null;
    private String name = null;


    public Option(Library library, String name) {
        this.library = library;
        this.name = name;
    }

    public List<Book> execute() {
        return this.library.getBooks();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Option{" +
                "name='" + name + '\'' +
                '}';
    }
}
