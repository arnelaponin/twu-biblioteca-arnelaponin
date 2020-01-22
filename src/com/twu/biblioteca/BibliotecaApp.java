package com.twu.biblioteca;

import java.util.List;

public class BibliotecaApp {

    public static void main(String[] args) {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        Library library = new Library();
        Menu menu = new Menu(library);
        List<Option> options = menu.getOptions();
        System.out.println(options);

        System.out.println(options.get(0).execute());
    }
}
