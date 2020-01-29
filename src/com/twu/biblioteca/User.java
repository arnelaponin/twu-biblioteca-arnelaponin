package com.twu.biblioteca;

import com.twu.biblioteca.exceptions.IncorrectLibraryNumberFormat;

public class User {

    //required
    private String libraryNumber;
    private String password;
    //optional
    private String name;
    private String email;
    private String phone;

    public User(String libraryNumber, String password) throws IncorrectLibraryNumberFormat {
        if (checkLibraryNumberFormat(libraryNumber)) {
            this.libraryNumber = libraryNumber;
        } else {
            throw new IncorrectLibraryNumberFormat("Format should follow pattern xxx-xxxx, where x is a number between 0 and 9.");
        }

        this.password = password;
    }

    private User(UserBuilder builder) {
        this.libraryNumber = builder.libraryNumber;
        this.password = builder.password;
        this.name = builder.name;
        this.email = builder.email;
        this.phone = builder.phone;
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

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        String name = this.getName() == null ? "-" : this.getName();
        String email = this.getEmail() == null ? "-" : this.getEmail();
        String phone = this.getPhone() == null ? "-" : this.getPhone();
        return "Library number: " + this.getLibraryNumber() + "\n" +
                "Name: " + name + "\n" +
                "Email: " + email + "\n" +
                "Phone: " + phone;
    }

    public static class UserBuilder {
        //required
        private String libraryNumber;
        private String password;
        //optional
        private String name;
        private String email;
        private String phone;

        public UserBuilder(String libraryNumber, String password) {
            this.libraryNumber = libraryNumber;
            this.password = password;
        }

        public UserBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public User build() throws IncorrectLibraryNumberFormat {
            return new User(this);
        }
    }
}
