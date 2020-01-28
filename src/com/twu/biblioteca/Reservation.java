package com.twu.biblioteca;

public class Reservation {

    private LibraryEntity libraryEntity;
    private User user;

    public Reservation(LibraryEntity libraryEntity, User user) {
        this.libraryEntity = libraryEntity;
        this.user = user;
    }

    public LibraryEntity getLibraryEntity() {
        return libraryEntity;
    }

    public User getUser() {
        return user;
    }
}
