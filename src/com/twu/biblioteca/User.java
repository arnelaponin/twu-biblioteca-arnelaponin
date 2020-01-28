package com.twu.biblioteca;

import com.twu.biblioteca.exceptions.IncorrectLibraryNumberFormat;

public class User {

    String libraryNumber;
    String password;

    public User(String libraryNumber, String password) throws IncorrectLibraryNumberFormat {
        if (checkLibraryNumberFormat(libraryNumber)) {
            this.libraryNumber = libraryNumber;
        } else {
            throw new IncorrectLibraryNumberFormat("Format should follow pattern xxx-xxxx, where x is a number between 0 and 9.");
        }

        this.password = password;
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public String getPassword() {
        return password;
    }

    private boolean checkLibraryNumberFormat(String libraryNumber) {
        String libraryNumberFormatPattern = "(\\d){3}[-](\\d){4}";
        return libraryNumber.matches(libraryNumberFormatPattern);
    }
}
