package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class BibliotecaApp {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        Library library = new Library();
        Menu menu = new Menu(library);
        List<Option> options = menu.getOptions();
        System.out.println(options);
        menu.pickOption(reader);


    }
}
