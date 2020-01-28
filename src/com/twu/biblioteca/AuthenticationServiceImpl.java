package com.twu.biblioteca;

import com.twu.biblioteca.exceptions.IncorrectCredentialsException;
import com.twu.biblioteca.exceptions.IncorrectLibraryNumberFormat;

import java.util.Arrays;
import java.util.List;

public class AuthenticationServiceImpl implements AuthenticationService{

    User currentUser;
    final List<User> users = Arrays.asList(
            new User ("123-4567", "test1234"),
            new User ("111-1111", "test1234")
    );

    public AuthenticationServiceImpl() throws IncorrectLibraryNumberFormat {
    }

    @Override
    public boolean userExists(String libraryNumber, String password) throws IncorrectCredentialsException {
        for (User user: users) {
            if (libraryNumber.equals(user.getLibraryNumber())) {
                if (password.equals(user.getPassword())) {
                    currentUser = user;
                    return true;
                }
            }
        }
        throw new IncorrectCredentialsException("Library number and/or password were incorrect.");
    }

    @Override
    public User getCurrentUser() {
        return currentUser;
    }

    @Override
    public void removeCurrentUser() {
        currentUser = null;
    }
}
