package com.twu.biblioteca;

public interface AuthenticationService {

    boolean userExists(String libraryNumber, String password);
}
