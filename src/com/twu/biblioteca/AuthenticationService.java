package com.twu.biblioteca;

import com.twu.biblioteca.exceptions.IncorrectCredentialsException;

public interface AuthenticationService {

    boolean userExists(String libraryNumber, String password) throws IncorrectCredentialsException;
}
