package com.twu.biblioteca;

import java.util.Arrays;
import java.util.List;

public class AuthenticationServiceImpl implements AuthenticationService{

    final List<User> users = Arrays.asList(
            new User ("123-4567", "test1234"),
            new User ("111-1111", "test1234")
    );

    public AuthenticationServiceImpl() throws IncorrectLibraryNumberFormat {
    }

    @Override
    public boolean userExists(String libraryNumber, String password) {
        for (User user: users) {
            if (libraryNumber.equals(user.getLibraryNumber())) {
                if (password.equals(user.getPassword())) {
                    return true;
                }
            }
        }
        return false;
    }
}
